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
<span style="color: antiquewhite; ">
<fmt:message key="jsp.about.home" bundle="${var}"/> <br/>
<fmt:message key="jsp.about.results" bundle="${var}"/><br/>
<fmt:message key="jsp.about.mybets" bundle="${var}"/><br/>
<fmt:message key="jsp.about.account" bundle="${var}"/><br/>
<fmt:message key="jsp.about.logout" bundle="${var}"/><br/>
    </span>
<hr/>
<br/>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
