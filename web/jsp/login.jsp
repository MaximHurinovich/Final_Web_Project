<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 24.01.2018
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <%@include file="../layout/high_menu_bar.jsp"%>
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login"/>
        <fmt:message key = "jsp.login.login" /><br/>
        <label>
            <input type="text" name="login" value=""/>
        </label>
        <br/><fmt:message key = "jsp.login.password" /><br/>
        <label>
            <input type="password" name="password" value=""/>
        </label><br/>
            <input type="submit" value="<fmt:message key = "jsp.login.submit" />">
        <br/> ${successMessage} <br/> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
    </form>
<hr/>    <a href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key = "jsp.login.signup" /></a>
</body>
</html>
