package tutorial.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    Bridging blocking and non-blocking worlds

    delay
     - 코루틴 스코프안에서 수행됨
    launch:
      - 코루틴 빌더
      - 자신을 호출한 스레드를 Blocking 하지 않음
    runBlocking:
      - 코루틴 빌더
      - 자신을 호출한 스레드 Blocking
 */

fun main() {
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    println("Hello,")
    runBlocking {  // Blocking 스레드가 아니라면 해당 프로그램은 Hello,만 출력하고 종료
        delay(2000L)
    }
}
