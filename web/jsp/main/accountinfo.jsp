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
    <title>Account info</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>
</head>
<body>
<%@include file="/layout/header.jsp" %>
<aside class="placeholder"></aside>
<h3><b>Account info</b></h3>
<br/>

<table>
    <tr>
        <td>
            <table>
                <tr>
                    <td>
                        <b>First name:</b>
                    </td>
                    <td>
                        <%out.print(((User) request.getSession().getAttribute("userfull")).getFirstName());%>
                    </td>
                <tr/>
                <tr>
                    <td>
                        <b>Second name:</b>
                    </td>
                    <td>
                        <%out.print(((User) request.getSession().getAttribute("userfull")).getSecondName());%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Email:</b>
                    </td>
                    <td>
                        <%out.print(((User) request.getSession().getAttribute("userfull")).getEmail());%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Card number:</b>
                    </td>
                    <td>
                        <%out.print(((User) request.getSession().getAttribute("userfull")).getCardNumber());%>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <form class="edit" method="post" action="/jsp/controller">
                <input type="hidden" name="command" value="edit">
                <input type="submit" value="Edit">
            </form>
        </td>
    </tr>
</table>
<hr/>
<span style="color: antiquewhite"> <b>Current amount:</b>
    <%out.print(((User) request.getSession().getAttribute("userfull")).getAmount());%>$</span><br/>
<form class="add" method="post" action="/jsp/controller">
    <input type="hidden" name="command" value="addmoney">
    <input type="submit" value="Money Transfer">
</form>

<%@include file="/layout/footer.jsp" %>
</body>
</html>
