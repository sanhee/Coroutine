package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    (*jvm : -Dkotlinx.coroutines.debug)

    Combining context elements

    - 코루틴 컨텍스트에 멀티 요소 등록하는 법
      -> + 연산자
 */

fun main() : Unit = runBlocking {
    launch(Dispatchers.Default + CoroutineName("test")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
}

/*
    out>

    I'm working in thread DefaultDispatcher-worker-1 @test#2
 */