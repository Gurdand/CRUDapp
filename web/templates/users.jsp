<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: Змей
  Date: 03.09.2019
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <section>
        <h2>Create new user</h2>
        <form method="post" action="/users/add">
            <input name="name" placeholder="Name" >
            <input type="number" name="age" placeholder="Age">
            <button type="submit">Create</button>
        </form>
    </section>

    <section>
        <p><c:out value="${message}"/></p>
    </section>

    <section>
        <h2>All users</h2>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Age</th>
            </tr>
            <%--@elvariable id="users" type="java.util.List"--%>
            <c:forEach var="user" items="${users}">
                <tr>
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
