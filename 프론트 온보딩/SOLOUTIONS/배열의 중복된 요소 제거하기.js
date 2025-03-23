// [문제] 배열의 중복된 요소 제거하기

const arr = [1, 2, 3, 6, 7, 11, 2, 4, 22, 1, 9, 5, 7];

// 이 배열에서 중복된 요소를 제거하고 새로운 배열을 반환하는 함수를 작성해 보세요.
// [조건]
// 1. 중복된 요소가 제거된 배열을 반환해야 합니다.
// 2. 원본 배열은 수정하지 않아야 합니다.
// 3. 새로운 배열을 반환하는 함수를 작성하세요.

// 1. set을 이용한 중복제거
// set 객체는 중복을 허용하지 않는 구조이기 때문에 set객체에 배열을 넣어 중복을 제거한 후 다시 배열로 변환
const solution1 = (arr) => [...new Set(arr)];

// 2. filter를 이용한 중복제거
// 배열을 순회하며 filter로 조건에 충족하는 요소만 반환
// (indexOf는 처음 발견한 인덱스를 반환하기 때문에 값이 다른 경우 중복이라 판단)
const solution2 = (arr) => arr.filter((v, i) => arr.indexOf(v) === i);

// 3. reduce를 이용한 중복제거
// reduce로 배열을 순회하며 include함수를 통해 결과값(r)에 없는 요소만 추가한 후 배열을 반환
const solution3 = (arr) => arr.reduce((r, v) => {
    if (!r.includes(v)) r.push(v);
    return r;
}, []);

// 4. 새로운 배열에 인덱스로 유무 체크하여 중복제거
// exists 배열을 생성하고 arr을 순회하며 값을 exists에 인덱스로 사용하여 유무를 체크한 후,
// reduce로 exists를 순회하며 true인 index만 배열에 담아서 반환
const solution4 = (arr) => {
    const exists = new Array(Math.max(...arr));

    arr.forEach(v => exists[v] = true);

    return exists.reduce((r, v, i) => {
        if (v) r.push(i);
        return r;
    }, []);
}
