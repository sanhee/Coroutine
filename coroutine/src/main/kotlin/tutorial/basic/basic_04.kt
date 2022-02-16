package tutorial.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    Waiting for a job
     - 이전 예제에서 마지막에 delay를 사용하는 방법은 좋은 방법이 아님.
       - 시간에 따라서 원하는대로 안될 수 있음

     - 명시적으로 기다리게 하는 방법: Job
 */

// runBlocking: 코루틴 빌더, 자신을 호출한 스레드 Blocking
fun main() = runBlocking {
    // launch: 코루틴 빌더, 자신을 호출한 스레드를 Blocking 하지 않음
    val job = GlobalScope.launch {
        // 함수를 일시 중단 (suspend)
        delay(3000L) // 3초뒤 world 실행
        println("World!")
    }

    println("Hello,")
    job.join() // job이 완료될 때까지 기다림.
}
