package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/*
    Jumping between threads

    JVM option: -Dkotlinx.coroutines.debug

    withContext()를 통해 스레드를 스위칭

    코루틴이 스레드를 스위칭하는 예제
 */

fun main() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                withContext(ctx2){
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
}

/*
    out >
    - 코루틴 하나에서 스레드를 교체

    [Ctx1 @coroutine#1 Started in ctx1]
    [Ctx2 @coroutine#1 Working in ctx2]
    [Ctx1 @coroutine#1 Back to ctx1]
 */