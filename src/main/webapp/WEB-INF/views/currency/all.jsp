<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Currencies</title>
</head>
<body>
<table border="1">
    <thead>
    <th>ID</th>
    <th>Currency</th>
    </thead>
    <tbody>
    <c:forEach items="${currencies}" var="currency">
        <tr>
            <td><c:out value="${currency.id}"/></td>
            <td><c:out value="${currency.currency}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>
</body>
</html>
