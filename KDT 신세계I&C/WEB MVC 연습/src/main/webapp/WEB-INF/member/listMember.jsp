<%--
  Created by IntelliJ IDEA.
  User: jjuuuunn
  Date: 3/9/24
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Member - LIST</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <style>
        .container {
            position: relative;
            padding: 2rem 5rem;
            margin-top: 50px;
            border: 1px solid #e3e3e3;
            width: 1000px;
            font-size: 15px;
        }

        .logout-button {
            position: absolute; /* Add this line */
            top: 15px; /* Adjust as needed */
            right: 15px; /* Adjust as needed */
        }

        .header {
            text-align: center;
            margin-bottom: 20px;
        }

        .content {
            height: 800px
            overflow-x: hidden;
            overflow-y: auto; /* Add this line */
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #e3e3e3;
            padding: 10px;
            text-align: center;
        }

        .buttons {
            display: flex;
            justify-content: center;
        }

        .buttons .btn {
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="logout-button">
        <a href="/signIn.do">로그아웃</a>
    </div>
    <div class="header">
        <h1>Member - LIST</h1>
    </div>
    <div class="content">
        <table>
            <tr>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>이름</th>
                <th>이메일</th>
                <th>가입일</th>
                <th>수정일</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
            <c:forEach items="${memberList}" var="member">
                <tr>
                    <td><c:out value="${member.id()}"/></td>
                    <td><c:out value="${member.password()}"/></td>
                    <td><c:out value="${member.name()}"/></td>
                    <td><c:out value="${member.email()}"/></td>
                    <td><c:out value="${member.createdAt()}"/></td>
                    <td><c:out value="${member.modifiedAt()}"/></td>
                    <td><a href="/member/modMember.do?id=${member.id()}">수정</a></td>
                    <td><a href="/member/delMember.do?id=${member.id()}">삭제</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="buttons">
        <a href="/member/addMember.do" class="btn btn-primary">회원가입하기</a>
    </div>
</div>

</body>
</html>
