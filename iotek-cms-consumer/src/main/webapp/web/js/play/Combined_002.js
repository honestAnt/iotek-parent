epx = window.epx || {};
epx.printExportButton = function() {
    function init() {
        epx.utilities && epx.utilities.supportsHtml5() && ($pabLink = $("#isd_printABook"), $toolbar = $("#exportToolBar"), $pabLink.click(pabLinkClicked), $toolbar.length > 0 && $toolbar.is(":visible") && ($pabLink.addClass("exportToolBarShown"), $pabLink.css("cursor", "text")), refreshExportCount(), $pabLink.show())
    }
    function pabLinkClicked(event) {
        if ($toolbar.length > 0 && $toolbar.is(":visible")) {
            event.preventDefault();
            return
        }
        getTopicCount() > 0 && (window.epx.utilities.setCookie("ShowPrintToolBar", "true", 365, "/"), location.reload(!0), event.preventDefault())
    }
    function getTopicCount() {
        var topicsCount = 0,
        book, c, t;
        if (typeof localStorage.book != "undefined") {
            book = JSON.parse(localStorage.book);
            for (c in book.Chapters) for (t in book.Chapters[c].Topics) if (topicsCount = topicsCount + book.Chapters[c].Topics[t].Count, book.Chapters[c].Topics[t].Count <= 0 || book.Chapters[c].Topics[t].Type === "node" && book.Chapters[c].Topics[t].Count === 1 || book.Chapters[c].Topics[t].Type === "topic" && book.Chapters[c].Topics[t].Count !== 1 || book.Chapters[c].Topics[t].Type !== "topic" && book.Chapters[c].Topics[t].Type !== "node") return 0
        }
        return topicsCount
    }
    function refreshExportCount(count) {
        localStorage.book && (count || (count = getTopicCount()), $pabLink.find(".count").text(count))
    }
    function removeExportBorder() {
        $pabLink.removeClass("exportToolBarShown");
        $pabLink.css("cursor", "pointer")
    }
    var $pabLink, $toolbar;
    return {
        init: init,
        refreshExportCount: refreshExportCount,
        removeExportBorder: removeExportBorder
    }
} ();
$(document).ready(function() {
    epx.printExportButton.init()
});;
epx = window.epx || {};
epx.library = window.epx.library || {};
epx.library.navigationResizeModule = function() {
    function init() {
        if (!epx.topic || epx.topic.isPrintExperience() !== !0) {
            var $left_txtareaWidths = $("#left_txtareaWidths");
            $left_txtareaWidths.length > 0 && (left_txtareaWidths = $left_txtareaWidths.val().split(";"));
            $left_txtarea = $("#left_txtarea");
            $link = $("#NavigationResize");
            $increase = $("#NavigationResize > img.cl_nav_resize_open");
            $reset = $("#NavigationResize > img.cl_nav_resize_close");
            $content = $("#content");
            epx.utilities && (position = epx.utilities.getCookie("TocPosition", position), normalizedPostion());
            setPosition();
            $link.keydown(function(e) {
                checkForTPressed(e)
            });
            $link.click(function() {
                resize()
            });
            $("html").attr("dir") == "rtl" && $("#toc-resizable-ew").addClass("rtl");
            $left_txtarea.css("max-width", left_txtareaWidths[maxPosition] + "px");
            $left_txtarea.css("min-height", $("#content").height() + "px");
            $(".toc-resizable-ew").mousedown(function(e) {
                mouseDown(e)
            });
            $(window).resize(function() {
                $left_txtarea.css("min-height", $("#content").height() + "px")
            })
        }
    }
    function checkForTPressed(evt) {
        if (evt = evt ? evt: event ? event: null, evt && evt.keyCode === 84) {
            var target = evt.srcElement != null ? evt.srcElement: evt.target;
            if (target.tagName.toLowerCase() == "input" || target.tagName.toLowerCase() == "textarea" || evt.ctrlKey || evt.altKey) return;
            resize()
        }
    }
    function resize() {
        gotoLeftPredefinedPostion();
        position++;
        position > maxPosition && (position = 0, $content.css("width", $("Online_box").css("width")));
        setPosition();
        epx.library.tocFixed && epx.library.tocFixed.setPosition()
    }
    function setPosition(width) {
        width ? ($left_txtarea.css("width", width + "px"), position = width >= left_txtareaWidths[maxPosition] ? maxPosition: width <= maxPosition ? 0 : width) : position <= maxPosition ? $left_txtarea.css("width", left_txtareaWidths[position] + "px") : $left_txtarea.css("width", position + "px");
        epx.utilities && epx.utilities.setCookie("TocPosition", position, 635, "/", ".microsoft.com", null);
        $("html").attr("dir") == "rtl" ? ($content.css("margin-right", $left_txtarea.css("width")), $.browser.msie ? $link.css("left", "-" + (parseInt($link.css("width").replace("px", "")) + 1) + "px") : $link.css("left", "-" + $link.css("width")), applyRtlSrc($increase), applyRtlSrc($reset)) : ($content.css("margin-left", $left_txtarea.css("width")), $link.css("left", $left_txtarea.css("width")));
        $link.css("display", "inline-block");
        $increase.css("display", "none");
        $reset.css("display", "none");
        $content.css("width", "auto");
        window.setTimeout(epx.library.navigationResize.resizeComplete, 0)
    }
    function applyRtlSrc($element) {
        var src = $element.attr("src"),
        dotIndex;
        src.indexOf("_Rtl") == -1 && (dotIndex = src.lastIndexOf("."), $element.attr("src", src.substr(0, dotIndex) + "_Rtl." + src.substr(dotIndex + 1, src.length - dotIndex - 1)))
    }
    function resizeComplete() {
        $increase.css("display", position == maxPosition ? "none": "");
        $reset.css("display", position != maxPosition ? "none": "")
    }
    function mouseMove(e) {
        resizing && (mouseDelayMet || (mouseDelayTimer = setTimeout(function() {
            mouseDelayMet = !0
        },
        MinMouseDelay)), epx.library.tocFixed && epx.library.tocFixed.setPosition(), mouseDistanceMet(e) && mouseDelayMet && (prevPageX = e.pageX, prevPageY = e.pageY, $("html").attr("dir") == "rtl" ? setPosition($left_txtarea.offset().left + $left_txtarea.width() - e.pageX) : setPosition(e.pageX - $left_txtarea.offset().left)), e.preventDefault())
    }
    function selectStart(e) {
        return ! 1
    }
    function mouseDown(e) {
        $(document).one("mouseup", mouseUp);
        $(document).on("mousemove", mouseMove);
        $(document).on("selectstart", selectStart);
        prevPageX = e.pageX;
        prevPageY = e.pageY;
        resizing = !0;
        e.preventDefault()
    }
    function mouseUp() {
        resizing && ($(document).off("mousemove", mouseMove), $(document).off("selectstart", selectStart), resizing = !1, mouseDelayMet = !1)
    }
    function mouseDistanceMet(e) {
        return Math.abs(prevPageX - e.pageX) >= MinMouseDist
    }
    function gotoLeftPredefinedPostion() {
        var currWidth, i;
        if (! (position <= maxPosition)) for (currWidth = position, position = maxPosition, i = 1; i < left_txtareaWidths.length; i++) if (currWidth <= left_txtareaWidths[i]) {
            position = i - 1;
            break
        }
    }
    function normalizedPostion() {
        position >= left_txtareaWidths[maxPosition] && (position = maxPosition)
    }
    var position = 1,
    resizing = !1,
    left_txtareaWidths = [0, 980, 1080, 1280],
    maxPosition = left_txtareaWidths.length - 1,
    prevPageX = 0,
    prevPageY = 0,
    mouseDelayMet = !1,
    mouseDelayTimer,
    MinMouseDist = 15,
    MinMouseDelay = 1,
    $left_txtarea,
    $link,
    $increase,
    $reset,
    $content;
    return {
        init: init,
        resize: resize,
        resizeComplete: resizeComplete,
        setPosition: setPosition
    }
};
epx.library.navigationResize = epx.library.navigationResizeModule();
$(document).ready(function() {
    epx.library.navigationResize.init()
});;