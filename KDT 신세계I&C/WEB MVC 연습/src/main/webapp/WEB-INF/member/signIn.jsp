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
    <title>SIGN IN</title>
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

        h1 {
            margin-bottom: 30px;
        }

        form {
            margin-top: 30px;
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
    <div class="header">
        <h1>Sgin In</h1>
        <c:if test="${param.result == 'error'}">
            <span style="color: red">
                <h3>Invalid username or password</h3>
            </span>
        </c:if>
    </div>
    <form action="/signIn.do" method="post">
        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
        </div>
        <div class="buttons">
            <button type="submit" class="btn btn-primary">로그인 하기</button>
        </div>
    </form>
</div>

</body>
</html>
