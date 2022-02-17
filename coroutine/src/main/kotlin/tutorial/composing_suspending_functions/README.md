
# Composing suspending functions
- https://kotlinlang.org/docs/composing-suspending-functions.html

- 코루틴 안에서 suspend func을 조합해서 원하는 형태로 사용할 수 있다는 것을 보여주는 예제


- Async to sequential

- async
  - 비동기를 동시에 사용할 수 있음
  - layz를 통해 실행을 늦출 수도 있음

- Structured concurrency
  - 스코프 안에서 코루틴을 실행시킨다면 cancel을 전파해서 모든 코루틴을 종료할 수 있음
  - 글로벌 스코프에서 따로 코루틴 실행시키는 건 추천하지 않음