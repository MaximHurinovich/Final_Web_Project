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
<h2><b>Races results:</b></h2>
<br/>
<table class="maintable">
    <tr>
        <th>Cards</th>
        <th>Dates</th>
        <th>Horses</th>
    </tr>
    <c:forEach items="${resultList}" var="race" varStatus="status">
        <tr>
            <td>${race.getCard()}</td>
            <td>${race.getDate()}</td>
            <td>
                <table class="horsestable">
                    <tr>
                        <th>Place</th>
                        <th>Name</th>
                    </tr>
                    <c:forEach items="${race.getHorses()}" var="horse" varStatus="status">
                        <tr>
                            <td>${horse.getPlace()}</td>
                            <td>
                                ${horse.getName()}
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
