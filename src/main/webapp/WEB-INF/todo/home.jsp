<%--
  Created by IntelliJ IDEA.
  User: devfrog
  Date: 2023/12/08
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tl"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>TODO</title>

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

    .input-form-wrapper {
      width: 100%;

    }

    .input-form-wrapper form {
      width: 100%;
      height: 75px;
      display: flex;
      align-items: center;
      justify-content: flex-start;
    }

    .input-form-wrapper input {
      width: 100%;
      outline: none;
      padding: 0.5rem;
      height: 40px;
      border: 1px solid lightslategray;
      border-top-left-radius: 4px;
      border-bottom-left-radius: 4px;
      border-right: none;
    }

    input::placeholder {
      color: #dcdacf;
    }
    .input-form-wrapper button {
      width: 10%;
      height: 40px;
      border: 1px solid lightslategray;

      border-top-right-radius: 4px;
      border-bottom-right-radius: 4px;
      background-color: #e59560;
      color: white;
      font-size: 1.5rem;
      cursor: pointer;
    }

    ul {
      margin-top: 1rem;
      width: 100%;
    }
    li {
      width: 100%;
      margin: 2.5rem 0;
      list-style: none;
      background-color: #bacec1;
      border-radius: 4px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem;
      color: #292929;
      font-size: 1.2rem;
    }

    li input[type=checkbox] {
      margin-right: 1rem;
      scale: 1.25;
      cursor: pointer;
    }

    li button {
      margin-left: 0.5rem;
      background-color: #1d3124;
      border: 1px solid lightslategray;
      color: white;
      border-radius: 4px;
      padding: 0.25rem;
      cursor: pointer;
    }

    li .dueDate {
      font-size: 0.8rem;
    }
  </style>
</head>
<body class="container">
<header>Remember Me!</header>
<section>
  <div class="input-form-wrapper">
    <form action="/api/todo" method="post">
      <input type="text" name="content" placeholder="기억해야할 일을 작성해주세요." />
      <button type="submit">+</button>
    </form>
  </div>
  <div>
    <ul>
      <tl:forEach items="${todos}" var="todo">
        <li>
          <span>
              <input class="doneCheckbox" onclick="updateDone(`${todo.id}`, `${todo.content}`, `${todo.done}`)" type="checkbox" ${todo.done ? "checked" : ""} name="done" readonly>
          </span>

          <span style="text-decoration: ${todo.done ? 'line-through' : ''}">${todo.content}</span>
          <span>

          <span class="dueDate">등록일: ${todo.dueDate}</span>
          <button onclick="deleteTodo(`${todo.id}`)">삭제</button>
          </span>

        </li>
      </tl:forEach>
    </ul>
  </div>
</section>
<script>
  const checkbox = document.querySelector(".doneCheckbox");
  const deleteTodo = (id) => {
    const answer = confirm("정말 삭제하시겠습니까?");
    if (!answer) return;
    fetch("/api/todo", {
      method: "DELETE",
      body: id
    }).then(() => {
      window.location.href="/";
    });
  }

  const updateDone = (id, content, done) => {
    const data = {
      id,
      content,
      done: done === "false"
    }
    fetch("/api/todo", {
      method: "PUT",
      body: JSON.stringify(data)
    }).then(() => {
      window.location.href="/";
    });
  }
</script>
</body>
</html>
