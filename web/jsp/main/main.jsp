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


<div class="header">
    <header>
        <a href="#home" class="logo" data-scroll><img src="<c:url value="/resources/img/logo.jpg"/>" width="160" height="55"> </a>
        <nav class="nav-collapse">

            <ul>
                <li>    <%@include file="../../layout/high_menu_bar.jsp"%>
                </li>
                <li><span style="color: white; padding-top: 10px"><b>Welcome</b>,<br/> ${user}!</span></li>
                <li class="menu-item active"><a href="#home" data-scroll>Home</a></li>
                <li class="menu-item"><a href="#about" data-scroll>Upcoming</a></li>
                <li class="menu-item"><a href="#projects" data-scroll>Results</a></li>
                <li class="menu-item"><a href="#blog" data-scroll>Statistic</a></li>
                <li class="menu-item"><a href="http://www.google.com" target="_blank">About</a></li>
                <li class="menu-item"><a href="#" target="_blank">Account</a></li>
                <li class="menu-item">
                    <a href="<c:url value="/jsp/controller?command=logout"/>">Log out</a>
                </li>
            </ul>
        </nav>
    </header>

</div>
<aside class="placeholder">
</aside>
<h2><b>Today's races:</b></h2>
<br/>
<table class="maintable">
    <tr>
        <th>Cards</th>
        <th>Races</th>
    </tr>
    <c:forEach items="${racesList}" var="race" varStatus="status">
        <tr>
            <td>${race.card}</td>
            <td>${race.race}</td>
        </tr>
    </c:forEach>
</table>

<footer class="footer">
    All rights reserved. 2018(c) Gurinovich M.A.
</footer>
</body>
</html>
