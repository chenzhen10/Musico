<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="../error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Musico</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link rel="stylesheet" href="jsp/style/style.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.button.eng" var="button_eng"/>
<fmt:message bundle="${loc}" key="locale.button.ru" var="button_ru"/>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="col-5  search">
        <form action="${pageContext.request.contextPath}/controller?command=find_news" method="post"
              class="form-inline">
            <input name="tag" class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>


    <div class="col-5 img">
        <a href="index.jsp" class="navbar-brad">
            <img class="rotate img-fluid" src="jsp/img/moonwalk_white.png"
                 width="30" height="30" alt="logo">
            <strong class="t-logo">usico</strong>
        </a>


    </div>

    <c:if test="${fn:length(role) eq 0  }">
        <div class="col-2 d-flex justify-content-end align-items-center">
            <a href="controller?command=go_to_auth" class="btn btn-light btn-outline-warning">Sign up</a>
        </div>
    </c:if>

    <c:if test="${role eq 'user'}">
        <p class="user-info" style="color:#FFFFFF"> Welcome : ${user} </p>

        <form action="${pageContext.request.contextPath}/controller?command=log_out" method="post">
            <button class="btn btn-danger" style="margin-left: 55px;">Log out</button>
        </form>

    </c:if>

    <c:if test="${role eq 'admin'}">
        <img src="jsp/img/admin.png" alt="admin">
        <p class="user-info" style="color:#FFFFFF"> ${user}</p>
        <form action="${pageContext.request.contextPath}/controller?command=log_out" method="post">
            <button class="btn margin-btn btn-danger"> Log out</button>
        </form>
    </c:if>


</nav>


<main role="main" class="container">
    <div class="row">
        <div class="col-md-8 blog-main">
            <h3 class="pb-3 mb-4 font-italic border-bottom">
                News
            </h3>
            <p>
                <c:if test="${role eq 'admin'}">
                    <a href="jsp/create_news.jsp">Create News</a>
                </c:if>
            </p>

            <c:forEach items="${requestScope.news}" var="news">
                <div class="blog-post">
                    <h2 class="blog-post-title">${news.title}</h2>
                    <p class="blog-post-meta"><fmt:formatDate pattern="dd-MMMMM"
                                                              value="${news.added_date}"/>  ${tag} <a href="#">Tim</a>

                    </p>

                    <p>${news.content}</p>
                </div>
            </c:forEach>

            <c:forEach items="${requestScope.findedNews}" var="news">
                <div class="blog-post">
                    <h2 class="blog-post-title">${news.title}</h2>
                    <p class="blog-post-meta"><fmt:formatDate pattern="dd-MMMMM"
                                                              value="${news.added_date}"/> ${tag} <a href="#">Tim</a>


                    </p>
                    <p>${news.content}</p>
                </div>
            </c:forEach>

            <nav class="blog-pagination">
                <c:if test="${currentPage != 1}">
                    <a class="btn  btn-outline-secondary" href="controller?command=go_to_index&page=${currentPage - 1}">Older</a>
                </c:if>
                <c:if test="${currentPage lt noOfPages}">
                    <a class="btn btn-outline-primary " href="controller?command=go_to_index&page=${currentPage + 1}">Newer</a>
                </c:if>
            </nav>

        </div>

        <aside class="col-md-4 blog-sidebar">
            <div class="p-3 mb-3 bg-light rounded">
                <h4 class="font-italic">About</h4>
                <p class="mb-0">This is musical blog about <em>newest</em> and <em>hotest</em> news in the world .</p>
            </div>

            <div class="p-3">
                <h4 class="font-italic">Elsewhere</h4>
                <ol class="list-unstyled">
                    <li><a target="_blank" href="https://github.com/chenzhen10"><img class="git-logo"
                                                                                     src="jsp/img/Git.png" alt="GitHub"></a>
                    </li>
                    <li><a target="_blank" href="https://vk.com/id135402953"><img class="vk-logo" src="jsp/img/vk.png"
                                                                                  alt="VK"></a></li>
                </ol>
            </div>
        </aside>

    </div>

</main>

<c:if test="${done}"><!-- Chage it on normal modal window -->
<script type="text/javascript">
    alert("Welcome ${user} to Musico!" + "\n" +
        "You are was successfully registered in Muscio.We are glad to see you hope you will get some enjoy :) ");
</script>
</c:if>

<footer class="blog-footer bg-dark">
    <p>
        <a href="#">Back to top</a>
    </p>
    <div>

        <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru"><img class="flag"
                                                                                                     src="jsp/img/russia_flag.png"></a>


        <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en"><img class="flag"
                                                                                                     src="jsp/img/usa_flag.png"></a>

    </div>
</footer>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
</body>
</html>