<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: Змей
  Date: 03.09.2019
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" language="java" import="java.sql.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Title</title>
</head>
<body>
    <section>
        <h2>Create new user</h2>
        <form method="post" action="/users/create">
            <input name="name" placeholder="Name" >
            <input type="number" name="age" placeholder="Age">
            <button type="submit">Create</button>
        </form>
    </section>

    <section>
        <p><c:out value="${message}"/></p>
        <a href="/">На главную</a>
    </section>

    <section>
        <h2>Update user</h2>
        <form method="post" action="/users/update">
            <input type="number" name="id" placeholder="ID">
            <input name="name" placeholder="New name">
            <input type="number" name="age" placeholder="New age">
            <button type="submit">Update</button>
        </form>
    </section>

    <section>
        <h2>All users</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
            </tr>
            <%--@elvariable id="users" type="java.util.List"--%>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                        <form method="post" action="/users/delete">
                            <button type="submit" name="id" value="${user.id}" >Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>


</body>
</html>
