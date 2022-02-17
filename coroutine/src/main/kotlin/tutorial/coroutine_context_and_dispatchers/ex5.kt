package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    Children of a coroutine
*/


fun main() = runBlocking {

    // launch a coroutine to process some kind of incoming request
    val request = launch {
        // 명시적으로 다른 컨텍스트를 지정해줌
        // request가 취소되든 말든 이 블록은 진행됨
        launch(Job()) {
            println("job1: I run in my own Job and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // 부모 컨텍스트를 상속 받음
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")

}

/*
    out>

    job1: I run in my own Job and execute independently!
    job2: I am a child of the request coroutine
    job1: I am not affected by cancellation of the request
    main: Who has survived request cancellation?
 */