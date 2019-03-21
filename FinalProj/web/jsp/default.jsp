<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="../error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Musico</title>

</head>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.welcome" var="welcom_message"/>
<fmt:message bundle="${loc}" key="locale.button.eng" var="button_eng"/>
<fmt:message bundle="${loc}" key="locale.button.ru" var="button_ru"/>

<body>

<c:if test="${fn:length(role) eq 0  }">
    <div align="right">
        <a style="text-decoration:none;float: right" href="controller?command=go_to_auth">Click to auth in system</a>
    </div>
</c:if>

<c:if test="${role eq 'user'}">
    <form action="${pageContext.request.contextPath}/controller?command=log_out" method="post">
        <button style="color: red">Log out</button>
    </form>
    <p style="color:red;"> User: ${user}</p>
</c:if>

<c:if test="${role eq 'admin'}">
    <form action="${pageContext.request.contextPath}/controller?command=log_out" method="post">
        <button style="color: red">Log out</button>
    </form>
    <p style="color:red;"> Admin : ${user}</p>
</c:if>



Res : ${url}

<div align="left">
    <form action="${pageContext.request.contextPath}/controller?command=find_news" method="post">
    <input name="tag" type="text" placeholder="Search news...">
    <input type="submit" value="Search">
</form>
</div>


<h1 align="center" style="margin-left: 15vh"><a href="controller?command=go_to_index" style="text-decoration: none">Musico</a>
</h1>


<div style="width: 100vh;">
    <div style="width:33vh;float:right;">
        <p style="overflow:auto;height: 100vh; width: 50vh; ">
        <table border="3">
            <tr>
                <td width="150">Title</td>
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

        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <td><a href="controller?command=go_to_index&page=${currentPage - 1}">Previous</a></td>
            </c:if>

            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="controller?command=go_to_index&page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <c:if test="${currentPage lt noOfPages}">
                <td><a href="controller?command=go_to_index&page=${currentPage + 1}">Next</a></td>
            </c:if>
        </div>
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

<div>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="ru">
        <input type="submit" name="${button_ru}" value="${button_ru}"/>
    </form>

    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="en">
        <input type="submit" name="${button_eng}" value="${button_eng}"/>
    </form>
</div>






</body>
</html>