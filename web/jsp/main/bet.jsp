<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 31.01.2018
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make Bet</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>
</head>
<body>

<%@include file="/layout/header.jsp" %>
<aside class="placeholder"></aside>
<h3><b>Make bet:</b></h3>
<br/>
<form name="makebet" method="post" action="${pageContext.request.contextPath}/jsp/controller">
<input type="hidden" name="command" value="acceptbet"/>
    <table>

        <tr>
            <td><b>Card: </b></td>
            <td>${betrace.getCard()}</td>
        </tr>
        <tr>
            <td>Date: </td>
            <td>${betrace.getDate()}</td>
        </tr>
        <tr>
            <td>Choose horse: </td>
            <td>
                    <c:forEach items="${betrace.getHorses()}" var="horses" varStatus="status">
                        <input type="radio" name="name" id="name" value="${horses.getName()}"/>
                        <label for="name">${horses.getName()}</label>
                        <br/>
                    </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Choosy type of odd</td>
            <td>
                <input type="radio" name="type" id="type1" value="win"/>
                <label for="type1">Win</label>
                <br/>
                <input type="radio" name="type" id="type2" value="top3"/>
                <label for="type2">Top 3</label>
                <br/>
                <input type="radio" name="type" id="type3" value="outsider"/>
                <label for="type3">Outsider</label>
                <br/>
            </td>
        </tr>
        <tr>
            <td>
                Amount:
            </td>
            <td>
                <label>
                    <input type="text" name="amount" value=""/>
                </label>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit">
</form>
<b><span style="color: antiquewhite">Current amount: ${userfull.getAmount()}</span> </b>
${errorBet}<br/>
<%@include file="/layout/footer.jsp" %>
</body>
</html>
