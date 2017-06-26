/**
 * Created by mario on 14.5.2017..
 */
var homeManager = {
    navCategories: {
        svijetlo: '#a_svijetlo',
        tamno: '#a_tamno',
        psenicno: '#a_psenicno',
        ostalo: '#a_ostalo',
        sve: '#a_sve'
    },
    containers: {
        svijetlo: '#container_svijetlo',
        tamno: '#container_tamno',
        psenicno: '#container_psenicno',
        ostalo: '#container_ostalo',
        sve: '#container_sve'
    },
    btnHome: '#a_home',
    containerHome: '#container_home',
    title: '#title_content',
    btnAddProduct: '.btn-add-product',
    kosaricaBrojArtikala: '#kosarica_broj_artikala',
    init: function () {

        this.positionToContainer();
        this.registerListeners();

    },
    registerListeners: function () {

        $.each(homeManager.navCategories, function (index, value) {

            $(value).click(function (e) {
                e.preventDefault();

                setContentTitle($(value).text());
                var url = $(value).attr('href');
                var containerId = homeManager.getContainerIdFromUrl(url);

                homeManager.toggleContainers('#' + containerId);
            })
        })
        $(this.btnHome).click(function (e) {
            e.preventDefault();
            homeManager.hideContainers();
            $(homeManager.containerHome).show();
        });
        $(homeManager.btnAddProduct).click(function (e) {
            homeManager.addProductToShoppingCart(this);
        });
    },
    toggleContainers: function (container) {

        if (container == '#' || container == '#izdvojeno')
            return;

        if ($(container) == undefined) {
            console.log(container);
            return;
        }

        $.each(homeManager.containers, function (index, value) {

            if ($(value).is(':visible') && $(value).attr('id') != container.replace('#', '')) {
                $(value).hide(500);
            }
        });

        $(homeManager.containerHome).hide();
        $(container).show(500);

    },
    positionToContainer: function () {

        var url = window.location.href;
        var containerId = homeManager.getContainerIdFromUrl(url);

        homeManager.toggleContainers('#' + containerId);
    },
    hideContainers: function () {

        $.each(homeManager.containers, function (index, value) {
            $(value).hide(500);
        });
    },
    getContainerIdFromUrl: function (url) {

        var index = (url.lastIndexOf('#'));

        if (index > -1)
            return url.substring(index + 1)
        else
            return '';
    },
    addProductToShoppingCart: function (btn) {

        var productId = $(btn).attr('id');
        var inputQty = $(btn).prev();
        var qty = $(inputQty).val();

        $.ajax({
            url: '/MVC/aProduct',
            method: 'post',
            data: {productId: productId, qty: qty},
            dataType: 'json',
            statusCode: {
                201: function (response) {
                    var items = Number($(homeManager.kosaricaBrojArtikala).text());
                    $(homeManager.kosaricaBrojArtikala).text(items + 1);
                    showModal(response.responseText);
                },
                202: function (response) {
                    showModal(response.responseText);
                },
                206: function (response) {
                    showModal(response.responseText);
                },
                500: function(response) {
                    showModal(response.responseText);
                }
            }
        })
    }
}
