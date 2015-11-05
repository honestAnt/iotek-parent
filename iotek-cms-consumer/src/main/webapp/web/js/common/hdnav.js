// JavaScript Document
(function($) {
    $(function() {
        var header = $(".header_nav");
        if (header.length > 0) {
            nav(); //顶部导航
        }
    });
})(jQuery);

function nav() {
    var $liCur = $(".header_nav ul li.cur");
    if ($liCur == undefined || $liCur == null) {
        return;
    }
    var curP = $liCur.position().left,
    curW = $liCur.outerWidth(true),
    $slider = $(".nav-line"),
    $targetEle = $(".header_nav>ul>li:not('.last')"),
    $navBox = $(".header_nav");

    $slider.stop(true, true).animate({
        "left": curP,
        "width": curW
    });
    $targetEle.mouseenter(function() {
        var $_parent = $(this); //.parent(),
        _width = $_parent.outerWidth(true),
        posL = $_parent.position().left;
        $slider.stop(true, true).animate({
            "left": posL,
            "width": _width
        },
        "fast");
    });
    $navBox.mouseleave(function(cur, wid) {
        cur = curP;
        wid = curW;
        $slider.stop(true, true).animate({
            "left": cur,
            "width": wid
        },
        "fast");
    });
    function b() {
        h = $(window).height(),
        t = $(document).scrollTop(),
        t > h ? $("#moquu_top").show() : $("#moquu_top").hide()
    }
    $(document).ready(function() {
        b(),
        $("#moquu_top").click(function() {
            $(document).scrollTop(0)
        })
    }),
    $(window).scroll(function() {
        b()
    });
};