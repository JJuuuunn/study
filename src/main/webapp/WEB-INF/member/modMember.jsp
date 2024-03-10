<%--
  Created by IntelliJ IDEA.
  User: jjuuuunn
  Date: 3/9/24
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Member - LIST</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <style>
        .container {
            position: relative;
            padding: 2rem 5rem;
            margin-top: 50px;
            border: 1px solid #e3e3e3;
            width: 600px;
            font-size: 15px;
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

        .date span {
            display: inline-block;
            width: 49%;
        }
    </style>

    <script>
        function pwCheck() {
            if ($('#password').val() == $('#passwordCheck').val()) {
                $('#passwordCheckMessage').text('비밀번호가 일치합니다.').css('color', 'green');
                $('button[type="submit"]').prop('disabled', false);
            } else {
                $('#passwordCheckMessage').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
                $('button[type="submit"]').prop('disabled', true);
            }
        }
    </script>
<body>
<div class="container">
    <div class="header">
        <h1>Member - MODIFY</h1>
    </div>
    <div class="content">
        <form action="/member/modMember.do" method="post">
            <div class="form-group">
                <label for="id">아이디</label>
                <input type="text" class="form-control" id="id" name="id" value="<c:out value="${member.id()}" />" readonly>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" value="<c:out value="${member.password()}" />">
            </div>
            <div class="form-group">
                <label for="passwordCheck">비밀번호 확인</label>
                <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" onkeyup="pwCheck()">
                <div id="passwordCheckMessage" style="color: red;"></div>
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" class="form-control" id="name" name="name" value="<c:out value="${member.name()}" />">
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" class="form-control" id="email" name="email" value="<c:out value="${member.email()}" />">
            </div>
            <div class="form-group date">
                <span>가입 날짜 : <c:out value="${member.createdAt()}" /></span>
                <span>수정 날짜 : <c:out value="${member.modifiedAt()}" /></span>
            </div>
            <div class="buttons">
                <button type="submit" class="btn btn-primary" disabled>수정</button>
                <button type="button" class="btn btn-primary" onclick="location.href='/member/listMembers.do'">취소</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
