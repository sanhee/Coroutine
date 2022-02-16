package tutorial.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    suspend <-> resume example
 */

fun main() = runBlocking {
    launch {
        repeat(5) { i ->
            println("Coroutine A, $i ...")
            delay(10L)
        }
    }

    launch {
        repeat(5) { i ->
            println("Coroutine B, $i ...")
            delay(10L)
        }
    }

    println("Coroutine Outer")
}


/*
    (**) vm option : -Dkotlinx.coroutines.debug

    out>
        - 코루틴 A,B가 서로 번갈아 가며 실행 됨
        - 런치 A가 먼저 실행될 것 같은데 Coroutine Outer 가 먼저 출력되는 걸 보면
          - 추측해볼 수 있는건, 런치라는 건 코루틴이 실행되기 위한 스케줄링 등록


    Coroutine Outer [main @coroutine#1]
    Coroutine A, 0 ... [main @coroutine#2]
    Coroutine B, 0 ... [main @coroutine#3]
    Coroutine A, 1 ... [main @coroutine#2]
    Coroutine B, 1 ... [main @coroutine#3]
    Coroutine A, 2 ... [main @coroutine#2]
    Coroutine B, 2 ... [main @coroutine#3]
    Coroutine A, 3 ... [main @coroutine#2]
    Coroutine B, 3 ... [main @coroutine#3]
    Coroutine A, 4 ... [main @coroutine#2]
    Coroutine B, 4 ... [main @coroutine#3]
 */
