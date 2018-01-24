<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Error Page</title>
</head>

<body>
<%@include file="high_menu_bar.jsp"%>
<fmt:message key="jsp.error.requestfrom" /> ${pageContext.errorData.requestURI} <fmt:message key="jsp.error.failed" /> <br/>
<fmt:message key="jsp.error.name" /> ${pageContext.errorData.servletName} <br/>
<fmt:message key="jsp.error.status" /> ${pageContext.errorData.statusCode} <br/>
<fmt:message key="jsp.error.exception" /> ${pageContext.errorData.throwable}

</body>
</html>
