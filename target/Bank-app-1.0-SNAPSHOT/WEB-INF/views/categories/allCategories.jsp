<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<table border="1">
    <thead>
    <th>ID</th>
    <th>Category Name</th>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td><c:out value="${category.id}"/></td>
            <td><c:out value="${category.name}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
