<%-- Created by IntelliJ IDEA. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <html>
    <head>
        <title>Index</title>
        <style type="text/css">
            body{
                background: url("/resources/img/background.jpg");
                background-size: cover;
            }
            .login{
                margin: 10% 50%;
                background-color: antiquewhite;
            }
            .register{
                margin: 10% 48%;
                background-color: antiquewhite;
            }

        </style>
    </head>
    <body>
    <%@include file="/layout/high_menu_bar.jsp"%>

    <label class="login">
    <a href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key = "jsp.index.login" /></a>
    </label>
    <br/>
    <label class="register">
    <a href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key = "jsp.login.signup" /></a>
    </label>
    </body>
    </html>
