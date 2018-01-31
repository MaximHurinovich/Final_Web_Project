

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
<h2><b>Available races:</b></h2>
<br/>
<table class="maintable">
<tr>
    <th>Cards</th>
    <th>Dates</th>
    <th>Horses</th>
    <th>Make A Bet</th>
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
            <form name="betForm" method="POST" action="controller">
                <input type="hidden" name="command" value="login"/>
                <input type="hidden" name="race_id" value=${race.getDate()}/>
                <input type="submit" value="" class="button"/>
            </form>
        </td>
        </tr>
</c:forEach>
    </table>

<%@include file="/layout/footer.jsp"%>
    </body>
    </html>
