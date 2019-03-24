<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration new user</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/style/registration.css">
</head>

<body>
<div class="container register-form">
    <form action="${pageContext.request.contextPath}/controller?command=registration" method="post">
        <input type="hidden" name="command" value="registration">
        <div class="note">
            <p>Input your credentional here</p>
        </div>

        <div class="form-content">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="text" class="form-control" name="login" placeholder="Your Login " value="" required autofocus>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="username"   placeholder="Your Username " value="" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Your Password " value="" required>
                    </div>
                </div>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
            <button><a href="controller?command=go_to_auth" style="text-decoration: none">Back</a></button>
        </div>
    </form>
    <h3>
        <p style="color: red"><c:out value="${requestScope.error}"/></p>
    </h3>
</div>





</body>
</html>