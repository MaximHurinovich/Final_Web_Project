<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 27.12.2017
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<form name="signForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signup"/>
    First name:<br/>
    <label>
        <input type="text" name="first_name" value=""/>
    </label>
    <br/>Second name:<br/>
    <label>
        <input type="text" name="second_name" value=""/>
    </label><br/>
    E-mail:<br/>
    <label>
        <input type="text" name="e-mail" value=""/>
    </label><br/>
    Username:<br/>
    <label>
        <input type="text" name="username" value=""/>
    </label>
    <br/>
    Password:<br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/>


</form>
</body>
</html>
