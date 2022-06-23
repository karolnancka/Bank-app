<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Rates</title>
</head>
<body>
<table border="1">
    <thead>
    <th>ID</th>
    <th>USD to EUR</th>
    <th>EUR to USD</th>
    <th>USD to PLN</th>
    <th>PLN to USD</th>
    <th>EUR to PLN</th>
    <th>PLN to EUR</th>
    <th>DATE</th>
    </thead>
    <tbody>
    <c:forEach items="${rates}" var="rate">
        <tr>
            <td><c:out value="${rate.id}"/></td>
            <td><c:out value="${rate.usdToEur}"/></td>
            <td><c:out value="${rate.eurToUsd}"/></td>
            <td><c:out value="${rate.usdToPln}"/></td>
            <td><c:out value="${rate.plnToUsd}"/></td>
            <td><c:out value="${rate.eurToPln}"/></td>
            <td><c:out value="${rate.plnToEur}"/></td>
            <td><c:out value="${rate.date}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
