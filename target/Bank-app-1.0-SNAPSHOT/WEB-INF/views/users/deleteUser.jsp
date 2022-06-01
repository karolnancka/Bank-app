<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 01.06.2022
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>

<body>
<c:url var="delete_url" value="/users/delete"/>
<form:checkboxes items="users" path="user" method="post" modelAttribute="user" action="${delete_url}">
<table border="1">

    <thead>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
        </tr>
    </c:forEach>
    </tbody>
    <form:hidden path="id"/>
    <form:input path="firstName"/>
    <form:errors path="firstName"/><br/>
    <form:input path="lastName"/>
    <form:errors path="lastName"/><br/>
    <form:input path="email"/>
    <form:errors path="email"/><br/>
    <form:input path="password"/>
    <form:errors path="password"/><br/>
    <input type="submit" value="Delete">
</form:checkboxes>


</table>

</body>

</body>
</html>
