$(document).ready(function(){
    $("button[name=btdelete]").click(function (e) {
        if (!confirm("Ви дійсно бажаєте вилучити запис?")){
            e.preventDefault();
        }
    });
    $("input[type=file]").change(function () {
        previewFile();
    });
});

function previewFile() {
    var preview = document.querySelector('img');
    // var input   = document.querySelector('input[name=filename]');
    var file    = document.querySelector('input[type=file]').files[0];
    var reader  = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
        // input.value = $('input[type=file]').val();
    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
}