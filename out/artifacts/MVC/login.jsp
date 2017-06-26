<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 14.5.2017.
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<header>
    <title>Beer shop</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="jsp_elements/_bundles.jsp" %>
    <script src="scripts/login.js"></script>
</header>

<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<%@include file="jsp_elements/_side_bar_menu.jsp" %>

<!-- Top menu on small screens -->
<%@include file="jsp_elements/_top_menu_small_screen.jsp" %>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

    <!-- Push down content on small screens -->
    <div class="w3-hide-large" style="margin-top:83px"></div>

    <!-- Top header -->
    <%@include file="jsp_elements/_top_header.jsp" %>

    <c:choose>
        <c:when test="${sessionScope.get('user') != null}">

            <form class="w3-container" action="login" method="get">
                <input type="hidden" value="true" name="logout" />
                <label class="w3-text-grey">User: ${sessionScope.get('user').getEmail()}</label>
                <br/>
                <input type="submit" class="w3-button w3-black w3-block w3-margin" value="Sign out">
            </form>

        </c:when>
        <c:otherwise>
            <form class="w3-container" action="/MVC/login" method="post">

                <br/>
                <p>
                    <label class="w3-text-grey">Email</label>
                    <input id="email" class="w3-input w3-border w3-round" type="email" name="email" required>
                </p>
                <p>
                    <label class="w3-text-grey">Password</label>
                    <input id="password" class="w3-input w3-border w3-round" type="password" name="pass" required>
                </p>
                <br/>
                <div class="w3-col l6 s6 w3-padding-16">
                    <p></p>
                </div>
                <div class="w3-col l6 s6 w3-padding-16">
                    <input id="btn_login" type="submit" class="w3-button w3-black w3-block w3-margin" value="Sign in >">
                </div>

            </form>
        </c:otherwise>
    </c:choose>

</div>

<%@include file="jsp_elements/_modal.jsp" %>

<script>

    <c:choose>
        <c:when test="${requestScope.get('error') != null}">
            showModal("${requestScope.get('error').getMessage()}");
        </c:when>
    </c:choose>

    loginManager.init();
</script>

</body>
</html>
