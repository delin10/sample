<%--
  Created by IntelliJ IDEA.
  User: delin
  Date: 2019-05-09
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script src="js/index.js"></script>
</head>
<body>
<form name="login-form" action="login" method="post" onsubmit="javascipt:return validate('login-form');">
    <label>用户名</label><input name="name" type="text"/>
    <label>密码</label><input name="password" type="password"/>
    <input type="submit" value="login"/>
</form>
</body>
</html>
