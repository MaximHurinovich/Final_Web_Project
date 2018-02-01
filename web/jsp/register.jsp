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
<%@include file="../layout/high_menu_bar.jsp"%>
<h4><b><fmt:message key="jsp.register.registration" /> </b></h4>
<hr/>
<div class="signForm">
<form name="signForm" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="signup"/>
    <fmt:message key="jsp.register.firstname" /><br/>
    <label class="first_name">
        <input type="text" name="first_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$"/>
    </label>
    <i><fmt:message key="jsp.register.namereg" /></i>
    <br/> <fmt:message key="jsp.register.secondname"/><br/>
    <label class="second_name">
        <input type="text" name="second_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$"/>
    </label>
    <br/>
    <fmt:message key="jsp.register.username"/><br/>
    <label class="username">
        <input type="text" name="username" value="" required pattern="^(\S){1,25}$"/>
    </label>
    <i><fmt:message key="jsp.register.usernamereg" /></i>
    <br/>
    <fmt:message key="jsp.register.password"/><br/>
    <label class="password">
        <input type="password" name="password" value="" required pattern="^(\S){8,25}$"/>
    </label>
    <i><fmt:message key="jsp.register.passwordreg" /></i>
    <br/>
    <fmt:message key="jsp.register.email"/><br/>
    <label class="e-mail">
        <input type="text" name="e-mail" value="" required pattern="^(\S){1,25}@(\w){2,8}.(\w){2,6}$"/>
    </label>
    <i><fmt:message key="jsp.register.emailreg" /></i>
    <br/>
    <hr/>
    <fmt:message key="jsp.register.please"/><br/><br/>
    <fmt:message key="jsp.regitster.number"/><br/>
    <label class="card_number">
        <input type="text" name="card_number" value="" required pattern="^\d{4}$"/>
    </label>
    <i><fmt:message key="jsp.register.cardreg" /></i>
    <br/> <fmt:message key="jsp.register.cardpass"/><br/>
    <label class="card_password">
        <input type="password" name="card_password" value="" required pattern="^\d{4}$"/>
    </label><br/>
    <input type="submit" value="<fmt:message key = "jsp.login.submit" />">
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
</div>
</body>
</html>
