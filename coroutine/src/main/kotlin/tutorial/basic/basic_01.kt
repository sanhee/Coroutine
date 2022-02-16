package tutorial.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/*
    미션: GlobalScope.launch {} => thread{} 로 교체

     - 결과는 이전 예제와 같음
     - 스레드랑 코루틴이 비슷한 것처럼 보임.
 */


fun main() {
    thread {
        Thread.sleep(2000L)
        println("World!")
    }

    println("Hello,")
    Thread.sleep(2000L)
}
