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
    <title>My Bets</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/header.jsp"%>
<aside class="placeholder">
</aside>
<h2><b>Active odds:</b></h2>
<br/>
<c:if test="${activeOdds.isEmpty()}">
    <span style="color: antiquewhite"><b>Odds not found.</b></span>
</c:if>
<c:if test="${!activeOdds.isEmpty()}">
<table class="maintable">


        <tr>
            <th>Date of race</th>
            <th>Horse Name</th>
            <th>Type of bet</th>
            <th>Amount</th>
            <th></th>
        </tr>
    <c:forEach items="${activeOdds}" var="odd" varStatus="status">
        <tr>
            <td>${odd.getDate()}</td>
            <td>${odd.getHorseName()}</td>
            <td>
                ${odd.getType()}
            </td>
            <td>
                ${odd.getOdd()}
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>
<br/>
<h2><b>Results:</b></h2>
<c:if test="${nonactiveOdds.isEmpty()}">
    <span style="color: antiquewhite"><b>Odds not found.</b></span>
</c:if>
<c:if test="${!nonactiveOdds.isEmpty()}">
<table class="maintable">
    <tr>
        <th>Date of race</th>
        <th>Horse Name</th>
        <th>Type of bet</th>
        <th>Amount</th>
        <th>Result</th>
        <th></th>
    </tr>
    <c:forEach items="${nonactiveOdds}" var="odd" varStatus="status">
        <tr>
            <td>${odd.getDate()}</td>
            <td>${odd.getHorseName()}</td>
            <td>
                    ${odd.getType()}
            </td>
            <td>
                    ${odd.getOdd()}
            </td>
            <td>
                <c:if test="${odd.isSuccess()}">
                    <span style="color: limegreen"><b>SUCCESS</b></span>
                </c:if>
                <c:if test="${!odd.isSuccess()}">
                    <span style="color: red"><b>FAIL</b></span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
