package tutorial.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
    Extract function refactoring
     - launch {} 내 로직을 메소드로 분리
 */

fun main() = runBlocking {
    this.launch {
        myWorld()
    }
    println("Hello,")
}

// Suspend function 'delay' should be called
// only from a [coroutine] or another [suspend function]
// 일시 중단될 수 있음을 알려주는 키워드
suspend fun myWorld(){
    delay(3000L)
    println("World!")
}
