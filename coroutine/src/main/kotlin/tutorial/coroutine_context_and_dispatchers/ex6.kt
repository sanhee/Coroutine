package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    Parental responsibilities

    - 부모 코루틴은 모든 자식 코루틴이 끝날 때까지 기다려줌
    - 부모 코루틴에서 자식 코루틴을 만들면 Join을 할 필요가 없음
*/


fun main() = runBlocking {
    // launch a coroutine to process some kind of incoming request
    val request = launch {
        repeat(3) { i -> // launch a few children jobs
            launch  {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    //request.join() // wait for completion of the request, including all its children
    println("Now processing of the request is complete")

}

/*
    out>

    Now processing of the request is complete
    request: I'm done and I don't explicitly join my children that are still active
    Coroutine 0 is done
    Coroutine 1 is done
    Coroutine 2 is done
 */