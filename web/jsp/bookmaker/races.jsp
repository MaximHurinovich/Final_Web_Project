

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
    <title>Book races</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/bookmaker_header.jsp"%>
<aside class="placeholder">
</aside>
<h2><b><fmt:message key="jsp.main.title" bundle="${var}"/></b></h2>

<br/>
<table class="maintable">
    <tr>
        <th><fmt:message key="jsp.main.cards" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.dates" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.horses" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${bookraces}" var="race" varStatus="status">
        <tr>
            <td>${race.card}</td>
            <td>${race.date}</td>
            <td>
                <table class="horsestable">
                    <c:forEach items="${race.horses}" var="horse" varStatus="status">
                        <tr>
                            <td>${horse.name}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <form name="runRaceForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                    <input type="hidden" name="command" value="bookrace"/>
                    <input type="hidden" name="race_id" value="${race.id}">
                    <input type="submit" value="<fmt:message key="jsp.booker.race" bundle="${var}"/>"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table><br/>
${bookMessage}<br/>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
