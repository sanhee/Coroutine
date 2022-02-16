package tutorial.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    suspend <-> resume example
 */

fun main() = runBlocking {
    launch {
        repeat(5){
            i-> println("Coroutine A, $i ...")
            delay(10L)
        }
    }

    launch {
        repeat(5){
                i-> println("Coroutine B, $i ...")
        }
    }

    println("Coroutine Outer")
}


/*
    (**) vm option : -Dkotlinx.coroutines.debug

    out>
        - 이전처럼 순차적인 결과가 아님.
          - A 코루틴이 실행되고, delay로 코루틴 중단된 뒤, B 코루틴 모두 실행후 다시 A 코루틴 실행


    Coroutine Outer [main @coroutine#1]
    Coroutine A, 0 ... [main @coroutine#2]
    Coroutine B, 0 ... [main @coroutine#3]
    Coroutine B, 1 ... [main @coroutine#3]
    Coroutine B, 2 ... [main @coroutine#3]
    Coroutine B, 3 ... [main @coroutine#3]
    Coroutine B, 4 ... [main @coroutine#3]
    Coroutine A, 1 ... [main @coroutine#2]
    Coroutine A, 2 ... [main @coroutine#2]
    Coroutine A, 3 ... [main @coroutine#2]
    Coroutine A, 4 ... [main @coroutine#2]
 */
