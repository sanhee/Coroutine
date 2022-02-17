package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


/*
    Debugging coroutines and threads

    JVM option: -Dkotlinx.coroutines.debug

 */

fun log(msg: String) = println("[${Thread.currentThread().name} ${msg}]")

fun main() = runBlocking {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}


/*
    out>
    - @coroutine#생성된 번호(순서)

    [main @coroutine#2 I'm computing a piece of the answer]
    [main @coroutine#3 I'm computing another piece of the answer]
    [main @coroutine#1 The answer is 42]
 */