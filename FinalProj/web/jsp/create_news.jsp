<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News creation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="jsp/style/create_page.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-signin" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="create_news">
    <a href="index.jsp" >
        <img class="mb-4" src="jsp/img/moonwalk.svg"
             alt="logo" height="72" width="72">
    </a>
    <h1 class="h3 mb-3 font-weight-normal">Create interesting and hot content here</h1>
    <label class="sr-only">News title</label>
    <input type="text" name="title" class="form-control" placeholder="News title" required autofocus>
    <label class="sr-only">News tag</label>
    <input name="tag" type="text" class="form-control" placeholder="News tag" required>


    <div class="input-group">
        <div class="input-group-prepend">
            <span class="input-group-text">Content</span>
        </div>
        <textarea class="textarea" aria-label="With textarea" name="content" required></textarea>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Publish</button>
</form>
<div class="tags">
    <h4>List of available tags</h4>
    <ol class="list">
        <c:forEach items="${requestScope.tags}" var="tag">
            <ul>${tag}</ul>
        </c:forEach>
    </ol>
</div>
<p>${sessionScope.error}</p>
</body>
</html>