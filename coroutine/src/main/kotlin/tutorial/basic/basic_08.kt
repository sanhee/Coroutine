package tutorial.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    Global coroutines은 데몬 스레드와 비슷하다.

     - 코루틴이 살아있다고 해서, 프로세스를 살리진 못함.
       - 프로새스가 끝나면, 코루틴도 끝난다.
 */

fun main() = runBlocking {
    GlobalScope.launch {
        repeat(1000){
            i-> println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    delay(1300L)  // 코루틴은 1000개 있지만.. 3번까지만 출력되고 프로세스 종료
}
