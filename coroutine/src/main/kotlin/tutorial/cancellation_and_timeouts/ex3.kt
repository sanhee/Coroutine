package tutorial.cancellation_and_timeouts

import kotlinx.coroutines.*

/*
    Making computation code cancellable
      - 코루틴을 취소하기 위해서는 코루틴 코드가 협조적이야 합니다.
        - (방법 2) 명시적으로 취소 상태임을 확인합니다. (isActive)
            - 코루틴 상태값을 체크해서 종료 시키는 것
            - (방법 1)처럼 exception을 던지지 않는 게 차이점
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        println("[before while] -  isActive: $isActive")
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
        println("[after while] -  isActive: $isActive")
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/*
 out>

    [before while] -  isActive: true

    job: I'm sleeping 0 ...
    job: I'm sleeping 1 ...
    job: I'm sleeping 2 ...

    main: I'm tired of waiting!

    [after while] -  isActive: false
    main: Now I can quit.
 */