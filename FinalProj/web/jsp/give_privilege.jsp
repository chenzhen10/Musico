<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration panel</title>
</head>
<body>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Login</th>
        <th scope="col">Username</th>
        <th scope="col">Role id</th>
    </tr>
    </thead>
    <tbody>

    <c:set var="i" value="0"/>
    <c:forEach items="${sessionScope.users}" var="user">
        <tr>
            <th scope="row">${user.idUser}
            </th>
            <td>${user.login}</td>
            <td>${user.username}</td>
            <td>${user.idRole}</td>
        </tr>
        <c:set var="i" value="${i+1}"/>

    </c:forEach>
    </tbody>
</table>
<div class="text-center d-inline-block">
    <form action="${pageContext.request.contextPath}/controller?command=delete_user" method="post">
        <input type="text" name="id" placeholder="delete user">
        <button type="submit">Delete user</button>
    </form>
</div>
<div class="text-center d-inline-block">
    <form action="${pageContext.request.contextPath}/controller?command=give_administration_privilege" method="post">
        <input type="text" name="privilege" placeholder="give privilege">
        <button type="submit">
            Give administrator privilege
        </button>
    </form>
</div>
<p><strong>id : 1 - administrator ; 2 - user ;</strong></p> <br>

<button><a href="index.jsp">back</a></button>

<p style="color: red">${sessionScope.error}</p>


</body>
</html>
