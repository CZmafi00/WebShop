<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 14.5.2017.
  Time: 14:09
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

    <h1>Error</h1>
    <h2>Code: <span>${requestScope.get('error').getErrorCode()}</span></h2>
    <p>${requestScope.get('error').getMessage()}</p>
    <p><a href="/MVC/home" class="w3-button w3-black">< Home</a></p>

</div>

<script>

</script>

</body>
</html>

