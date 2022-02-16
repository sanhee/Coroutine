package tutorial.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    - 이전까지 GlobalScope.launch를 사용하여, 탑레벨 코루틴을 생성했습니다.

    - 이전 예제처럼 실행되고 있는 모든 코루틴을 수동으로 유지하고 조인해야 하는 경우 오류가 발생하기 쉽습니다.
      - 실수로 join을 못했다면?!

    - 해결책
       - 이전까지 서로 관계가 업는 runBlocking과 GlobalScope.launch에서 실행되는 코루틴이 구조적으로 관계 생성
       - structured concurrency
          - runBlocking에서 만든 코루틴 스코프에서 하면 됨
       - 따라서 탑레벨 코루틴을 만들지 말고, runBlocking 코루틴의 child 코루틴으로 만들면 구조적인 형태로 인해서 부모 코루틴이 기다려줌.
 */

fun main() = runBlocking {
    this.launch {
        delay(3000L)
        println("World!")
    }

    this.launch {
        delay(3000L)
        println("------- end ------")
    }

    println("Hello,")
}
