<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 21.6.2017.
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Beer shop</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="jsp_elements/_bundles.jsp" %>
</head>
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

    <div class="w3-container">

        <h4>Narudžba uspješno zaprimljena</h4>

        <h5>Podaci o dostavi <b></b></h5>

        <p>
            <label><b>Ime i prezime</b></label>
            <input class="w3-input w3-border" disabled type="text" value="${requestScope.get('purchase').getFullName()}" /></p>
        </p>

        <p>
            <label><b>Email</b></label>
            <input class="w3-input w3-border" disabled type="text" value="${requestScope.get('purchase').getEmail()}" /></p>
        </p>

        <p>
            <label><b>Adresa</b></label>
            <input class="w3-input w3-border" disabled type="text" value="${requestScope.get('purchase').getFullAddress()}" /></p>
        </p>

        <p>
            <label><b>Ukupna cjena</b></label>
            <input class="w3-input w3-border" disabled type="text" value="${requestScope.get('purchase').getTotalPrice()} kn" /></p>
        </p>

        <h5>Stavke računa<b></b></h5>

        <table class="w3-table w3-striped">
            <thead>
                <tr>
                    <th>Naziv</th>
                    <th>Količina</th>
                    <th>Cjena</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.get('purchase').getItems()}" var="item">
                    <tr>
                        <td>${item.getProductName()}</td>
                        <td>${item.getQty()}</td>
                        <td>${item.getPrice()} kn</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="/MVC/home" class="w3-button w3-black">< Home</a></p>

    </div>

</div>

</body>
</html>
