package tutorial.composing_suspending_functions

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/*
    Structured concurrency with async

    - exception에 대한 핸들링을 잘하기 위해서, 동일한 코루틴 스코프 안에서 suspend func을 조합해서 사용해야 함
 */


fun main() = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")

    runBlocking {
        delay(1000000)
    }
}


// 일반 메서드가 아니므로, 더이상 아무곳에서 사용할 수는 없음
// 다만 코루틴 사이에서 exception이 발생했을 때, exception이 전파되면서 모든 코루틴이 취소 될 수 있음
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne1() }
    val two = async { doSomethingUsefulTwo2() }

    delay(10)
    throw Exception()
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne1(): Int {
    println("start, doSomethingUsefulOne")
    delay(3000L) // pretend we are doing something useful here
    println("end, doSomethingUsefulOne")
    return 13
}

suspend fun doSomethingUsefulTwo2(): Int {

    println("start, doSomethingUsefulTwo")
    delay(3000L)
    println("end, doSomethingUsefulTwo")
    return 29
}

/*
out>

   - 2개의 async코루틴이 실행되었지만 예외발생으로 모든 코루틴이 종료됨

    start, doSomethingUsefulOne
    start, doSomethingUsefulTwo
    Exception in thread "main" java.lang.Exception

 */