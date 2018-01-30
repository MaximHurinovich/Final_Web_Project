<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 27.12.2017
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<%@include file="../layout/high_menu_bar.jsp"%>
<h4><b><fmt:message key="jsp.register.registration" /> </b></h4>
<hr/>
<form name="signForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signup"/>
    <fmt:message key="jsp.register.firstname" /><br/>
    <label>
        <input type="text" name="first_name" value=""/>
    </label>
    <i><fmt:message key="jsp.register.namereg" /></i>
    <br/> <fmt:message key="jsp.register.secondname"/><br/>
    <label>
        <input type="text" name="second_name" value=""/>
    </label>
    <br/>
    <fmt:message key="jsp.register.username"/><br/>
    <label>
        <input type="text" name="username" value=""/>
    </label>
    <i><fmt:message key="jsp.register.usernamereg" /></i>
    <br/>
    <fmt:message key="jsp.register.password"/><br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <i><fmt:message key="jsp.register.passwordreg" /></i>
    <br/>
    <fmt:message key="jsp.register.email"/><br/>
    <label>
        <input type="text" name="e-mail" value=""/>
    </label>
    <i><fmt:message key="jsp.register.emailreg" /></i>
    <br/>
    <hr/>
    <fmt:message key="jsp.register.please"/><br/><br/>
    <fmt:message key="jsp.regitster.number"/><br/>
    <label>
        <input type="text" name="card_number" value=""/>
    </label>
    <i><fmt:message key="jsp.register.cardreg" /></i>
    <br/> <fmt:message key="jsp.register.cardpass"/><br/>
    <label>
        <input type="password" name="card_password" value=""/>
    </label><br/>
    <input type="submit" value="<fmt:message key = "jsp.login.submit" />">
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
</body>
</html>
