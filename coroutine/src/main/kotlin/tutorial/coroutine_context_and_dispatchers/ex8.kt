package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.*

/*
    Coroutine scope

    - 모든 코루틴은 메모리를 피하기 위해 활동이 소멸될 때 취소되어야 합니다.
    - 안드로이드나 라이프 사이클이 있는 클래스의 경우, CoroutineScope를 만들어서 라이프 사이클에 맞게 연결
 */

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default)

    fun destroy() {
        mainScope.cancel()
    }// class Activity continues

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                println("Coroutine $i is done")
            }
        }
    }
}

fun main() : Unit = runBlocking {

    val activity = Activity()
    activity.doSomething() // run test function

    println("Launched coroutines")
    delay(500L) // delay for half a second
    println("Destroying activity!")

    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work
}
