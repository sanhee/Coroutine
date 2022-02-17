package tutorial.composing_suspending_functions

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/*
    Cancellation propagated coroutines hierarchy

    - Note how both the first async and the awaiting parent are cancelled on failure of one of the children (namely, two)
 */


fun main() = runBlocking<Unit> {
    try {
        failedConcurrentSum()
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()  // *************
    }
    one.await() + two.await()
}


/*
out>

    Second child throws an exception
    First child was cancelled
    Computation failed with ArithmeticException
 */