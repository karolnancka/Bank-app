<%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 26.06.2022
  Time: 08:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
      Welcome Home
    </title>
</head>
<body>
<h2>Choose an action:</h2>
<ul>
    <li><a class="block" href="<%= request.getContextPath()%>../users/register">Register new user</a></li>
    <li><a class="block" href="<%= request.getContextPath()%>../users/all">Show all users</a></li>
    <li><a class="block" href="<%= request.getContextPath()%>../accounts/all">Show all accounts</a></li>
    <li><a class="block" href="<%= request.getContextPath()%>../operations/operation">Make an operation</a></li>
    <li><a class="block" href="<%= request.getContextPath()%>../operations/all">Operation History</a></li>
    <li><a class="block" href="<%= request.getContextPath()%>../exchangeRate/all">Exchange Rates from Google</a></li>
</ul>

</body>
</html>
