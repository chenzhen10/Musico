
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>

<h1>Auth in system</h1>
<div style="display: flex">

    <button>
        <a style="text-decoration: none" href="${pageContext.request.contextPath}/auth?command=go_to_registration_page" >Sign in</a>
    </button>


    <button>
    <a style="text-decoration: none" href="${pageContext.request.contextPath}/auth?command=go_to_authentication_page" >Sign up</a>
    </button>
</div>

</body>
</html>
