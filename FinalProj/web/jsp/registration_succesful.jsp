<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<h3 style="color: blueviolet ">Welcome ${user} to Musico </h3>


<button><a href="${pageContext.request.contextPath}/controller?command=go_to_index&page=1"
           style="text-decoration: none ">Back to main page</a></button>


</body>
</html>
