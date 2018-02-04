<%@ page import="by.gurinovich.webproject.entity.Bookmaker" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 18.12.2017
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account info</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/bookmaker_header.jsp"%>
<aside class="placeholder">
</aside>
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
                        <%out.print(((Bookmaker) request.getSession().getAttribute("userfull")).getFirstName());%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Second name:</b>
                    </td>
                    <td>
                        <%out.print(((Bookmaker) request.getSession().getAttribute("userfull")).getSecondName());%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Email:</b>
                    </td>
                    <td>
                        <%out.print(((Bookmaker) request.getSession().getAttribute("userfull")).getEmail());%>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <input class="login" type="button" value="Edit" onClick='location.href="/jsp/bookmaker/edit_account.jsp"'>

        </td>
    </tr>
</table>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
