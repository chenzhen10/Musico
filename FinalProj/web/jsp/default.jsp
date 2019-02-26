<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="../error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>

<div align="right">
    <a style="text-decoration:none;float: right" href="auth?command=go_to_auth">Click to auth in system</a>
</div>

<div align="left">
    <form action="${pageContext.request.contextPath}/auth?command=find_news" method="post">
        <input name="tag" type="text" placeholder="Search news...">
        <input type="submit" value="Search">

    </form>
</div>


<h1 align="center" style="margin-left: 15vh"><a href="auth?command=go_to_index" style="text-decoration: none">Musico</a></h1>

<div style="width: 100vh;">
    <div style="width:33vh;float:right;">
        <p style="overflow:auto;height: 100vh; width: 50vh; ">
        <table border="3">
            <tr>
                <td>Title</td>
                <td>Content</td>
                <td width="100">Date</td>
            </tr>
            <c:forEach items="${requestScope.news}" var="news">
                <tr>
                    <td>${news.title}</td>
                    <td>${news.content}</td>
                    <td>${news.added_date}</td>
                </tr>
            </c:forEach>

        <c:forEach items="${requestScope.findedNews}" var="news">
        <tr>
            <td>${news.title}</td>
            <td>${news.content}</td>
            <td>${news.added_date}</td>
        </tr>
        </c:forEach>
        </table>
        </p>
    </div>

    <div style="float: left;width:33vh; margin-left: 30px;margin-bottom: 33vh">
        <a href="" style="text-decoration: none"><strong>IListen</strong></a>
    </div>

    <div style="float: left;width:33vh; margin-left: 30px;margin-bottom: 33vh">
        <a href="" style="text-decoration: none"><strong>Music</strong></a>
    </div>

    <div style="float: left;width:33vh; margin-left: 30px;margin-bottom: 30vh;">
        <a href="" style="text-decoration: none"><strong>Premium</strong></a>
    </div>

</div>

<p style="float: left;padding-left: 30px ">All rights are reserved Copyright ©2000 - 2019</p>


</body>
</html>