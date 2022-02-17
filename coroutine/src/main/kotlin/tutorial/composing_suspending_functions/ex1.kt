package tutorial.composing_suspending_functions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/*
    Sequential by default

    비동기 호출을 하게 되면, 순차를 맞추기 어렵다.
    만약, 실행을 순서대로 하고 싶다면 어떻게 작성하는게 좋을까?

    * 코루틴은 기본적으로 일반적인 코드 처럼 순차적으로 제공한다.
      - main thread를 blocking 하지 않음
 */

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

// network 같이 무거운 동작을 비동기로 한다고 생각
suspend fun doSomethingUsefulOne(): Int {
    println("doSomethingUsefulOne")
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

/*
out>
   - 2초가 걸린 건 각 비동기 메서드가 1초씩

    doSomethingUsefulOne
    doSomethingUsefulTwo
    The answer is 42
    Completed in 2004 ms
 */