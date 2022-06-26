<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Operations</title>
</head>
<body>
<table border="1">
    <thead>
    <th>ID</th>
    <th>Operation Type</th>
    <th>From Account</th>
    <th>To Account</th>
    <th>Amount</th>
    <th>Currency from</th>
    <th>Currency to</th>
    <th>Commission USD</th>
    <th>Commission EUR</th>
    <th>Commission PLN</th>
    </thead>
    <tbody>
    <c:forEach items="${operations}" var="operation">
        <tr>
            <td><c:out value="${operation.id}"/></td>
            <td><c:out value="${operation.operationType.name}"/></td>
            <td><c:out value="${operation.fromAccount.number}"/></td>
            <td><c:out value="${operation.toAccount.number}"/></td>
            <td><c:out value="${operation.amount}"/></td>
            <td><c:out value="${operation.currencyFrom.currency}"/></td>
            <td><c:out value="${operation.currencyTo.currency}"/></td>
            <td><c:out value="${operation.commissionUSD}"/></td>
            <td><c:out value="${operation.commissionEUR}"/></td>
            <td><c:out value="${operation.commissionPLN}"/></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<div>
    <a class="block"  href="<%= request.getContextPath()%>../accounts/all">All accounts balance</a>
</div><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>
</body>
</html>
