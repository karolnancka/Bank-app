<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register new user</title>
</head>
<body>
<form:form method="post" modelAttribute="user">
    <div>
        <label for="firstName">First Name</label>
        <form:input path="firstName" id="firstName" type="text"/>
        <form:errors path="firstName"/>
    </div>
    <div>
        <label for="lastName">LastName</label>
        <form:input path="lastName" id="lastName" type="text"/>
        <form:errors path="lastName"/>
    </div>
    <div>
        <label for="email">Email</label>
        <form:input path="email" id="email" type="email"/>
        <form:errors path="email"/>
    </div>
    <div>
        <label for="password">Password</label>
        <form:input path="password" id="password" type="password"/>
        <form:errors path="password"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>
</body>
</html>
