// [문제] flattenArray 구현하기
// 1. 중첩 배열(nested array)을 포함할 수 있는 배열을 평탄화(flatten)하는 함수를 작성하세요.
//  즉, 모든 중첩 배열의 요소를 하나의 배열로 만들어 반환하는 함수입니다.
// 2. 함수 이름은 flattenArray로 합니다.
// 3. 매개변수는 하나의 배열입니다.
// 4. 반환값은 모든 요소가 평탄화된 배열이어야 합니다.
// 5. 재귀 함수를 활용하여 중첩 깊이에 상관없이 평탄화를 수행하세요.

// 예제:
flattenArray([1, [2, [3, 4], 5], 6]); // [1, 2, 3, 4, 5, 6] 
flattenArray([[[1, 2], 3], 4]);       // [1, 2, 3, 4] 

// 주의 : 내장 메서드 Array.prototype.flat()은 사용하지 않습니다.


// 1. 재귀 함수를 사용하여 중첩 배열을 평탄화하는 함수를 구현합니다.
const flattenArray = (arr) => {
    let result = [];

    arr.forEach(x => {
        if (Array.isArray(x)) {
            result = result.concat(flattenArray(x));
        } else {
            result.push(x);
        }
    });

    return result;
}
