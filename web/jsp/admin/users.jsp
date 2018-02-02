

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
    <title>Users</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/admin_header.jsp"%>
<aside class="placeholder">
</aside>
<h3><b>List of users:</b></h3>
${adminMessage}
<br/>
<table class="maintable">
    <tr>
        <th>Username</th>
        <th>First name</th>
        <th>Second name</th>
        <th>E-mail</th>
    </tr>
    <c:forEach items="${usersList}" var="user" varStatus="status">
        <tr>
            <td>${user.getUsername()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getSecondName()}</td>
            <td>${user.getEmail()}</td>
            <td>
                <form name="runRaceForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="makeadmin"/>
                    <input type="hidden" name="username" value="${user.getUsername()}">
                    <input type="submit" value="Make admin"/>
                </form>
            </td>
            <td>
                <form name="editRaceForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="makebookmaker"/>
                    <input type="hidden" name="username" value="${user.getUsername()}">
                    <input type="submit" value="Make bookmaker"/>
                </form>
            </td>
            <td>
                <form name="deleteRaceForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="banuser"/>
                    <input type="hidden" name="username" value="${user.getUsername()}">
                    <input type="submit" value="Ban user"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
