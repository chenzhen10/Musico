<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authentication</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/jsp/style/authpage.css" rel="stylesheet">
</head>


<body>
<div class="form-signin">
    <form action="${pageContext.request.contextPath}/controller?command=authorization" method="post">
        <input type="hidden" name="command" value="authorization">
        <a href="controller?command=go_to_index&page=1" class="navbar-brad">
        <img class="mb-4" src="jsp/img/moonwalk.svg" alt="" width="72" height="72">
        </a>
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <input type="text" name="login" class="form-control" placeholder="Username" required autofocus>
        <input type="password" class="form-control" name="password" placeholder="Password" required>
        <button class="btn btn-lg btn btn-outline-primary btn-block" value="login" type="submit">Sign in</button>
        <p><strong>or</strong></p>
    </form>

    <a style="text-decoration: none"
       href="${pageContext.request.contextPath}/controller?command=go_to_registration_page">
        <button class="btn btn-lg btn btn-outline-primary btn-block">
            Sign up
        </button>
    </a>

    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru"><img class="flag"
                                                                                                 src="jsp/img/russia_flag.png"></a>


    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en"><img class="flag"
                                                                                                 src="jsp/img/usa_flag.png"></a>



    <h3>
        <p style="color: red"> ${requestScope.error}</p>
    </h3>

</div>


</body>


</html>