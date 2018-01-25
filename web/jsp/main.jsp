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
        .placeholder{
            height: 250px;
            margin: 55px 0px;
        }

        body, div,
        h1, h2, h3, h4, h5, h6,
        p, blockquote, pre, dl, dt, dd, ol, ul, li, hr,
        fieldset, form, label, legend, th, td,
        article, aside, figure, footer, header, hgroup, menu, nav, section,
        summary, hgroup {
            margin: 0;
            padding: 0;
            border: 0;
        }

        a:active,
        a:hover {
            outline: 0;
        }


        /* ------------------------------------------
          RESPONSIVE NAV STYLES
        --------------------------------------------- */

        .nav-collapse ul {
            margin: 0;
            padding: 0;
            width: 100%;
            display: block;
            list-style: none;
        }

        .nav-collapse li {
            width: 100%;
            display: block;
        }

        .js .nav-collapse {
            clip: rect(0 0 0 0);
            max-height: 0;
            position: absolute;
            display: block;
            overflow: hidden;
            zoom: 1;
        }

        @media screen and (min-width: 40em) {
            .js .nav-collapse {
                position: relative;
            }
        }


        /* ------------------------------------------
          DEMO STYLES
        --------------------------------------------- */

        body {
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
            text-size-adjust: 100%;
            color: #37302a;
            background-color: antiquewhite;
            font: normal 100%/1.4 sans-serif;
        }


        h1 {
            margin-bottom: .5em;
        }

        p {
            width: 90%;
            margin: 0 auto;
        }


        /* ------------------------------------------
          FIXED HEADER
        --------------------------------------------- */

        header {
            background: #f4421a;
            position: fixed;
            z-index: 3;
            width: 100%;
            left: 0;
            top: 0;
        }

        .logo {
            -webkit-tap-highlight-color: rgba(0,0,0,0);
            text-decoration: none;
            font-weight: bold;
            line-height: 55px;
            padding: 0;
            color: #fff;
            float: left;
        }

        @media screen and (min-width: 40em) {
        }


        /* ------------------------------------------
          NAVIGATION STYLES
        --------------------------------------------- */

        .nav-collapse,
        .nav-collapse * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        .nav-collapse,
        .nav-collapse ul {
            list-style: none;
            width: 100%;
            float: left;
        }

        @media screen and (min-width: 40em) {
            .nav-collapse {
                float: right;
                width: auto;
            }
        }

        .nav-collapse li {
            float: left;
            width: 100%;
        }

        @media screen and (min-width: 40em) {
            .nav-collapse li {
                width: auto;
            }
        }

        .nav-collapse a {
            -webkit-tap-highlight-color: rgba(0,0,0,0);
            border-top: 1px solid white;
            text-decoration: none;
            background: #f4421a;
            padding: 0.7em 1em;
            color: #fff;
            width: 100%;
            float: left;
        }

        .nav-collapse a:active,
        .nav-collapse .active a {
            background: #b73214;
        }

        @media screen and (min-width: 40em) {
            .nav-collapse a {
                border-left: 1px solid white;
                padding: 1.02em 2em;
                text-align: center;
                border-top: 0;
                float: left;
                margin: 0;
            }
        }

        .nav-collapse ul ul a {
            background: #ca3716;
            padding-left: 2em;
        }

        @media screen and (min-width: 40em) {
            .nav-collapse ul ul a {
                display: none;
            }
        }


        /* ------------------------------------------
          NAV TOGGLE STYLES
        --------------------------------------------- */

        @font-face {

            font-weight: normal;
            font-style: normal;
        }

        .footer{
            height: 30px;
            background-color: #f4421a;
        }
    </style>
</head>
<body>

<div class="header">
    <header>
        <a href="#home" class="logo" data-scroll><img src="<c:url value="/resources/img/logo.jpg"/>" width="160" height="55"> </a>
        <nav class="nav-collapse">

            <ul>
                <li>    <%@include file="../layout/high_menu_bar.jsp"%>
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
    <img src="<c:url value="/resources/img/header.jpg"/>" height="250" width="1500">
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
            <td>${theme.card}</td>
            <td>${theme.race}</td>
        </tr>
    </c:forEach>
</table>

<footer class="footer">
    All rights reserved. 2018(c) Gurinovich M.A.
</footer>
</body>
</html>
