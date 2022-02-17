package tutorial.composing_suspending_functions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/*
    Lazily started async

    - async(start = CoroutineStart.LAZY)
    - async.start()
    - start를 해주지 않으면,
       - await()를 만났을 때 실행이 되기 시작함
         - 끝날 때까지 기다리고, 다음 await 호출
       - async로 코루틴을 만들지만 LAZY로 실행을 하지 않았기 때문임
       - 따라서, start 안하면 아래 예제는 2초 걸림
 */

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }

        one.start()
        two.start()
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}


/*
out>

    doSomethingUsefulOne
    doSomethingUsefulTwo
    The answer is 42
    Completed in 1012 ms

 */