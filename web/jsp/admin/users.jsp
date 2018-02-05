

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
<h3><b><fmt:message key="jsp.header.users" bundle="${var}"/></b></h3>
${adminMessage}
<br/>
<table class="maintable">
    <tr>
        <th><fmt:message key="jsp.register.username" bundle="${var}"/></th>
        <th><fmt:message key="jsp.register.firstname" bundle="${var}"/></th>
        <th><fmt:message key="jsp.accountinfo.secondname" bundle="${var}"/></th>
        <th><fmt:message key="jsp.register.email" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${usersList}" var="user" varStatus="status">
        <tr>
            <td>${user.username}</td>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.email}</td>
            <td>
                <form name="runRaceForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                    <input type="hidden" name="command" value="makeadmin"/>
                    <input type="hidden" name="username" value="${user.username}">
                    <input type="submit" value="<fmt:message key="jsp.button.admin" bundle="${var}"/>"/>
                </form>
            </td>
            <td>
                <form name="editRaceForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                    <input type="hidden" name="command" value="makebookmaker"/>
                    <input type="hidden" name="username" value="${user.username}">
                    <input type="submit" value="<fmt:message key="jsp.button.bookmaker" bundle="${var}"/>"/>
                </form>
            </td>
            <td>
                <form name="deleteRaceForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                    <input type="hidden" name="command" value="banuser"/>
                    <input type="hidden" name="username" value="${user.username}">
                    <input type="submit" value="<fmt:message key="jsp.button.ban" bundle="${var}"/>"/>
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
