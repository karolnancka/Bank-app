<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register new Category</title>
</head>
<body>
<form:form method="post" autocomplete="off" modelAttribute="category">
    <div>
        <label for="category">Category</label>
        <form:input path="name" id="category" type="text"/>
        <form:errors path="name"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form>
</body>
</html>
