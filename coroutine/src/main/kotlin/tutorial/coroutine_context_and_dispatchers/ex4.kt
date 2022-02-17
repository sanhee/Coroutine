package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.*

/*
    Job in the context

    - 코루틴 컨텍스트의 중요한 요소 Job
*/

fun main() = runBlocking<Unit> {
    println("My job is ${coroutineContext[Job]}")     // 실행한 코루틴 컨텍스트에서 Job을 꺼낸 것

    launch {
        println("My job is ${coroutineContext[Job]}")     // 실행한 코루틴 컨텍스트에서 Job을 꺼낸 것
    }

    async {
        println("My job is ${coroutineContext[Job]}")     // 실행한 코루틴 컨텍스트에서 Job을 꺼낸 것
    }

    coroutineContext[Job]?.isActive // isActive()는 확장 프로퍼티였음
}

/*
    out>

    My job is BlockingCoroutine{Active}@3159c4b8
    My job is StandaloneCoroutine{Active}@185d8b6
    My job is DeferredCoroutine{Active}@335eadca

 */