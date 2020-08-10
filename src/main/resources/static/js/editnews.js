$(document).ready(function(){
    $(".btclear").click(function () {
        $(".photo").attr("src", null);
        $("input[name=img]").val("");
        $("input[type=file]").val("");
    });
    $("button[name=btdelete]").click(function (e) {
        if (!confirm("Ви дійсно бажаєте вилучити статтю?")){
            e.preventDefault();
        }
    });
    $("input[type=file]").change(function () {
        previewFile();
    });

});

function previewFile() {
    var preview = document.querySelector('img');
    var input   = document.querySelector('input[name=img]');
    var file    = document.querySelector('input[type=file]').files[0];
    var reader  = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
        input.value = reader.result.split(',')[1];
    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
}