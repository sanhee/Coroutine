package tutorial.composing_suspending_functions

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/*
    Concurrent using [async]

    - 코루틴 스코프 블록안에서 regular code를 작성하게 되면, 순차적으로 실행이된다.
    - 만약, 메서드 호출간에 종속성이 없을 경우
    - 두 코루틴을 동시에 호출해서 2배 더 빠르게 결과를 받을 수 있음
       - 명시적으로 async call을 하면 됨

    - async{}
       - async 자체는 코루틴 빌더
       - 이전엔 suspend -> resume 될 때까지 해당 라인을 기다렸지만, 지금은 async로 실행하고 바로 다음 라인으로 감
       - RETURN: Job을 상속받는 Deferred

    - await() : async가 끝날 때 까지 기다림
 */

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}


/*
out>
   - 이전 에제(2004ms)보다 2배 빨라진 것을 확인할 수 있음

    doSomethingUsefulOne
    doSomethingUsefulTwo
    The answer is 42
    Completed in 1011 ms
 */