
# Cancellation and timeouts
- https://kotlinlang.org/docs/cancellation-and-timeouts.html

# Job
 - cancel()

# Cancellation is cooperative
 - 방법1: 주기적으로 suspend fun 호출 - delay(), yeild()...
 - 방법2: 취소 상태를 확인 - isActive()

# Timout
 - withTimeout
   - 일정 시간 후 Exception 발생
 - withTimeoutOrNull