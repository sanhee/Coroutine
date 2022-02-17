package tutorial.composing_suspending_functions

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/*
    Async-style functions
     - 유용하게 아무곳에서나 사용할 수 있게끔 하기위해 async로직을 일반 메서드로 분리할 수도 있지만...
     - Using this style with Kotlin coroutines is strongly discouraged
       - [하지 말라는 예시임]

     - 만약 val one = somethingUsefulOneAsync()와 one.await() 사이에서 예외가 발생한다고 가정해보면
       - 예외가 발생되어도, 여전히 백그라운드에서 코루틴이 실행되고 있을 것임
         - 메인의 runBlocking과는 다른 코루틴 스코프(GlobalScope)이므로
 */
fun main() {
    try {
        val time = measureTimeMillis {
            val one = somethingUsefulOneAsync() // Async-style functions
            val two = somethingUsefulTwoAsync() // Async-style functions

            throw Exception("*********** exceptions!!! ***************")

            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }catch (e: Exception){
        println(e.message)
    }

    runBlocking {
        delay(1000000)
    }
}


// 일반 메서드
fun somethingUsefulOneAsync() = GlobalScope.async {
    println("start, somethingUsefulOneAsync")
    val res = doSomethingUseful1()
    println("end, somethingUsefulOneAsync")
    res
}


// 일반 메서드
fun somethingUsefulTwoAsync() = GlobalScope.async {
    println("start, somethingUsefulTwoAsync")
    val res = doSomethingUseful2()
    println("end, somethingUsefulTwoAsync")
    res
}

suspend fun doSomethingUseful1(): Int {
    println("doSomethingUsefulOne")
    delay(3000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUseful2(): Int {
    println("doSomethingUsefulTwo")
    delay(3000L) // pretend we are doing something useful here, too
    return 29
}


/*
out>

    - 다른 코루틴 스코프라 지역에서 발생한 예외를 캐치하지 못함

    *********** exceptions!!! ***************
    start, somethingUsefulOneAsync
    start, somethingUsefulTwoAsync
    doSomethingUsefulOne
    doSomethingUsefulTwo
    end, somethingUsefulTwoAsync
    end, somethingUsefulOneAsync


    waiting....
 */