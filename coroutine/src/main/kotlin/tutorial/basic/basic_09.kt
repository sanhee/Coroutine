package tutorial.basic

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    launch {
        repeat(5){
            i-> println("Coroutine A, $i ...")
        }
    }

    launch {
        repeat(5){
                i-> println("Coroutine B, $i ...")
        }
    }

    println("Coroutine Outer")
}


fun <T> println(msg: T){
    kotlin.io.println("$msg [${Thread.currentThread().name}]")
}


/*
    (**) vm option : -Dkotlinx.coroutines.debug

    out>
        - 한 개의 스레드에서 3개의 코루틴 생성 (runBlocking, launch, launch)


    Coroutine Outer [main @coroutine#1]
    Coroutine A, 0 ... [main @coroutine#2]
    Coroutine A, 1 ... [main @coroutine#2]
    Coroutine A, 2 ... [main @coroutine#2]
    Coroutine A, 3 ... [main @coroutine#2]
    Coroutine A, 4 ... [main @coroutine#2]
    Coroutine B, 0 ... [main @coroutine#3]
    Coroutine B, 1 ... [main @coroutine#3]
    Coroutine B, 2 ... [main @coroutine#3]
    Coroutine B, 3 ... [main @coroutine#3]
    Coroutine B, 4 ... [main @coroutine#3]
 */
