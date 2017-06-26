<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 13.5.2017.
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
    <div class="w3-container w3-display-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
        <h3 class="w3-wide"><b>Beer Shop</b></h3>
    </div>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <a id="a_home" href="/MVC/#" class="w3-bar-item w3-button w3-margin-bottom">Pocetna</a>
        <a id="a_svijetlo" href="/MVC/home#container_svijetlo" class="w3-bar-item w3-button">Svijetlo pivo</a>
        <a id="a_tamno" href="/MVC/home#container_tamno" class="w3-bar-item w3-button">Tamno pivo</a>
        <a id="a_psenicno" href="/MVC/home#container_psenicno" class="w3-bar-item w3-button">Psenicno pivo</a>
        <a id="a_ostalo" href="/MVC/home#container_ostalo" class="w3-bar-item w3-button">Ostale vrste</a>
        <a id="a_sve" href="/MVC/home#container_sve" class="w3-bar-item w3-button">Sve pive</a>
    </div>
</nav>
