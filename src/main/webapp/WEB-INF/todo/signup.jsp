<%--
  Created by IntelliJ IDEA.
  User: devfrog
  Date: 2023/12/09
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tl"%>
<html>
<head>
    <title>Login</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            width: 100vw;
            min-height: 100vh;

            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 10%;
            background-color: #f6f4e8;
        }

        header {
            font-size: 72px;
            margin-bottom: 3rem;
            color: #1d3124;
        }

        section {
            width: 50%;

        }

        form {
            width: 100%;
            height: 75px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        form label {
            width: 100%;
            margin-top: 1rem;
            color: #1d3124;
        }

        input {
            margin-top: 0.5rem;
            width: 100%;
            outline: none;
            padding: 0.5rem;
            height: 40px;
            border: 1px solid lightslategray;
            border-radius: 4px;
        }

        input::placeholder {
            color: #dcdacf;
        }

        button {
            width: 30%;
            border: 1px solid lightslategray;

            border-radius: 4px;
            background-color: #e59560;
            color: white;
            font-size: 1.5rem;
            padding: .5rem;
            cursor: pointer;
            margin-top: 1.5rem;
        }

        .error_message {
            font-size: 0.8rem;
            color: red;
            margin-top: 0.5rem;
        }
    </style>
</head>
<body>
<header>Remember Me!</header>
<section>
    <form action="${pageContext.request.contextPath}/api/member" method="post">
        <label for="name">
            이름:
            <input type="text" id="name" name="name" placeholder="이름을 입력해주세요"/>
        </label>

        <label for="email">
            이메일:
            <input type="text" id="email" name="email" placeholder="이메일을 입력해주세요"/>
            <tl:if test="${param.error.equals('duplicate')}">
                <p class="error_message">중복된 이메일 입니다</p>
            </tl:if>
        </label>

        <label for="password">
            비밀번호:
            <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요"/>
        </label>

        <label for="passwordCheck">
            비밀번호 확인:
            <input type="password" id="passwordCheck" name="passwordCheck" placeholder="비밀번호를 입력해주세요"/>
        </label>

        <button>회원가입</button>
    </form>
</section>
</body>
</html>
