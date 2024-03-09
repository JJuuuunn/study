<%--
  Created by IntelliJ IDEA.
  User: jjuuuunn
  Date: 3/9/24
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Member - ADD</title>
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
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Member - ADD</h1>
    </div>
    <form action="/member/addMember.do" method="post">
        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" name="password" value="${member.password()}">
        </div>
        <div class="form-group">
            <label for="passwordCheck">비밀번호 확인</label>
            <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" onchange="pwCheck()">
            <div id="passwordCheckMessage" style="color: red;"></div>
        </div>
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
        </div>
        <div class="buttons">
            <button type="submit" class="btn btn-primary" disabled>가입하기</button>
            <button type="reset" class="btn btn-primary">다시입력</button>
        </div>
    </form>
</div>

</body>
</html>
