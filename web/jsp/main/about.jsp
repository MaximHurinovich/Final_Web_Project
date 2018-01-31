<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 30.01.2018
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
    <style type="text/css">
    <%@include file="/resources/css/index.css"%>
    </style>
</head>
<body>

<%@include file="/layout/header.jsp"%>
<aside class="placeholder"></aside>

<b>Home</b> - main page with all available races and bets.<br/>
<b>Results</b> - list of done races.<br/>
<b>Statistic</b> - list of bets of specific user.<br/>
<b>Account</b> - info and possible changes of account.<br/>
<b>Log out</b> - leave account. <br/>
<hr/>
<br/>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
