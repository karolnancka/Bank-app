<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register new Commission</title>
</head>
<body>
<form:form method="post" modelAttribute="commission">
    <div>
        <label for="commission">Commission Currency</label>
        <form:input path="commissionName" id="commission" type="text"/>
        <form:errors path="commissionName"/>
    </div>
    <div>
        <label for="commission">Commission Rate</label>
        <form:input path="commissionRate" id="commission" type="text"/>
        <form:errors path="commissionRate"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form>
</body>
</html>
