<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <label>
        <input type="text" name="login" value=""/>
    </label>
    <br/>Password:<br/>
    <label>
        <input type="password" name="password" value=""/>
    </label><br/>
    <input type="submit" value="Log in"/>
    <br/> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
<hr/>
</body>
</html>