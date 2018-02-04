

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
<h2><b>Available races:</b></h2>

<br/>
<table class="maintable">
    <tr>
        <th>Cards</th>
        <th>Dates</th>
        <th>Horses</th>
    </tr>
    <c:forEach items="${bookraces}" var="race" varStatus="status">
        <tr>
            <td>${race.getCard()}</td>
            <td>${race.getDate()}</td>
            <td>
                <table class="horsestable">
                    <c:forEach items="${race.getHorses()}" var="horse" varStatus="status">
                        <tr>
                            <td>${horse.getName()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <form name="runRaceForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="bookrace"/>
                    <input type="hidden" name="race_id" value="${race.getId()}">
                    <input type="submit" value="Book race"/>
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
