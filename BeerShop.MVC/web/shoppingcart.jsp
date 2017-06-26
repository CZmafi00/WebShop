<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 13.5.2017.
  Time: 15:14
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
    <script src="https://www.paypalobjects.com/api/checkout.js"></script>
    <script src="scripts/shoppingcart.js"></script>
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

    <div class="w3-container w3-text-grey" id="jeans">
        <p>U košarici: <span id="broj_artikala">${sessionScope.get("shoppingcart").getNumberOfItems()}</span> items / ukupno: <span id="total_price">${sessionScope.get("shoppingcart").getTotalPrice()}</span> kn</p>
    </div>

    <!-- Form content -->
    <div id="product_container" class="w3-row w3-grayscale">

        <c:forEach items="${sessionScope.get('shoppingcart').getItems()}" var="item">
           <div>
               <div class="w3-col l3 s1 height-400-px"></div>
               <div class="w3-col l3 s5">
                   <div class="w3-container height-400-px" id="${item.getProductId()}">
                       <img src="${item.getImageUrl()}" class="width-100">
                       <p>${item.getProductName()}<br><b>${item.getPrice()} kn</b></p>
                   </div>
               </div>
               <div class="w3-col l3 s5 height-400-px">
                   <p>ukupna cjena: <span class="ukupna-cjena">${item.getTotalPrice()}</span> kn</p>
                   <p>u košarici: <span class="ukupna-kolicina">${item.getQty()}</span></p>
                   <p>nova količina: <input type="number" class="w3-input w3-border w3-round input-cart-qty" value="1" /></p>
                   <p><a class="w3-button w3-block w3-red w3-round btn-cart-remove" id="${item.getProductId()}"><span class="fa fa-trash"></span></a></p>
                   <p><a class="w3-button w3-block w3-black w3-round btn-cart-modify" id="${item.getProductId()}"><span class="fa fa-shopping-cart"></span> </a></p>
               </div>
               <div class="w3-col l3 s1 height-400-px"></div>
           </div>
        </c:forEach>

       <c:choose>
           <c:when test="${sessionScope.get('shoppingcart').getNumberOfItems() > 0}">
               <div class="w3-col l6 s6 w3-padding-16">
                   <p></p>
               </div>

               <div class="w3-col l6 s6 w3-padding-16">
                   <input id="btn_to_payment" type="button" class="w3-button w3-black w3-block" value="Plaćanje >">
               </div>
           </c:when>
           <c:otherwise>
                <h2>Košarica je prazna.</h2>
               <p><a href="/MVC/home" class="w3-button w3-black">< Home</a></p>
           </c:otherwise>
       </c:choose>

    </div>

   <c:choose>
       <c:when test="${sessionScope.get('shoppingcart').getNumberOfItems() > 0}">
           <div id="payment_container" class="w3-row w3-grayscale">

         <c:choose>
             <c:when test="${sessionScope.get('user') == null}">

                 <form class="w3-container" action="/MVC/shoppingcart" method="post">

                     <br/>
                     <h4>Način plaćanja</h4>
                     <div class="w3-row">
                         <div>
                             <input id="cash" class="w3-radio" type="radio" name="payment" value="cash" checked="">
                             <label>Gotovina/pouzeće</label>
                             <br>
                             <input id="pay_pal" class="w3-radio" type="radio" name="payment" value="paypal">
                             <label><i class="fa fa-paypal"></i> Pay pal </label>
                         </div>
                     </div>

                     <div id="paypal_btn" style="display: none;"></div>

                     <br>
                     <p>
                         <label class="w3-text-grey">Ime</label>
                         <input id="name" class="w3-input w3-border w3-round" type="text" name="name" required>
                     </p>
                     <p>
                         <label class="w3-text-grey">Prezime</label>
                         <input id="last_name" class="w3-input w3-border w3-round" type="text" name="lastName" required>
                     </p>
                     <p>
                         <label class="w3-text-grey">Email</label>
                         <input id="email" class="w3-input w3-border w3-round" type="email" name="email" required>
                     </p>
                     <p>
                         <label class="w3-text-grey">Adresa</label>
                         <input id="adress" class="w3-input w3-border w3-round" type="text" name="adress" required>
                     </p>
                     <p>
                         <label class="w3-text-grey">Grad</label>
                         <input id="city" class="w3-input w3-border w3-round" type="text" name="city" required>
                     </p>
                     <p>
                         <label class="w3-text-grey">Poštanski broj</label>
                         <input id="zip" class="w3-input w3-border w3-round" type="text" name="zip" required>
                     </p>

                     <div class="w3-col l6 s6 w3-padding-16">
                         <input id="btn_back" type="button" class="w3-button w3-gray w3-block w3-margin" style="float: right"
                                value="< Natrag">
                     </div>
                     <div class="w3-col l6 s6 w3-padding-16">
                         <input id="btn_submit" type="submit" class="w3-button w3-black w3-block w3-margin" value="Potvrdi >">
                     </div>

                 </form>

             </c:when>
             <c:otherwise>

                 <form class="w3-container" action="/MVC/shoppingcart" method="post">

                     <br/>
                     <h4>Način plaćanja</h4>
                     <div class="w3-row">
                         <div>
                             <input id="cash" class="w3-radio" type="radio" name="payment" value="cash" checked="">
                             <label>Gotovina/pouzeće</label>
                             <br>
                             <input id="pay_pal" class="w3-radio" type="radio" name="payment" value="paypal">
                             <label><i class="fa fa-paypal"></i> Pay pal </label>
                         </div>
                     </div>

                     <div class="w3-col l6 s6 w3-padding-16">
                         <input id="btn_back" type="button" class="w3-button w3-gray w3-block w3-margin" style="float: right"
                                value="< Natrag">
                     </div>
                     <div class="w3-col l6 s6 w3-padding-16">
                         <input id="btn_submit" type="submit" class="w3-button w3-black w3-block w3-margin" value="Potvrdi >">
                     </div>

                 </form>

             </c:otherwise>
         </c:choose>

           </div>
       </c:when>
   </c:choose>

</div>

<%@include file="jsp_elements/_modal.jsp" %>

<script>
    shoppingCartManager.init();
</script>

</body>
</html>
