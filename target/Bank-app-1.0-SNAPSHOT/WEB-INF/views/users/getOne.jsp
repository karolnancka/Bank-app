<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>

<table border="1">
    <thead>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    </thead>
    <tbody>

    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.account.number}"/></td>
    </tr>

    </tbody>
</table><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>
</body>
</html>