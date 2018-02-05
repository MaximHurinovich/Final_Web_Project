

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
    <title>Horse Racing Bets</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/header.jsp"%>
<aside class="placeholder">
</aside>
${betMessage}
<h2><b><fmt:message key="jsp.main.title" bundle="${var}"/></b></h2>
<br/>
<table class="maintable">
<tr>
    <th><fmt:message key="jsp.main.cards" bundle="${var}"/></th>
    <th><fmt:message key="jsp.main.dates" bundle="${var}"/></th>
    <th><fmt:message key="jsp.main.horses" bundle="${var}"/></th>
    <th><fmt:message key="jsp.main.makebet" bundle="${var}"/></th>
</tr>
<c:forEach items="${racesList}" var="race" varStatus="status">
    <tr>
    <td>${race.card}</td>
    <td>${race.date}</td>
    <td>
    <table class="horsestable">
    <tr>
    <th><fmt:message key="jsp.main.name" bundle="${var}"/></th>
    <th><fmt:message key="jsp.main.coeffs" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${race.horses}" var="horse" varStatus="status">
        <tr>
            <td>${horse.name}</td>
            <td>
                <table class="betstable">
                    <tr>
                        <th><fmt:message key="jsp.main.win" bundle="${var}"/></th>
                        <th><fmt:message key="jsp.main.top3" bundle="${var}"/></th>
                        <th><fmt:message key="jsp.main.outsider" bundle="${var}"/></th>
                    </tr>
                    <tr>
                        <td>${horse.bets.winner}</td>
                        <td>${horse.bets.top3}</td>
                        <td>${horse.bets.outsider}</td>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
        </table>

        </td>
        <td>
            <form name="betForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                <input type="hidden" name="command" value="bet"/>
                <input type="hidden" name="race_id" value="${race.id}">
                <input type="submit" value="" class="button"/>
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
