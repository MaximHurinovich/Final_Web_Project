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
    <title>Add money</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>
</head>
<body>
<%@include file="/layout/header.jsp" %>
<aside class="placeholder"></aside>
<h3><b>Add money</b></h3>
<br/>

<form  method="post" action="/jsp/controller">
    <table>
        <tr>
            <td>
                <table>
                    <tr>
                        <td>
                            <b>Current amount:</b>
                        </td>
                        <td>
                            <%out.print(((User) request.getSession().getAttribute("userfull")).getAmount());%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Card amount:</b>
                        </td>
                        <td>
                            ${cardAmount}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Add to account</b>
                        </td>
                        <td>
                            <label>
                                <input type="text" name="money" value="">
                            </label>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                    <input type="hidden" name="command" value="acceptadd">
                    <input type="submit" value="Add">

            </td>
            <td>

        </tr>
    </table>

</form><br/>
<form class="edit" method="post" action="/jsp/controller">
    <table>
        <tr>
            <td>
                <b>Return from account:</b>
            </td>
            <td>
                <label>
                    <input type="text" name="retmoney" value="">
                </label>
            </td>
            <td>
                <input type="hidden" name="command" value="returnmoney">
                <input type="submit" value="Return">
            </td>
        </tr>
    </table>
</form>

${addMessage}<br/>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
