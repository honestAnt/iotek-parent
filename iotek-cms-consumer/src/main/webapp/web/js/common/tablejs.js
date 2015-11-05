// JavaScript Document
//表格隔行换色
$(document).ready(function(){ //这个就是传说的ready   
	$(".Exper_Tables tr").mouseover(function(){   
	   //如果鼠标移到class为stripe的表格的tr上时，执行函数   
	  $(this).addClass("over");}).mouseout(function(){   
			//给这行添加class值为over，并且当鼠标一出该行时执行函数   
			$(this).removeClass("over");}) //移除该行的class   
  $(".Exper_Tables tr:even").addClass("alt");   
	//给class为stripe的表格的偶数行添加class值为alt
  });  
  


//左边导航展开关闭
$(function() {
    $(".subNav").click(function() {
        $(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd")

        // 修改数字控制速度， slideUp(500)控制卷起速度
        $(this).next(".navContent").slideToggle(500).siblings(".navContent").slideUp(500);
    })
})
//左边导航展开关闭
//function m_id(id) {
//	return document.getElementById(id);
//}
//function getcookie(name) {
//	var cookie_start = document.cookie.indexOf(name);
//	var cookie_end = document.cookie.indexOf(";", cookie_start);
//	return cookie_start == -1 ? '' : unescape(document.cookie.substring(cookie_start + name.length + 1, (cookie_end > cookie_start ? cookie_end : document.cookie.length)));
//}
//var collapsed = getcookie('m_shutoropen');
//function shutoropen(menucount) {
//	var classname = m_id('menuimg_' + menucount).parentNode.className;
//	if(m_id('menu_' + menucount).style.display == 'none') {
//		m_id('menu_' + menucount).style.display = '';collapsed = collapsed.replace('[' + menucount + ']' , '');
//	} else {
//		m_id('menu_' + menucount).style.display = 'none';collapsed += '[' + menucount + ']';
//	}
//}

//编程题Tab切换

//<!CDATA[
//function g(o) { return document.getElementById(o); }
//function hover_zzjs_net(n) {
//如果有N个标签,就将i<=N;
//for (var i = 1; i <= 2; i++) { g('all_letter_' + i).className = 'nor_mywork'; g('all_letter_0' + i).className = 'unall_letter_net'; } g('all_letter_0' + n).className = 'all_letter_con'; g('all_letter_' + n).className = 'hovertab_mywork';}
//如果要做成点击后再转到请将<li>中的onmouseover 改成 onclick;
//]]>