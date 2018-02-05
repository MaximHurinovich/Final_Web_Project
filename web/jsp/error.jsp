<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Error Page</title>
</head>

<body>
<%@include file="../layout/i18n_bar.jsp"%>
<fmt:message key="jsp.error.requestfrom" bundle="${var}"/> ${pageContext.errorData.requestURI} <fmt:message key="jsp.error.failed" bundle="${var}"/> <br/>
<fmt:message key="jsp.error.name" bundle="${var}"/> ${pageContext.errorData.servletName} <br/>
<fmt:message key="jsp.error.status" bundle="${var}"/> ${pageContext.errorData.statusCode} <br/>
<fmt:message key="jsp.error.exception" bundle="${var}"/> ${pageContext.errorData.throwable}

</body>
</html>
