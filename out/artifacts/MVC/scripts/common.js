/**
 * Created by mario on 13.5.2017..
 */

// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

function showModal(msg) {

    $('#modal_message').empty();
    $('#modal_message').append(msg);
    document.getElementById('modal').style.display='block';
}

function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}

function setContentTitle(val) {
    $('#title_content').text(val);
}