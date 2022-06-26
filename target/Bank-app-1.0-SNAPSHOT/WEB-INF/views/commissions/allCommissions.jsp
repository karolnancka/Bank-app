<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Commissions</title>
</head>
<body>
<table border="1">
    <thead>
    <th>ID</th>
    <th>Currency</th>
    <th>Rate</th>

    </thead>
    <tbody>
    <c:forEach items="${commissions}" var="commission">
        <tr>
            <td><c:out value="${commission.id}"/></td>
            <td><c:out value="${commission.commissionName}"/></td>
            <td><c:out value="${commission.commissionRate}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>
</body>
</html>
