<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 14.5.2017.
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <title>Beer shop</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="jsp_elements/_bundles.jsp" %>
    <link rel="stylesheet" href="content/styles/jquery-ui.min.css">
    <script src="scripts/history.js"></script>
    <script src="scripts/jquery-ui.min.js"></script>
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

    <div class="w3-container">
        <c:choose>
            <c:when test="${sessionScope.get('user').getRole() == 'Admin'}">

                <div class="w3-row w3-margin-top w3-margin-bottom">
                    <div class="w3-half">
                        <a id="tab_pur" class="w3-button w3-block w3-dark-grey tab-btn" aria-describedby="container_purchases">Prošlost kupnji</a>
                    </div>
                    <div class="w3-half">
                        <a id="tab_log" class="w3-button w3-block w3-light-grey tab-btn" aria-describedby="container_logins">Prošlost prijava</a>
                    </div>
                </div>

               <form method="post" action="/MVC/history">
                   <div class="w3-row w3-margin-bottom">
                       <div class="w3-col l3 s3 w3-padding">
                           <c:choose>
                               <c:when test="${requestScope.get('user') != null}">
                                   <input id="date_picker" name="date" type="text" class="w3-input w3-border w3-round" placeholder="date..." value="${requestScope.get('date')}"/>
                               </c:when>
                               <c:otherwise>
                                   <input id="date_picker" name="date" type="text" class="w3-input w3-border w3-round" placeholder="date..."/>
                               </c:otherwise>
                           </c:choose>
                       </div>
                       <div class="w3-col l3 s3 w3-padding">
                           <c:choose>
                               <c:when test="${requestScope.get('user') != null}">
                                   <input name="email" id="user" type="text" class="w3-input w3-border w3-round" placeholder="user..."  value="${requestScope.get('user')}"/>
                               </c:when>
                               <c:otherwise>
                                   <input name="email" id="user" type="text" class="w3-input w3-border w3-round" placeholder="user..."/>
                               </c:otherwise>
                           </c:choose>
                       </div>
                       <div class="w3-col l3 s3 w3-padding">
                           <input type="submit" class="w3-button w3-black w3-round w3-block" value="Search" />
                       </div>
                       <div class="w3-col l3 s3 w3-padding">
                           <a href="/MVC/history" class="w3-button w3-black w3-round w3-block">Clear filters</a>
                       </div>
                   </div>
               </form>
            </c:when>
        </c:choose>
    </div>

    <c:choose>
        <c:when test="${sessionScope.get('user').getRole() == 'Admin'}">

            <div id="container_logins" class="w3-container" style="display: none">

                <table class="w3-table w3-striped w3-bordered w3-border">
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Datum</th>
                        <th>Ip address</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.get('history').getLogins()}" var="item">
                        <tr>
                            <td>${item.getEmail()}</td>
                            <td>${item.getDate()}</td>
                            <td>${item.getIp()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>

        </c:when>
    </c:choose>

    <div id="container_purchases" class="w3-container">

        <table class="w3-table w3-bordered w3-border">
            <thead>
            <tr>

                <c:choose>
                    <c:when test="${sessionScope.get('user').getRole() == 'Admin'}">
                        <th>Korisnik</th>
                    </c:when>
                </c:choose>

                <th>Datum</th>
                <th>Način plaćanja</th>
                <th>Ukupna cjena</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.get('history').getPurchases()}" var="item" varStatus="loop">
                <tr class="w3-light-grey">

                    <c:choose>
                        <c:when test="${sessionScope.get('user').getRole() == 'Admin'}">
                            <td>${item.getUserName()}</td>
                        </c:when>
                    </c:choose>

                    <td>${item.getDate()}</td>
                    <td>${item.getPurchaseType()}</td>
                    <td>${item.getTotalPrice()}</td>
                    <td><a class="toggleBtn w3-btn w3-dark-grey" id="item_${loop.index}"><i class="fa fa-list"></i></a></td>
                </tr>
                <c:forEach items="${item.getItems()}" var="entry">
                    <tr aria-describedby="item_${loop.index}" class="collapse" style="display: none;">
                        <c:choose>
                            <c:when test="${sessionScope.get('user').getRole() == 'Admin'}">
                                <td colspan="5">
                            </c:when>
                            <c:otherwise>
                                <td colspan="4">
                            </c:otherwise>
                        </c:choose>
                            ${entry.getProductName()} - ${entry.getQty()} kom - ${entry.getPrice()} kn
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>

    </div>

</div>

<%@include file="jsp_elements/_modal.jsp" %>

<script>
    historyManager.init();
</script>

</body>
</html>
