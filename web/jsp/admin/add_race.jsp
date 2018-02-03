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
    <title>Add Horse</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/admin_header.jsp" %>
<aside class="placeholder">
</aside>
${addRaceMessage}
<br/>
<form method="post" action="/jsp/controller">
    <input type="hidden" name="command" value="addhorse">
    <table>
        <tr>
            <th>Horse name:</th>
            <td>
                <label>
                    <input type="text" name="horsename" value="">
                </label>
            </td>
            <td>
                <input type="submit" value="Add horse">
            </td>

        </tr>
    </table>
</form>
<form method="post" action="/jsp/controller">
    <table class="maintable">
        <tr>
            <th>Card:</th>
            <td>
                <label>
                    <input type="text" name="card" value="">
                </label>
            </td>
        </tr>
        <tr>
            <th>Date:</th>
            <td>
                <label class="dateForm">
                    <input type="text" name="date" value="">
                </label>
            </td>
        </tr>
    </table>
    ${horseMessage}<br/>
    <input type="hidden" name="horses" value="${horsesList}">
    <input type="hidden" name="command" value="addrace">
    <input type="submit" value="Submit">
</form>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp" %>
</body>
</html>
