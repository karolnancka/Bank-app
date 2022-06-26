<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>

<table border="1">
    <thead>
    <th>ID</th>
    <th>Account Number</th>
    <th>Balance USD</th>
    <th>Balance EUR</th>
    <th>Balance PLN</th>
    </thead>
    <tbody>

    <tr>
        <td><c:out value="${account.id}"/></td>
        <td><c:out value="${account.number}"/></td>
        <td><c:out value="${account.balanceUSD}"/></td>
        <td><c:out value="${account.balanceEUR}"/></td>
        <td><c:out value="${account.balancePLN}"/></td>
    </tr>

    </tbody>
</table><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>
</body>
</html>