

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


<%@include file="/layout/admin_header.jsp"%>
<aside class="placeholder">
</aside>
<table class="upperadmin">
    <tr>
        <td><h2><b>Available races:</b></h2></td>
        <td>
            <input class="addRace" type="button" value="Add race" onClick='location.href="/jsp/admin/add_race.jsp"'>
        </td>
    </tr>
</table>

<br/>
<table class="maintable">
    <tr>
        <th>Cards</th>
        <th>Dates</th>
        <th>Horses</th>
    </tr>
    <c:forEach items="${racesList}" var="race" varStatus="status">
        <tr>
            <td>${race.getCard()}</td>
            <td>${race.getDate()}</td>
            <td>
                <table class="horsestable">
                    <tr>
                        <th>Name</th>
                        <th>Coeffitients</th>
                    </tr>
                    <c:forEach items="${race.getHorses()}" var="horse" varStatus="status">
                        <tr>
                            <td>${horse.getName()}</td>
                            <td>
                                <table class="betstable">
                                    <tr>
                                        <th>Winner</th>
                                        <th>Top 3</th>
                                        <th>Outsider</th>
                                    </tr>
                                    <tr>
                                        <td>${horse.getBets().getWinner()}</td>
                                        <td>${horse.getBets().getTop3()}</td>
                                        <td>${horse.getBets().getOutsider()}</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </td>
            <td>
                <form name="runRaceForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="runrace"/>
                    <input type="hidden" name="race_id" value="${race.getId()}">
                    <input type="submit" value="Run"/>
                </form>
            </td>
            <td>
                <form name="deleteRaceForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="deleterace"/>
                    <input type="hidden" name="race_id" value="${race.getId()}">
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table><br/>
${adminMessage}<br/>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
