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
    <title>My Bets</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/header.jsp"%>
<aside class="placeholder">
</aside>
<h2><b><fmt:message key="jsp.mybets.active" bundle="${var}"/></b></h2>
<br/>
<c:if test="${activeOdds.isEmpty()}">
    <span style="color: antiquewhite"><b><fmt:message key="jsp.mybets.notfound" bundle="${var}"/></b></span>
</c:if>
<c:if test="${!activeOdds.isEmpty()}">
<table class="maintable">


        <tr>
            <th><fmt:message key="jsp.main.dates" bundle="${var}"/></th>
            <th><fmt:message key="jsp.mybets.horse" bundle="${var}"/></th>
            <th><fmt:message key="jsp.mybets.type" bundle="${var}"/></th>
            <th><fmt:message key="jsp.mybets.amount" bundle="${var}"/></th>
            <th></th>
        </tr>
    <c:forEach items="${activeOdds}" var="odd" varStatus="status">
        <tr>
            <td>${odd.date}</td>
            <td>${odd.horseName}</td>
            <td>
                ${odd.type}
            </td>
            <td>
                ${odd.odd}
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>
<br/>
<h2><b><fmt:message key="jsp.mybets.results" bundle="${var}"/></b></h2>
<c:if test="${nonactiveOdds.isEmpty()}">
    <span style="color: antiquewhite"><b><fmt:message key="jsp.mybets.notfound" bundle="${var}"/></b></span>
</c:if>
<c:if test="${!nonactiveOdds.isEmpty()}">
<table class="maintable">
    <tr>
        <th><fmt:message key="jsp.main.dates" bundle="${var}"/></th>
        <th><fmt:message key="jsp.mybets.horse" bundle="${var}"/></th>
        <th><fmt:message key="jsp.mybets.type" bundle="${var}"/></th>
        <th><fmt:message key="jsp.mybets.amount" bundle="${var}"/></th>
        <th><fmt:message key="jsp.mybets.results" bundle="${var}"/></th>
        <th></th>
    </tr>
    <c:forEach items="${nonactiveOdds}" var="odd" varStatus="status">
        <tr>
            <td>${odd.date}</td>
            <td>${odd.horseName}</td>
            <td>
                    ${odd.type}
            </td>
            <td>
                    ${odd.odd}
            </td>
            <td>
                <c:if test="${odd.isSuccess()}">
                    <span style="color: limegreen"><b><fmt:message key="jsp.mybets.success" bundle="${var}"/></b></span>
                </c:if>
                <c:if test="${!odd.isSuccess()}">
                    <span style="color: red"><b><fmt:message key="jsp.mybets.fail" bundle="${var}"/></b></span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
