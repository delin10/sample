<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="register" name="register-form" method="post" onsubmit="return validate('register-form');">
    <label title="用户名"/><input name="name" type="text"/>
    <label title="密码"/><input name="password" type="password"/>
    <input type="submit" value="register"/>
</form>
<a href="./login.jsp">back</a>
<script src="js/index.js"></script>
</body>
</html>