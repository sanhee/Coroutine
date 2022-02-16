package tutorial.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking



/*
     - thread를 100_000개 만들면 부하가 생기고, 느리다.
     - 자칫하면 Out-Of-Memory error가 발생할 수도 있음
*/

/*
fun main() = runBlocking {
    repeat(100_000){
        thread{
            Thread.sleep(1000L)
            print(".")
        }
    }
}
*/


/*
    Coroutines ARE light-weight

    - 코루틴이 스레드보다 구조적으로 상당히 가볍고, 부하가 적음
     - 훨씬 빠르게 출력되는 것을 볼 수 있음
 */

fun main() = runBlocking {
    repeat(100_000){
        launch{
            delay(1000L)
            print(".")
        }
    }
}
