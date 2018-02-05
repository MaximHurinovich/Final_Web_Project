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
    <style type="text/css">
        body{
            background: url("/resources/img/background.jpg");
            background-size: cover;
        }
        .signForm{
            color: antiquewhite;
            font: normal 100%/1.4 sans-serif;
        }
        .signForm input:valid{
            border: 3px solid green;
        }
        .signForm input:focus:invalid{
            border: 3px solid red;
        }
    </style>
</head>
<body>
<%@include file="../layout/i18n_bar.jsp"%>
<h4><b><fmt:message key="jsp.register.registration" bundle="${var}"/> </b></h4>
<hr/>
<div class="signForm">
<form name="signForm" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="signup"/>
    <fmt:message key="jsp.register.firstname" bundle="${var}"/><br/>
    <label class="first_name">
        <input type="text" name="first_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$"/>
    </label>
    <i><fmt:message key="jsp.register.namereg" bundle="${var}"/></i>
    <br/> <fmt:message key="jsp.register.secondname" bundle="${var}"/><br/>
    <label class="second_name">
        <input type="text" name="second_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$"/>
    </label>
    <br/>
    <fmt:message key="jsp.register.username" bundle="${var}"/><br/>
    <label class="username">
        <input type="text" name="username" value="" required pattern="^(\S){1,25}$"/>
    </label>
    <i><fmt:message key="jsp.register.usernamereg" bundle="${var}"/></i>
    <br/>
    <fmt:message key="jsp.register.password" bundle="${var}"/><br/>
    <label class="password">
        <input type="password" name="password" value="" required pattern="^(\S){8,25}$"/>
    </label>
    <i><fmt:message key="jsp.register.passwordreg" bundle="${var}"/></i>
    <br/>
    <fmt:message key="jsp.register.email" bundle="${var}"/><br/>
    <label class="e-mail">
        <input type="text" name="e-mail" value="" required pattern="^(\S){1,25}@(\w){2,8}.(\w){2,6}$"/>
    </label>
    <i><fmt:message key="jsp.register.emailreg" bundle="${var}"/></i>
    <br/>
    <hr/>
    <fmt:message key="jsp.register.please" bundle="${var}"/><br/><br/>
    <fmt:message key="jsp.regitster.number" bundle="${var}"/><br/>
    <label class="card_number">
        <input type="text" name="card_number" value="" required pattern="^\d{4}$"/>
    </label>
    <i><fmt:message key="jsp.register.cardreg" bundle="${var}"/></i>
    <br/> <fmt:message key="jsp.register.cardpass" bundle="${var}"/><br/>
    <label class="card_password">
        <input type="password" name="card_password" value="" required pattern="^\d{4}$"/>
    </label><br/>
    <input type="submit" value="<fmt:message key = "jsp.login.submit" bundle="${var}"/>">
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
</div>
</body>
</html>
