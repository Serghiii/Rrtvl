$(document).ready(function(){
    $(".btback").click(function (e) {
        e.preventDefault();
        DoRequest();
    });
    $("input[name=name]").focus(function () {
        // $(this).next("span").css("display","none").fadeOut(1000);
        $(".txt1").css("display","none").fadeOut(1000);
    });
    $("input[name=email]").focus(function () {
        $(".txt2").css("display","none").fadeOut(1000);
    });
    $("input[name=email]").change(function () {
        $(".txt2").css("display","none").fadeOut(1000);
    });
    $("textarea[name=topictxt]").focus(function () {
        $(".txt4").css("display","none").fadeOut(1000);
    });
    window.addEventListener('blur',function(){
        window.setTimeout(function () {
            if (document.activeElement instanceof HTMLIFrameElement) {
                $(".txt5").css("display", "none").fadeOut(1000);
            }
        },0);
    });
});

function DoRequest() {
    if (!DoValidate())return;
    var name = $("input[name=name]").val();
    var email = $("input[name=email]").val();
    var topic = $("input[name=topic]").val();
    var topictxt = $("textarea[name=topictxt]").val();
    var object = {name:name,email:email,topic:topic,topictxt:topictxt};
    $.ajax('/backf',{
        type: "post",
        data: object,
        headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()}
    }).done(function (data) {
        if (data === true){
            alert("Дякуємо за заявку.");
            $('.contact_form').trigger('reset');
        }
        else{
            alert("Сервіс не доступний.\nСпробуйте ще раз, або спробуйте пізніше.");
        }
    });
}

function DoValidate() {
    var res = true;
    if (!validateName())res = false;
    if (!validateEmail())res = false;
    if (!validateTopictxt())res = false;
    if (!validateCaptcha())res = false;
    return res;
}

var NoContent = "Не заповнено поле.";
var FalseContent = "Не вірно заповнено поле.";

function validateName() {
    var res = true;
    var name = $("input[name=name]").val();
    if (!name.length){
        res = false;
        $(".txt1").text(NoContent);
        $(".txt1").css("display","inline").fadeIn(1000);
    }else if ((name.length<3)||(name.length>30)){
        res = false;
        $(".txt1").text("Ім'я (3-30 символів).");
        $(".txt1").css("display","inline").fadeIn(1000);
    }else{
        $(".txt1").css("display","none").fadeOut(1000);
    }
    return res;
}

function validateEmail() {
//     var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var pattern  = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var res = true;
    var email = $("input[name=email]").val();
    console.log(email);
    if (!email.length){
        res = false;
        $(".txt2").text(NoContent);
        $(".txt2").css("display","inline").fadeIn(1000);
    }else if (!pattern.test(email)){
        res = false;
        $(".txt2").text(FalseContent);
        $(".txt2").css("display","inline").fadeIn(1000);
    }else{
        $(".txt2").css("display","none").fadeOut(1000);
    }
    return res;
}

function validateTopictxt() {
    var res = true;
    var topictxt = $("textarea[name=topictxt]").val();
    if (!topictxt.length){
        res = false;
        $(".txt4").text(NoContent);
        $(".txt4").css("display","inline").fadeIn(1000);
    }else{
        $(".txt4").css("display","none").fadeOut(1000);
    }
    return res;
}

function validateCaptcha() {
    var res = true;
    var response = grecaptcha.getResponse();
    if (!response.length){
        res = false;
        $(".txt5").text(NoContent);
        $(".txt5").css("display","inline").fadeIn(1000);
    }else{
        $(".txt5").css("display","none").fadeOut(1000);
    }
    return res;
}