<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register new user</title>
</head>
<body>
<form:form method="post" modelAttribute="operation">
    <div>
        <label>Operation Type</label>
        <select name="category" >
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="fromAccount">Account From</label>
        <form:input path="fromAccount" id="fromAccount" type="text"/>
        <form:errors path="fromAccount"/>
    </div>
    <div>
        <label for="toAccount">Account To</label>
        <form:input path="toAccount" id="toAccount" type="text"/>
        <form:errors path="toAccount"/>
    </div>
    <div>
        <label for="amount">Amount</label>
        <form:input path="amount" id="amount" type="text"/>
        <form:errors path="amount"/>
    </div>
    <div>
        <label for="currencyFrom">Base Currency</label>
        <form:input path="currencyFrom" id="currencyFrom" type="text"/>
        <form:errors path="currencyFrom"/>
    </div>
    <div>
        <label for="currencyTo">To Currency</label>
        <form:input path="currencyTo" id="currencyTo" type="text"/>
        <form:errors path="currencyTo"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form>
</body>
</html>
