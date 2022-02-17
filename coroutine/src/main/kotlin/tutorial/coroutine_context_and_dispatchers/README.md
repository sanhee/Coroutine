
# Coroutine context and dispatchers

> https://kotlinlang.org/docs/coroutine-context-and-dispatchers.html#coroutine-scope

- 코루틴은 실행될 때, 코루틴 컨텍스트에서 실행이 됨
- 코루틴 컨텍스트
  - job
  - dispatcher
    - 어떤 스레드에서 코루틴이 실행될지 결정할 요소
- Jumping between threads
- Children of a coroutine
- Parental responsibilities
- Coroutine scope