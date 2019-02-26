<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="locale.welcome" var="welcom_message" />
<fmt:message bundle="${loc}" key="locale.button.eng" var="button_eng" />
<fmt:message bundle="${loc}" key="locale.button.ru" var="button_ru" />




<form action="${pageContext.request.contextPath}/auth" method="post">
    <input type="hidden" name="command" value="authorization">
    Login :<input type="text" name="login">
    Password :<input type="password" name="password">
    <input type="submit" value="login">
</form>

<h3>
    <c:out value="${requestScope.error}" />
</h3>

<h3>
    <c:out value="${welcom_message}" />
</h3>





<form action="${pageContext.request.contextPath}/auth" method="post" >
    <input type="hidden" name="command" value="change_locale">
    <input type="hidden" name="locale" value="ru">
    <input type="submit" name="${button_ru}" value="${button_ru}" />
</form>
<form action="${pageContext.request.contextPath}/auth" method="post">
    <input type="hidden" name="command" value="change_locale">
    <input type="hidden" name="locale" value="en">
    <input type="submit" name="${button_eng}" value="${button_eng}" />
</form>
<button><a href="auth?command=go_to_auth" style="text-decoration: none">back</a></button></body>
</html>
