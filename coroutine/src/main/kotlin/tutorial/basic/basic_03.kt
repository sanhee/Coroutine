package tutorial.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    이전 예제보다 관용적인 표현방법
     - runBlocking으로 감쌌음
     - 결국 main 함수내 모든 로직이 처리되기 전까지 리턴되지 않는 것을 원함.
*/

// runBlocking: 코루틴 빌더, 자신을 호출한 스레드 Blocking
fun main() = runBlocking {
    GlobalScope.launch {
        // 함수를 일시 중단 (suspend)
        delay(1000L)
        println("World!")
    }

    println("Hello,")
    delay(2000L)
}
