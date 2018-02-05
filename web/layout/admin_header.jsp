<%--
  Created by IntelliJ IDEA.
  User: mukha
  Date: 30.01.2018
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<body>
<div class="header">
    <header>
        <a href="${pageContext.request.contextPath}/jsp/admin/main.jsp" class="logo" data-scroll><img src="<c:url value="/resources/img/logo.jpg"/>" width="160"
                                                                                                     height="55"> </a>
        <nav class="nav-collapse">

            <ul>
                <li>
                    <%@include file="../layout/i18n_bar.jsp" %>
                </li>
                <li><span style="color: white; padding-top: 10px"><b><fmt:message key="jsp.header.welcome" bundle="${var}"/></b>,<br/> ${user}!</span></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/admin/main.jsp" data-scroll><fmt:message key="jsp.header.home" bundle="${var}"/></a></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/admin/users.jsp" data-scroll><fmt:message key="jsp.header.users" bundle="${var}"/></a></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/controller?command=adminbets" data-scroll><fmt:message key="jsp.header.bets" bundle="${var}"/></a></li>
                <li class="menu-item"><a href="<c:url value="/jsp/admin/account_info.jsp"/>" data-scroll><fmt:message key="jsp.header.account" bundle="${var}"/></a></li>
                <li class="menu-item">
                    <a href="<c:url value="/jsp/controller?command=logout"/>"><fmt:message key="jsp.header.logout" bundle="${var}"/></a>
                </li>
            </ul>
        </nav>
    </header>
    <input type="hidden" name="usefull" value=${userfull}>
</div>
</body>
</html>
