
<h2>쇼핑몰 기반 창고관리 시스템 - Spring Boot _ Mybatis</h2>

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

[여기를 클릭하시면 구현된 영상을 확인 가능합니다](https://youtu.be/VFlLRDUYnwE)


---
### 사용기술

#### 백엔드
![Spring Boot](https://img.shields.io/badge/spring%20boot-6DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-007396.svg?&style=for-the-badge&logo=java&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-02303A.svg?&style=for-the-badge&logo=gradle&logoColor=white)
![MyBatis](https://img.shields.io/badge/mybatis-BE3939.svg?&style=for-the-badge&logo=mybatis&logoColor=white)
![JPA](https://img.shields.io/badge/jpa-6DB33F.svg?&style=for-the-badge&logo=jpa&logoColor=white)

#### 프론트엔드
![Bootstrap](https://img.shields.io/badge/bootstrap-7952B3.svg?&style=for-the-badge&logo=bootstrap&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/thymeleaf-005F0F.svg?&style=for-the-badge&logo=thymeleaf&logoColor=white)
![AJAX](https://img.shields.io/badge/ajax-00A0DC.svg?&style=for-the-badge&logo=ajax&logoColor=white)
![jQuery](https://img.shields.io/badge/jquery-0769AD.svg?&style=for-the-badge&logo=jquery&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

#### 서버
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?&style=for-the-badge&logo=docker&logoColor=white)

#### 툴
![Figma](https://img.shields.io/badge/figma-F24E1E.svg?&style=for-the-badge&logo=figma&logoColor=white)
![Notion](https://img.shields.io/badge/notion-000000.svg?&style=for-the-badge&logo=notion&logoColor=white)
![ERD](https://img.shields.io/badge/erd-0177C1.svg?&style=for-the-badge&logo=data:image/svg+xml;base64,<base64_encoded_logo>)
![GitHub](https://img.shields.io/badge/github-181717.svg?&style=for-the-badge&logo=github&logoColor=white)
![Google Sheets](https://img.shields.io/badge/google%20sheets-34A853.svg?&style=for-the-badge&logo=google%20sheets&logoColor=white)



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
