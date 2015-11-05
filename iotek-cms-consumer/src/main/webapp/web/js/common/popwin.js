//���ض���
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

//���������
function psninfor(){
document.getElementById('psinforbg').style.display='block';
document.getElementById('psninforbox').style.display='block';
}
function payforbox(){
document.getElementById('payforpbbg').style.display='block';
document.getElementById('payforpbbox').style.display='block';
}
function statebox(){
document.getElementById('statebg').style.display='block';
document.getElementById('statemain').style.display='block';
}
function statebox(){
document.getElementById('statebg').style.display='block';
document.getElementById('statemain').style.display='block';
}
function inforclose(){
document.getElementById('psinforbg').style.display='none';
document.getElementById('psninforbox').style.display='none';
}
function closebg(){
document.getElementById('statebg').style.display='none';
document.getElementById('statemain').style.display='none';
}
function payforclose(){
document.getElementById('payforpbbg').style.display='none';
document.getElementById('payforpbbox').style.display='none';
}
function publicbox(){
document.getElementById('public_bg').style.display='block';
document.getElementById('public_pmp').style.display='block';
}
function publicbox_close(){
document.getElementById('public_bg').style.display='none';
document.getElementById('public_pmp').style.display='none';
}
function logo_in(){alert()
//��֤
//ת��...
//myform.action=""
//myform.submit()
guanbi();
}
//��֤�����л�
//<!CDATA[
function g(o) { return document.getElementById(o); }
function hover_zzjs_net(n) {
//�����N����ǩ,�ͽ�i<=N;
	for (var i = 1; i <= 2; i++) { 
		g('all_letter_' + i).className = 'nor_setup'; 
		g('all_letter_0' + i).className = 'unall_letter_net'; 
	} 
	g('all_letter_0' + n).className = 'all_letter_con'; 
	g('all_letter_' + n).className = 'hovertab_setup';
}
//���Ҫ���ɵ������ת���뽫<li>�е�onmouseover �ĳ� onclick;
//]]>

//��վ��ҳ����
/*(function ($) {
	$(function () {
		nav(); //��������
	});
	function nav() {
		var $liCur = $(".header_nav>ul>li.cur"),
			curP = $liCur.position().left,
			curW = $liCur.outerWidth(true),
			$slider = $(".nav-line"),
			$targetEle = $(".header_nav>ul>li:not('.last')"),
			$navBox = $(".header_nav");
		$slider.stop(true, true).animate({
			"left" : curP,
			"width" : curW
		});
		$targetEle.mouseenter(function () {
			var $_parent = $(this);//.parent(),
			_width = $_parent.outerWidth(true),
			posL = $_parent.position().left;
			$slider.stop(true, true).animate({
				"left" : posL,
				"width" : _width
			}, "fast");
		});
		$navBox.mouseleave(function (cur, wid) {
			cur = curP;
			wid = curW;
			$slider.stop(true, true).animate({
				"left" : cur,
				"width" : wid
			}, "fast");
		});
	};
	
})(jQuery);*/
$(function () {
	$(".outlinemenu li > .outl_header").click(function(){
		   
		var arrow = $(this).find("span.arrow");
	
		if(arrow.hasClass("up")){
			arrow.removeClass("up");
			arrow.addClass("down");
		}else if(arrow.hasClass("down")){
			arrow.removeClass("down");
			arrow.addClass("up");
		}
		$(this).parent().find(".second_menu1").slideToggle();
		$(this).parent().find(".second_menu2").slideToggle();
		
	});
})
