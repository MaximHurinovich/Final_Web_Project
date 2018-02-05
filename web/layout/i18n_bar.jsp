<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent" var="var"/>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<form name="localeForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="pagePath" value="${pageContext.request.requestURL}" />
    <input type="hidden" name="command" value="locale"/>
    <input type="submit" value= "<fmt:message key="label.changeLocale" bundle="${var}"/>" >
</form>
</body>
</html>
