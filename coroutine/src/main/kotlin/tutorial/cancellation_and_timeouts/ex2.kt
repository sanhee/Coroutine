package tutorial.cancellation_and_timeouts

import kotlinx.coroutines.*

/*
    Cancellation is cooperative

    - 코루틴을 취소하기 위해서는 코루틴 코드가 협조적이야 합니다.
      - (방법 1) 주기적으로 suspend fun를 호출
         - job이 캔슬이 되면, 코루틴 내부에서 suspend가 되었다가 다시 재개될 때 exception을 던짐
         - kotlinx.coroutines.JobCancellationException

    - https://youtu.be/GmVv98LUa0k
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        try {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    yield() // suspend fun, 없을 경우  job.cancelAndJoin() 이 안먹힘
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        } catch (e: Exception) {
            println("Exception: ${e}")
        }
    }

    delay(1300L) // suspend fun
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")

}

/*
 out>

    job: I'm sleeping 0 ...
    job: I'm sleeping 1 ...
    job: I'm sleeping 2 ...
    main: I'm tired of waiting!
    Exception: kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@12acbb5a
    main: Now I can quit.

 */