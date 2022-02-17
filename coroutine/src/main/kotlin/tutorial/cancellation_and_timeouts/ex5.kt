package tutorial.cancellation_and_timeouts

import kotlinx.coroutines.*

/*
    Run non-cancellable block
      - 드문 케이스
      - 취소된 코루틴 내에서 suspend fun를 호출해야 하는 경우
      - withContext(NonCancellable){...}
 */

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            //  job.cancelAndJoin() 으로 코루틴이 취소가 됐지만..
            withContext(NonCancellable) {
                println("job: I'm running finally")
                // 종료된 코루틴 다시 동작
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/*
 out>

        job: I'm sleeping 0 ...
        job: I'm sleeping 1 ...
        job: I'm sleeping 2 ...
        main: I'm tired of waiting!
        job: I'm running finally
        job: And I've just delayed for 1 sec because I'm non-cancellable
        main: Now I can quit.

 */