/**
 * Created by mario on 14.5.2017..
 */
var loginManager = {
    btnLogin: '#btn_login',
    txtEmail: '#email',
    txtPassword: '#password',
    init: function () {

        //this.registerListeners();
        setContentTitle('Login');
    },
    registerListeners: function () {
        $(loginManager.btnLogin).click(function (e) {
            e.preventDefault();
            return loginManager.validateLogin();
            this.submit();
        });
    },
    validateLogin: function () {

        var email = $(loginManager.txtEmail).val();
        var password = $(loginManager.txtPassword).val();
        var msg = '';
        var valid = true;

        if (email == '') {
            valid = false;
            msg += '<p>Polje email je obvezno.</p>'
        }
        if (email != '' && !isEmail(email)) {
            valid = false;
            msg += '<p>Email je neispravnog formata.</p>'
        }
        if (password == '') {
            valid = false;
            msg += '<p>Polje password je obvezno.</p>'
        }

        if (msg != '') {
            showModal(msg);
        }
console.log(valid);
        return valid;
    }
}