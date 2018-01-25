<%-- Created by IntelliJ IDEA. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <html>
    <head>
        <title>Index</title>
    </head>
    <body>
    <%@include file="/layout/high_menu_bar.jsp"%>
    <a href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key = "jsp.index.login" /></a>
    <br/>
    <a href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key = "jsp.login.signup" /></a>
    </body>
    </html>
