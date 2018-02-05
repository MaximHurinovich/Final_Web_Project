

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
    <title>Book race</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
        input:focus:invalid{
            border: 3px solid red;
        }
        input:valid{
            border: 3px solid green;
        }
    </style>

</head>
<body>


<%@include file="/layout/bookmaker_header.jsp"%>
<aside class="placeholder">
</aside>
<h2><b><fmt:message key="jsp.booker.race" bundle="${var}"/></b></h2>

<br/>
<form name="runRaceForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
<table class="maintable">
    <tr>
        <th><fmt:message key="jsp.main.horses" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.win" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.top3" bundle="${var}"/></th>
        <th><fmt:message key="jsp.main.outsider" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${horsesList}" var="horse" varStatus="status">
        <tr>
            <td>${horse.name}</td>
            <td>
                <label>
                    <input type="text" name="winList" value="" required pattern="^(\d+\.?\d+)|\d+$">
                </label>
            </td>
            <td>
                <label>
                    <input type="text" name="top3List" value="" required pattern="^(\d+\.?\d+)|\d+$">
                </label>
            </td>
            <td>
                <label>
                    <input type="text" name="outsiderList" value="" required pattern="^(\d+\.?\d+)|\d+$">
                </label>
            </td>
            </tr>
    </c:forEach>
    <tr>
            <td>
                    <input type="hidden" name="command" value="setbooking"/>
                    <input type="submit" value="<fmt:message key="jsp.button.submit" bundle="${var}"/>"/>

            </td>
    </tr>
</table><br/>
</form>
${bookMessage}<br/>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
