// [문제] 디바운스 함수 만들기
// 1. 주어진 함수와 지연 시간을 받아서, 디바운스(debounce) 기능을 하는 함수를 작성하세요.
// 함수 이름은 debounce로 합니다.
// 2. 매개변수는 두 개입니다:
//  func: 호출될 함수
//  wait: 지연 시간 (밀리초 단위)
// 3. 반환값은, 주어진 wait 시간 동안 호출이 연속되면 마지막 호출만 실행하도록 하는 새로운 함수여야 합니다.

// 예시 사용법:
function sayHello() {
    console.log("Hello!");
}

const debouncedSayHello = debounce(sayHello, 1000);

debouncedSayHello();
debouncedSayHello();
debouncedSayHello();
// 위와 같이 연속 호출 시, 마지막 호출 후 1초 뒤에만 "Hello!"가 출력됩니다. 

// 1. setTimeout을 이용한 디바운스 함수
// setTimeout을 사용하여 wait 시간 후에 func를 실행하도록 구현
// 연속 호출 시 clearTimeout을 통해 이전 setTimeout을 취소
// map을 사용하여 각 함수별로 timeoutId를 저장
let timeoutIdMap = new Map();

const debounce = (func, wait) => {
    clearTimeout(timeoutIdMap.get(func));
    const timeoutId = setTimeout(() => {
        func();

        timeoutIdMap.delete(func);
    }, wait);

    timeoutIdMap.set(func, timeoutId);
}
