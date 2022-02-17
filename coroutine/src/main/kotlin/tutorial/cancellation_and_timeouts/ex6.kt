package tutorial.cancellation_and_timeouts

import kotlinx.coroutines.*

/*
    Timeout

    - 이전까지는 코루틴 스스로가 내부에서 캔슬을 체크하는 2가지 방법에 대해 배움
      - suspend fuc 주기적 호출, isActive() 체크
    - Timout 방식은 코루틴의 job을 통해 cancel하는 것이 아님
    - 미리 타임아웃을 정하고, 일정시간이 지나면 종료

 */

fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

/*
 out>
  - Exception이 발생

    I'm sleeping 0 ...
    I'm sleeping 1 ...
    I'm sleeping 2 ...
    Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
        at kotlinx.coroutines.TimeoutKt.TimeoutCancellationException(Timeout.kt:186)
        at kotlinx.coroutines.TimeoutCoroutine.run(Timeout.kt:156)
        at kotlinx.coroutines.EventLoopImplBase$DelayedRunnableTask.run(EventLoop.common.kt:497)
        at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:274)
        at kotlinx.coroutines.DefaultExecutor.run(DefaultExecutor.kt:69)
        at java.base/java.lang.Thread.run(Thread.java:829)

 */