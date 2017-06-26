/**
 * Created by mario on 13.5.2017..
 */
var shoppingCartManager = {
    productContainer: '#product_container',
    paymentContainer: '#payment_container',
    btnToPayment: '#btn_to_payment',
    btnBack: '#btn_back',
    btnSubmit: '#btn_submit',
    btnRemove: '.btn-cart-remove',
    btnModify: '.btn-cart-modify',
    numItems: '#broj_artikala',
    kosaricaBrojArtikala: '#kosarica_broj_artikala',
    totalPrice: '#total_price',
    form: {
        name: '#name',
        lastName: '#last_name',
        email: '#email',
        adress: '#adress',
        city: '#city',
        zip: '#zip',
        paypal: '#pay_pal',
        cash: '#cash'
    },
    paypalBtn: '#paypal_btn',
    init: function () {

        //Ab1V0meC0Zu-QFbHnOFVJ1nfVxuMbHOdlceAiNztYq9IBB7f6CdngSni7QkoK-ukDlpPbP-EO06BapSF - clientId
        //EILhVllwYtdPBukSyCDS4ymfQdzTOPM4CeXfq-Fnr_IyLX_-VWkEf3svKmfyZ_u4y312tCxN0B2Kqf0t - secret

        setContentTitle('Košarica');
        $(shoppingCartManager.paymentContainer).hide();
        this.registerListeners();
        this.paypal();
    },
    registerListeners: function () {

        if (shoppingCartManager.btnToPayment === undefined)
            return;

        $(shoppingCartManager.btnToPayment).click(function () {
            shoppingCartManager.toggleContainers(this.id);
        });
        $(shoppingCartManager.btnBack).click(function () {
            shoppingCartManager.toggleContainers(this.id);
        });
        $(shoppingCartManager.btnSubmit).click(function () {
            return shoppingCartManager.validateFormData();
        });
        $(shoppingCartManager.btnRemove).click(function () {
            shoppingCartManager.removeFromCart(this);
        });
        $(shoppingCartManager.btnModify).click(function () {
            shoppingCartManager.modifyCartItemNumber(this);
        });
        $(shoppingCartManager.form.paypal).change(function () {
           if (this.checked) {
               $(shoppingCartManager.paypalBtn).show();
               $(shoppingCartManager.btnSubmit).hide();
           }
        });
        $(shoppingCartManager.form.cash).change(function () {
            if (this.checked) {
                $(shoppingCartManager.paypalBtn).hide();
                $(shoppingCartManager.btnSubmit).show();
            }
        });
    },
    toggleContainers: function (senderId) {
        if (senderId == $(shoppingCartManager.btnToPayment).attr("id")) {
            $(shoppingCartManager.productContainer).hide(300);
            $(shoppingCartManager.paymentContainer).show(300);
        }
        else if (senderId == $(shoppingCartManager.btnBack).attr("id")) {
            $(shoppingCartManager.paymentContainer).hide(300);
            $(shoppingCartManager.productContainer).show(300);
        }
    },
    validateFormData: function () {

        valid = true;
        msg = '';

        if ($(shoppingCartManager.form.name) != undefined && $(shoppingCartManager.form.name).val() == '') {
            valid = false;
            msg += '<p>Polje ime je obvezno.</p>'
        }
        if ($(shoppingCartManager.form.lastName) != undefined && $(shoppingCartManager.form.lastName).val() == '') {
            valid = false;
            msg += '<p>Polje prezime je obvezno.</p>'
        }
        if ($(shoppingCartManager.form.email) != undefined && $(shoppingCartManager.form.email).val() == '') {
            valid = false;
            msg += '<p>Polje email je obvezno.</p>'
        }
        if ($(shoppingCartManager.form.email).val() != undefined &&!isEmail($(shoppingCartManager.form.email).val())) {
            valid = false;
            msg += '<p>Email format je neispravan.</p>'
        }
        if ($(shoppingCartManager.form.adress) != undefined && $(shoppingCartManager.form.adress).val() == '') {
            valid = false;
            msg += '<p>Polje adresa je obvezno</p>'
        }
        if ($(shoppingCartManager.form.city) && $(shoppingCartManager.form.city).val() == '') {
            valid = false;
            msg += '<p>Polje grad je obvezno</p>'
        }
        if ($(shoppingCartManager.form.zip) != undefined && $(shoppingCartManager.form.zip).val() == '') {
            valid = false;
            msg += '<p>Polje Poštanski broj je obvezno.</p>'
        }

        if (msg != '') {
            showModal(msg);
        }

        return valid;
    },
    removeFromCart: function (btn) {

        var productId = $(btn).attr('id');

        $.ajax({
            url:'/MVC/aShoppingcart',
            method: 'get',
            data: {productId: productId},
            statusCode: {
                200: function (response) {

                    var deleteItemModel = $.parseJSON(response);

                    $(shoppingCartManager.numItems).text(deleteItemModel.newItemCount);
                    $(shoppingCartManager.kosaricaBrojArtikala).text(deleteItemModel.newItemCount);
                    $(shoppingCartManager.totalPrice).text(deleteItemModel.newPrice);

                    var parentDiv = $(btn).parent().parent().parent();
                    $(parentDiv).remove();

                    if (deleteItemModel.newItemCount == 0)
                        window.location = '/MVC/home';
                },
                206: function (response) {
                    showModal(response);
                },
                500: function (response) {
                    showModal(response);
                }
            }
        })
    },
    modifyCartItemNumber: function (btn) {

        var productId = $(btn).attr('id');
        var qtyInput = $(btn).parent().prev().prev().children()[0];
        var qty = $(qtyInput).val();
        var itemQty = $(btn).parent().prev().prev().prev().children()[0];
        var itemPrice = $(btn).parent().prev().prev().prev().prev().children()[0];

        $.ajax({
            url:'/MVC/aShoppingcart',
            method: 'post',
            data: {productId: productId, qty: qty},
            statusCode: {
                200: function (response) {
                    var modifyItemModel = $.parseJSON(response);

                    $(shoppingCartManager.totalPrice).text(modifyItemModel.newPrice);
                    $(itemQty).text(modifyItemModel.newQty);
                    $(itemPrice).text(modifyItemModel.newItemPrice);

                },
                206: function (response) {
                    showModal(response.responseText);
                }, 
                500: function (response) {
                    showModal(response.responseText);
                }
            }
        })

    },
    paypal: function () {

        paypal.Button.render({
            env: 'sandbox',
            client: {
              sandbox: 'Ab1V0meC0Zu-QFbHnOFVJ1nfVxuMbHOdlceAiNztYq9IBB7f6CdngSni7QkoK-ukDlpPbP-EO06BapSF',
            },
            commit: true,
            payment: function(data, actions) {
                console.log('payment');

                if (shoppingCartManager.validateFormData()) {

                    return actions.payment.create({
                        transactions: [
                            {
                                amount: { total: $(shoppingCartManager.totalPrice).text(), currency: 'HRK' }
                            }
                        ]
                    });
                } else {
                    return;
                }
            },
            onAuthorize: function(data, actions) {
                console.log('authorize');
                return actions.payment.execute().then(function(payment) {

                    $('form').submit();
                });
            }
        }, shoppingCartManager.paypalBtn);

    }
}
