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
        <form:select itemValue="id" itemLabel="name" path="operationType.id" items="${categories}"/>
    </div>
    <div>
        <label>Account From</label>
        <form:select itemValue="id" itemLabel="id" path="fromAccount.id" items="${accounts}"/>
    </div>
    <div>
        <label>Account To</label>
        <form:select itemValue="id" itemLabel="id" path="toAccount.id" items="${accounts}"/>
    </div>

    <div>
        <label for="amount">Amount</label>
        <form:input path="amount" id="amount" type="number"/>
        <form:errors path="amount"/>
    </div>
    <div>

        <label>Base Currency</label>
        <form:select itemValue="id" itemLabel="currency" path="currencyFrom.id" items="${currencies}"/>
    </div>
    <div>
        <label>To Currency</label>
        <form:select itemValue="id" itemLabel="currency" path="currencyTo.id" items="${currencies}"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form>
</body>
</html>
