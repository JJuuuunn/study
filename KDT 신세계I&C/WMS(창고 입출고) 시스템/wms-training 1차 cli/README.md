
<h2>쇼핑몰 기반 창고관리 시스템(CLI방식)</h2>

<h3>프로젝트 목적</h3>
<ul>
<li>유저는 총관리자, 쇼핑몰관리자, 창고관리자, 주문고객 이렇게 4분류로 나뉜다.</li>
<li>주문고객이 상품을 주문하고 주문 상태에 따라 실시간으로 창고의 입출고 내역을 조회할 수 있다.</li>
<li>CLI방식으로 구현 후, Spring MVC 및 Mybatis적용해서 리펙토링한다. </li>
</ul>

---
<h3>팀원 소개</h3>

<table style="border-collapse: collapse; width: 100%;" border="1">
    <thead>
        <tr>
            <th><img src="https://avatars.githubusercontent.com/u/100821691?v=4" width="100px" alt="_"/></th>
            <th><img src="https://avatars.githubusercontent.com/u/148327627?s=64&v=4" width="100px" alt="_"/></th>
            <th><img src="https://avatars.githubusercontent.com/u/74845690?v=4" width="100px" alt="_"/></th>
            <th><img src="https://avatars.githubusercontent.com/u/79977544?v=4" width="100px" alt="_"/></th>
            <th><img src="https://avatars.githubusercontent.com/u/84374546?v=4" width="100px" alt="_"/></th>
            <th><img src="https://avatars.githubusercontent.com/u/152829358?s=64&v=4" width="100px" alt="_"/></th>
        </tr>
        <tr>
            <th style="width: 14%;">양성준</th>
            <th style="width: 14%;">최문석</th>
            <th style="width: 14%;">백정훈</th>
            <th style="width: 14%;">문지환</th>
            <th style="width: 14%;">이도엽</th>
            <th style="width: 14%;">이다혜</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>ERD설계, <br>GitHub 관리, <br>프로젝트 환경구성, <br>전체 개발관리</td>
            <td>창고 관리,<br> 통합재고 관리,<br> 유즈케이스 작성</td>
            <td>발주 관리</td>
            <td>상품 관리</td>
            <td>주문 관리</td>
            <td>배송 관리</td>
        </tr>
    </tbody>
</table>


---
<h3>주요기능</h3>

- 상품관리 : 상품 등록 및 조회 관련 기능 처리
- 발주관리 : 발주관리 및 발주 관련 기능 처리
- 배송관리 : 배송준비된 상품에 대한 배송 처리
- 주문관리 : 주문 관리 고객 주문 처리 및 배송 연계
- 창고관리 : 발주 상품 입고 및 주문 상품 출고 자동화 관리
- 입/출고관리 : 발주와 주문에 따른 실시간 입/출고관리 (Trigger 적용)
  - 각 입/출고 상태는 대기/취소/확정으로 나뉜다.
- 통합재고관리 : 상품의 입/출고상태에 따라 실시간으로 재고조회 기능 처리

---
### 시연 영상

[여기를 클릭하시면 구현된 영상을 확인 가능합니다](https://youtu.be/3EDp340JiCI?si=b-9qvMnLrfBoQ22_)


---
### 사용기술

- JAVA : 전체적인 백엔드 JAVA 객체지향으로 구현
- MySQL : 관계형 데이터베이스로 데이터 저장 및 관리
- JDBC : Java 표준 API를 사용하여 MySQL과 연결하고, 쿼리를 통해 CRUD (Create, Read, Update, Delete) 작업 수행

---
### 주요기능 
![image](https://github.com/ysJun0608/ssg-team2-sellpick/assets/100821691/f0669814-9edc-49db-83c9-6db3d72ebd0c)


---
### Class Diagram
![image](https://github.com/ysJun0608/ssg-team2-sellpick/assets/100821691/f9f85ddf-a7d1-46ac-813b-202dcf2ab279)


---
### ER Diagram
![image](https://github.com/ysJun0608/ssg-team2-sellpick/assets/100821691/8557fccb-2398-45ac-94c7-1f1841a82321)


---
### Usecase
![image](https://github.com/ysJun0608/ssg-team2-sellpick/assets/100821691/5046d7e9-5134-4493-80ae-b5bcc690f1eb)



---
