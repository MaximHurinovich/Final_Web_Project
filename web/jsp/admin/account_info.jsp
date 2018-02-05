<%@ page import="by.gurinovich.webproject.entity.Admin" %>

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
    <title>Account info</title>
    <style type="text/css">
        <%@include file="/resources/css/index.css"%>
    </style>

</head>
<body>


<%@include file="/layout/admin_header.jsp"%>
<aside class="placeholder">
</aside>
<h3><b><fmt:message key="jsp.accountinfo.title" bundle="${var}"/></b></h3>
<br/>

<table>
    <tr>
        <td>
            <table>
                <tr>
                    <td>
                        <b><fmt:message key="jsp.register.firstname" bundle="${var}"/></b>
                    </td>
                    <td>
                        <%out.print(((Admin) request.getSession().getAttribute("userfull")).getFirstName());%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="jsp.register.secondname" bundle="${var}"/></b>
                    </td>
                    <td>
                        <%out.print(((Admin) request.getSession().getAttribute("userfull")).getSecondName());%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="jsp.register.email" bundle="${var}"/></b>
                    </td>
                    <td>
                        <%out.print(((Admin) request.getSession().getAttribute("userfull")).getEmail());%>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <input class="login" type="button" value="<fmt:message key="jsp.button.edit" bundle="${var}"/>" onClick='location.href="/jsp/admin/edit_account.jsp"'>

        </td>
    </tr>
</table>
<aside class="placeholder">
</aside>
<%@include file="/layout/footer.jsp"%>
</body>
</html>
