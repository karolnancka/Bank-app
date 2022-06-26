<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register new currency</title>
</head>
<body>
<form:form method="post" modelAttribute="currency">
    <div>
        <label for="currency">Currency</label>
        <form:input path="currency" id="currency" type="text"/>
        <form:errors path="currency"/>
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
