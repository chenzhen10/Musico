<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h1>Input your data here</h1>

<form action="${pageContext.request.contextPath}/auth" method="post">
    <input type="hidden" name="command" value="registration">
    Login :<input type="text" name="login">
    Password :<input type="password" name="password">
    Username : <input type="text" name="username">
    <input type="submit" value="login">
</form>

<h3>
    <c:out value="${requestScope.error}"/>
</h3>
<h4>Your login should contain latin symbols</h4>
<h3>
    <c:out value="${requestScope.prev_request}"/>
</h3>
<button><a href="auth?command=go_to_auth" style="text-decoration: none">back</a></button>
</body>
</html>
