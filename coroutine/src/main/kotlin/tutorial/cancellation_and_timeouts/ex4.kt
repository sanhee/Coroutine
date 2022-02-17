package tutorial.cancellation_and_timeouts

import kotlinx.coroutines.*

/*
    Closing resources with finally
      - suspend fun으로 취소를 체크 할 때 리소스해제 지역은 finally block
 */

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
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
        main: Now I can quit.

 */