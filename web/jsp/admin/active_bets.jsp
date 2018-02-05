

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
    <title>Active Bets</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/admin_header.jsp"%>
<aside class="placeholder">
</aside>

<br/>
<c:if test="${!betsList.isEmpty()}" >
<table class="maintable">
    <tr>
        <th><fmt:message key="jsp.register.username" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.dates" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.horses" bundle="${var}"/></th>
        <th><fmt:message key="jsp.mybets.type" bundle="${var}"/></th>
        <th><fmt:message key="jsp.mybets.amount" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${betsList}" var="bet" varStatus="status">
        <tr>
            <td>${bet.username}</td>
            <td>${bet.date}</td>
            <td>
                ${bet.horseId}
            </td>
            <td>${bet.type}</td>
            <td>${bet.odd}</td>
        </tr>
    </c:forEach>
</table><br/>
</c:if>
<c:if test="${betsList.isEmpty()}">
    <h3><b><fmt:message key="jsp.mybets.notfound" bundle="${var}"/></b></h3>
</c:if>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
