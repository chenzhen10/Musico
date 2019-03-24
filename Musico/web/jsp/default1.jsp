<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="../error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Musico</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/style/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/style/delete_news.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.button.sign" var="button_sign"/>
<fmt:message bundle="${loc}" key="locale.button.out" var="button_out"/>



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
                 alt="logo">
            <strong class="t-logo">usico</strong>
        </a>
    </div>

    <c:if test="${fn:length(role) eq 0  }">
        <div class="col-2 d-flex justify-content-end align-items-center">
            <a href="controller?command=go_to_auth" class="btn btn-light btn-outline-warning">${button_sign}</a>
        </div>
    </c:if>

    <c:if test="${role eq 'User'}">
        <p class="user-info" style="color:#FFFFFF"> Welcome : ${user} </p>
        <form action="${pageContext.request.contextPath}/controller?command=log_out" method="post">
            <button class="btn btn-danger" style="margin-left: 55px;">${button_out}</button>
        </form>
    </c:if>

    <c:if test="${role eq 'Admin'}">
        <img src="jsp/img/admin.png" alt="admin">
        <p class="user-info" style="color:#FFFFFF"> ${user}</p>
        <form action="${pageContext.request.contextPath}/controller?command=log_out" method="post">
            <button class="btn margin-btn btn-danger"> ${button_out}</button>
        </form>
    </c:if>

</nav>


<main role="main" class="container">
    <div class="row">
        <div class="col-md-8 blog-main">

            <c:if test="${done}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>Welcome ${user}</strong> we hope you will enjoy our service :)
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if><!--Registration successful -->


            <c:set var="count" value="0"/>
            <c:forEach items="${requestScope.news}" var="news">
                <c:set var="path" value="${news.filePath}"/>
                <div class="blog-post">
                    <h2 class="blog-post-title">${news.title}</h2>
                    <p class="blog-post-meta">

                        <c:if test="${role eq 'Admin'}">
                            id : (${news.idNews})
                        </c:if>

                        <fmt:formatDate pattern="dd-MMMMM" value="${news.added_date}"/>
                            ${tag[count]} <a href="#">Tim</a>
                    </p>
                    <p>${news.content}</p>
                </div>

                <c:set var="count" value="${count + 1}"/>
            </c:forEach>


            <c:if test="${fn:length(empty_result) gt 0}">
                <div>
                    <h4>${empty_result}</h4>
                    <img class="empty-results" src="jsp/img/empty.png" alt="Empty results">
                </div>
            </c:if><!--Empty result -->

            <c:forEach items="${requestScope.findedNews}" var="news">
                <div class="blog-post">
                    <h2 class="blog-post-title">${news.title}</h2>
                    <p class="blog-post-meta">

                        <c:if test="${role eq 'Admin'}">
                            id : (${news.idNews})
                        </c:if>

                        <fmt:formatDate pattern="dd-MMMMM" value="${news.added_date}"/> ${tag[count]}
                        <a href="#">Tim</a>
                    </p>
                    <p>${news.content}</p>
                </div>
            </c:forEach>

            <nav class="blog-pagination">
                <c:if test="${currentPage != 1 and currentPage gt 0 }">
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
                <h4 class="font-italic">Contact with me</h4>
                <ol class="list-unstyled">
                    <li><a target="_blank" href="https://github.com/chenzhen10"><img class="git-logo"
                                                                                     src="jsp/img/git.png" alt="GitHub"></a>
                    </li>
                    <li><a target="_blank" href="https://vk.com/id135402953"><img class="vk-logo" src="jsp/img/vk.png"
                                                                                  alt="VK"></a></li>
                </ol>
            </div>
            <c:if test="${role eq 'Admin'}">
                <div class="border-bottom p-3">
                    <h3 class="font-italic">
                        Administration panel
                    </h3>
                    <ol class="list-unstyled">
                        <li><a href="${pageContext.request.contextPath}/controller?command=go_to_give_privilege">
                            Give administration privilege/Delete user</a></li>
                        <li><a href="${pageContext.request.contextPath}/controller?command=go_to_create_news">Create
                            News</a></li>
                        <li><a href="#myModal" class="trigger-btn" data-toggle="modal">Delete
                            News</a>
                            <p style="color: red">${sessionScope.error}</p>
                        </li>
                    </ol>
                </div>
            </c:if>
        </aside>
    </div>

</main>


<footer class="blog-footer bg-dark">
    <!-- modal window  to delete news -->
    <form action="${pageContext.request.contextPath}/controller?command=delete_news" method="post">
        <div id="myModal" class="modal fade" style="display: none;">
            <div class="modal-dialog modal-confirm">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="icon-box">
                            <i class="material-icons"></i>
                        </div>
                        <h4 class="modal-title">Are you sure?</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <p>Do you really want to delete  records? This process cannot be undone.</p>
                        <p style="font-family:'Rage Italic', serif"><strong>Input id of news which we should delete
                            :</strong>
                            <input type="text" name="delete_field">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
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


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>