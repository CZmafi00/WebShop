<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 13.5.2017.
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="w3-container w3-xlarge">
    <p class="w3-right">
        <c:choose>
            <c:when test="${sessionScope.get('user').getRole() == 'Admin'}">
                <a href="/MVC/history" class="w3-button w3-padding-small w3-ripple w3-light-gray">povijest kupnji<i class="fa fa-history w3-margin-left"></i></a>
            </c:when>
            <c:when test="${sessionScope.get('user').getRole() == 'Customer'}">
                <a href="/MVC/history" class="w3-button w3-padding-small w3-ripple w3-light-gray">povijest kupnji<i class="fa fa-history w3-margin-left"></i></a>
            </c:when>
        </c:choose>

        <a href="/MVC/shoppingcart" class="w3-button w3-padding-small w3-ripple w3-light-gray">kosarica<i class="fa fa-shopping-cart w3-margin-left"><span id="kosarica_broj_artikala" class="w3-text-red">${sessionScope.get('shoppingcart').getNumberOfItems()}</span></i></a>

        <c:choose>
            <c:when test="${sessionScope.get('user') == null}">
                <a href="/MVC/login" class="w3-button w3-padding-small w3-ripple w3-light-gray" tooltip="Click to sign in or register">guest<i class="fa fa-user-circle w3-margin-right w3-margin-left"></i></a>
            </c:when>
            <c:when test="${sessionScope.get('user') != null}">
                <a href="/MVC/login" class="w3-button w3-padding-small w3-ripple w3-light-gray" tooltip="Click to sign out">${sessionScope.get('user').getEmail()}<i class="fa fa-user-circle w3-margin-right w3-margin-left"></i></a>
            </c:when>
        </c:choose>
    </p>
    <p id="title_content" class="w3-block clear-both"></p>
</header>
