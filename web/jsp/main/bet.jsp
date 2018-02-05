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
    <title>Make a Bet</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>
</head>
<body>

<%@include file="/layout/header.jsp" %>
<aside class="placeholder"></aside>
<h3><b><fmt:message key="jsp.makebet.title" bundle="${var}"/></b></h3>
<br/>
<form name="makebet" method="post" action="${pageContext.request.contextPath}/jsp/controller">
<input type="hidden" name="command" value="acceptbet"/>
    <table>

        <tr>
            <td><b><fmt:message key="jsp.main.cards" bundle="${var}"/> </b></td>
            <td>${betrace.card}</td>
        </tr>
        <tr>
            <td><fmt:message key="jsp.main.dates" bundle="${var}"/> </td>
            <td>${betrace.date}</td>
        </tr>
        <tr>
            <td><fmt:message key="jsp.makebet.choose" bundle="${var}"/> </td>
            <td>
                    <c:forEach items="${betrace.horses}" var="horses" varStatus="status">
                        <input type="radio" name="name" id="name" value="${horses.name}"/>
                        <label for="name">${horses.name}</label>
                        <br/>
                    </c:forEach>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="jsp.makebet.typeodd" bundle="${var}"/></td>
            <td>
                <input type="radio" name="type" id="type1" value="win"/>
                <label for="type1"><fmt:message key="jsp.main.win" bundle="${var}"/></label>
                <br/>
                <input type="radio" name="type" id="type2" value="top3"/>
                <label for="type2"><fmt:message key="jsp.main.top3" bundle="${var}"/></label>
                <br/>
                <input type="radio" name="type" id="type3" value="outsider"/>
                <label for="type3"><fmt:message key="jsp.main.outsider" bundle="${var}"/></label>
                <br/>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="jsp.makebet.amount" bundle="${var}"/>
            </td>
            <td>
                <label>
                    <input type="text" name="amount" value=""/>
                </label>
            </td>
        </tr>
    </table>
    <input type="submit" value="<fmt:message key="jsp.button.submit" bundle="${var}"/>">
</form>
<b><span style="color: antiquewhite"><fmt:message key="jsp.accountinfo.currentamount" bundle="${var}"/> ${userfull.amount}</span> </b>
${errorBet}<br/>
<%@include file="/layout/footer.jsp" %>
</body>
</html>
