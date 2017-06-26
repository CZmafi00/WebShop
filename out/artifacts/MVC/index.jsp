<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 13.5.2017.
  Time: 11:26
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
    <script src="scripts/index.js"></script>
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

    <div id="container_home">
        <!-- Image header -->
        <div class="w3-display-container w3-container">
            <img src="content/images/home.jpg" alt="Home" style="width:100%">
            <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">
                <h1 class="w3-jumbo w3-hide-small">Tržnica piva</h1>
                <h1 class="w3-hide-large w3-hide-medium">Tržnica piva</h1>
                <h1 class="w3-hide-small">Kvalitetne domaće i uvozne pive</h1>
                <p><a href="#izdvojeno" class="w3-button w3-black w3-padding-large w3-large">SHOP NOW</a></p>
            </div>
        </div>

        <div class="w3-container w3-text-grey" id="izdvojeno">
            <p>Izdvojeno iz ponude: ${requestScope.get('model').getSpecials().size()} items</p>
        </div>

        <!-- Product grid -->
        <c:forEach items="${requestScope.get('model').getSpecials()}" var="item">
            <div class="w3-col l3 s6  margin-top-60px">
                <div class="w3-container height-400-px">
                    <div style="height: 300px; overflow: hidden;">
                        <img src="content/images/beer/${item.getImgUrl()}" style="width:100%;">
                    </div>
                    <p>${item.getName()}<br><b>${item.getPrice()} kn</b></p>
                    <p>
                        <input type="number" min="0" class="w3-input w3-border w3-round" value="1"/>
                        <a id="${item.getProductId()}" class="w3-button w3-block w3-black btn-add-product" style="margin-top:2px;"> + <span
                                class="fa fa-shopping-cart"></span> </a>
                    </p>
                </div>
            </div>
        </c:forEach>

    </div>

    <c:forEach items="${requestScope.get('model').getContainers()}" var="entry">
        <div id="${entry.key}" class="w3-row w3-grayscale" style="display: none;">
            <c:forEach items="${entry.value}" var="item">
                <div class="w3-col l3 s6 margin-top-60px">
                    <div class="w3-container height-400-px">
                        <div style="height: 300px; overflow: hidden;">
                            <img src="content/images/beer/${item.getImgUrl()}" style="width:100%;">
                        </div>
                        <p>${item.getName()}<br><b>${item.getPrice()} kn</b></p>
                        <p>
                            <input type="number" min="0" class="w3-input w3-border w3-round" value="1"/>
                            <a id="${item.getProductId()}" class="w3-button w3-block w3-black btn-add-product" style="margin-top:2px;"> + <span
                                    class="fa fa-shopping-cart"></span> </a>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:forEach>

</div>

<div class="width-100 height-80px clear-both">
</div>

<%@include file="jsp_elements/_modal.jsp" %>

<script>
    homeManager.init();
</script>

</body>
</html>
