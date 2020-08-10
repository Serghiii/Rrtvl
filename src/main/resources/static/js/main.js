$(document).ready(function(){
    setActiveMenu();
    $(".slider").each(function () {
        var obj = $(this);
        $(obj).append("<div class='nav'></div>");
        $(obj).find("li").each(function () {
            $(obj).find(".nav").append("<span rel='"+$(this).index()+"'></span>");
            $(this).addClass("slider"+$(this).index());
        });
        $(obj).find("span").first().addClass("on");
    });
    $(".slider .nav span").click(function() {
        var sl = $(this).closest(".slider");
        $(sl).find("span").removeClass("on");
        $(this).addClass("on");
        var obj = $(this).attr("rel");
        sliderJS(obj,sl);
        return false;
    });
});

/*
function setEqualHeight(columns){
    var tallestcolumn = 0;
    columns.each(function(){
        currentHeight = $(this).offsetHeight; // height();
        if (currentHeight > tallestcolumn){
            tallestcolumn = currentHeight;
        }
    });
    $(".main_column_right").height(tallestcolumn);
    // columns.height(tallestcolumn);
}
*/

function setActiveMenu(){
    var location = window.location.href;
    var Index = location.indexOf("?");
    if (Index>0)location=location.substring(0,Index);
    var cur_url = '/' + location.split('/').pop();
    $('.top-menu li').each(function(){
        var link = $(this).find('a').attr('href');
        Index = link.indexOf("?");
        if (Index>0)link=link.substring(0,Index);
        if (cur_url === link){
            $(this).addClass('active').siblings().removeClass('active');
            $(this).parents('li').addClass('active').siblings().removeClass('active');
        }
    });
}

function sliderJS (obj, sl) {
    var ul = $(sl).find("ul");
    var bl = $(sl).find("li.slider" + obj);
    // var step = $(bl).width();
    var step = 670;
    $(ul).animate({marginLeft: "-" + step * obj}, 500);
}