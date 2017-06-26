/**
 * Created by mario on 14.5.2017..
 */
var historyManager = {
    collapse: '.collapse',
    toggleBtn: '.toggleBtn',
    tabBtn: '.tab-btn',
    containerPurchases: 'container_purchases',
    containerLogins: 'container_logins',
    tabPurBtn: '#tab_pur',
    tabLogBtn: '#tab_log',
    dateFormat: 'yy/mm/dd',
    datePicker: '#date_picker',
    init: function () {

        this.registerListeners();
        $(this.datePicker).datepicker({dateFormat: this.dateFormat});
    },
    registerListeners: function () {
        $(this.toggleBtn).click(function () {
            historyManager.toggleRow(this);
        });
        $(this.tabBtn).click(function () {
            historyManager.toggleContainer(this);
        });
        $(this.tabLogBtn).click(function () {
            $(historyManager.tabLogBtn).removeClass('w3-light-grey');
            $(historyManager.tabPurBtn).removeClass('w3-dark-grey');

            $(historyManager.tabLogBtn).addClass('w3-dark-grey');
            $(historyManager.tabPurBtn).addClass('w3-light-grey');
        })
        $(this.tabPurBtn).click(function () {
            $(historyManager.tabPurBtn).removeClass('w3-light-grey');
            $(historyManager.tabLogBtn).removeClass('w3-dark-grey');

            $(historyManager.tabPurBtn).addClass('w3-dark-grey');
            $(historyManager.tabLogBtn).addClass('w3-light-grey');
        })
    },
    toggleRow: function (btn) {

        var id = $(btn).attr('id');
        var rows = $('[aria-describedby=' + id + ']');

        $.each(rows, function (index, value) {

            if ($(value).hasClass('collapse'))
                $(value).toggle();
        })
    },
    toggleContainer: function (btn) {

        if ($(btn).attr('aria-describedby') == historyManager.containerPurchases) {
            $('#' + historyManager.containerLogins).hide(0);
            $('#' + historyManager.containerPurchases).show(100);
        }
        else if ($(btn).attr('aria-describedby') == historyManager.containerLogins) {
            $('#' + historyManager.containerPurchases).hide(0);
            $('#' + historyManager.containerLogins).show(100);
        }

    }
}