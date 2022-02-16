package tutorial.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    코루틴
        - 경량 스레드
    launch (코루틴 빌더)
        - 코루틴 빌더를 사용하기 위해선 스코프가 필요함
    CoroutineScope, GlobalScope

    미션: GlobalScope.launch {} => thread{} 로 교체
 */


fun main() {
    // launch: 코루틴 빌더, 자신을 호출한 스레드를 Blocking 하지 않음
    GlobalScope.launch {
        // 함수를 일시 중단 (suspend)
        delay(1000L)
        println("World!")
    }

    println("Hello,")
    Thread.sleep(2000L) // (메인) 스레드를 Blocking 하는 함수
}
