package tutorial.coroutine_context_and_dispatchers

import kotlinx.coroutines.*

/*
    Dispatchers and threads
     - 모든 코루틴 빌더들은 optional로 CoroutineContext 파라미터를 가짐
     - Dispatchers : 어떤 코르틴이 어떤 스레드에서 실행될지 결정 해주는 요소
 */

fun main(): Unit = runBlocking {
    launch { // main thread, 자신을 호출했던 코루틴 스코프에서 컨텍스트를 상속받아서 실행
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // DefaultDispatcher-worker-1 thread, GlobalScope에서 실행되던 코루틴의 기본 스레드
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    GlobalScope.launch {
        println("GlobalScope            : I'm working in thread ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("MyOwnThread")) { // 코루틴을 실행할 때마다, 스레드를 만듬. close 안하면 memory leak 가능성
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }

    // 위 방식보다 아래 use 방식 권장
    // use block안에서는 close()를 자동으로 해줌, 메모리 leak 방지
    newSingleThreadContext("MyOwnThread2").use {
        launch(it) {
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }


}


/*

 out >

    Unconfined            : I'm working in thread main
    Default               : I'm working in thread DefaultDispatcher-worker-1
    GlobalScope            : I'm working in thread DefaultDispatcher-worker-2
    main runBlocking      : I'm working in thread main
    newSingleThreadContext: I'm working in thread MyOwnThread

 */