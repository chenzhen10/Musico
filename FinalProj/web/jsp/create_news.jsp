<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="create_news">
Title/File name :<input type="text" name="title">
Tag  : <input type="text" name="tag">

<textarea cols="50" rows="40" name="content">
</textarea>
<br>
<br>
<br>
<button type="submit">Create</button>
</form>

</body>
</html>
