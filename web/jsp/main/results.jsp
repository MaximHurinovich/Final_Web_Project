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
    <title>Results</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/header.jsp"%>
<aside class="placeholder">
</aside>
<h2><b><fmt:message key="jsp.mybets.results" bundle="${var}"/></b></h2>
<br/>
<table class="maintable">
    <tr>
        <th><fmt:message key="jsp.main.cards" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.dates" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.horses" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${resultList}" var="race" varStatus="status">
        <tr>
            <td>${race.card}</td>
            <td>${race.date}</td>
            <td>
                <table class="horsestable">
                    <tr>
                        <th><fmt:message key="jsp.main.place" bundle="${var}"/></th>
                        <th><fmt:message key="jsp.mybets.horse" bundle="${var}"/></th>
                    </tr>
                    <c:forEach items="${race.horses}" var="horse" varStatus="status">
                        <tr>
                            <td>${horse.place}</td>
                            <td>
                                ${horse.name}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
