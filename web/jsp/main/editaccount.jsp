<%@ page import="by.gurinovich.webproject.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 31.01.2018
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit account info</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>
</head>
<body>
<%@include file="/layout/header.jsp" %>
<aside class="placeholder"></aside>
<h3><b>Account info</b></h3>
<br/>

<form class="acceptedit" method="post" action="/jsp/controller">
<label style="background-color: antiquewhite">
    <input type="hidden" name="command" value="acceptedit">
    <b>First name:</b><br/>
    <input type="text" name="firstname" value="<%out.print(((User) request.getSession().getAttribute("userfull")).getFirstName());%>"><br/>
    <b>Second name:</b><br/>
    <input type="text" name="secondname" value="<%out.print(((User) request.getSession().getAttribute("userfull")).getSecondName());%>"><br/>
    <b>Email:</b><br/>
    <input type="text" name="email" value="<%out.print(((User) request.getSession().getAttribute("userfull")).getEmail());%>"><br/>
    <input type="hidden" name="cardnumber" value="<%out.print(((User) request.getSession().getAttribute("userfull")).getCardNumber());%>"><br/>
</label>
    <input type="submit" value="Submit">
</form><br/>
    ${editMessage}<br/>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
