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
        <a href="${pageContext.request.contextPath}/jsp/main/main.jsp" class="logo" data-scroll><img src="<c:url value="/resources/img/logo.jpg"/>" width="160"
                                                                             height="55"> </a>
        <nav class="nav-collapse">

            <ul>
                <li>
                    <%@include file="../layout/high_menu_bar.jsp" %>
                </li>
                <li><span style="color: white; padding-top: 10px"><b>Welcome</b>,<br/> ${user}!</span></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/main/main.jsp" data-scroll>Home</a></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/controller?command=results" data-scroll>Results</a></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/controller?command=mybets" data-scroll>My Bets</a></li>
                <li class="menu-item"><a href="${pageContext.request.contextPath}/jsp/main/about.jsp" data-scroll>About</a>
                </li>
                <li class="menu-item"><a href="<c:url value="/jsp/main/accountinfo.jsp"/>" data-scroll>Account</a></li>
                <li class="menu-item">
                    <a href="<c:url value="/jsp/controller?command=logout"/>">Log out</a>
                </li>
            </ul>
        </nav>
    </header>
    <input type="hidden" name="usefull" value=${userfull}>
</div>
</body>
</html>
