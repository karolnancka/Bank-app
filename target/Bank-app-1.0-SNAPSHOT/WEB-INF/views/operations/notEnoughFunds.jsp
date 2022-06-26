<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Operations</title>
</head>
<body>
<h2>Not enough funds for this operation</h2>

<div>
    <a class="block"  href="<%= request.getContextPath()%>../accounts/all">All accounts balance</a>
</div><br>

<div>
    <a class="block" href="<%= request.getContextPath()%>..//">Back to homepage</a>
</div>

</body>
</html>
