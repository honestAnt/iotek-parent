/**
 * 函数集合对象。防止函数名称重复
 */
var header = {};

$(function(){
	
	//渲染
	$('#login_account,#login_passwd').placeholder({isUseSpan:true});
	
 	// tab 选项卡
	$("#header_title_message ul li a").click(function(){
		$(this).addClass("cur").parent("li").siblings("li").find("a").removeClass("cur");
		var index = $(this).parent("li").index();
		$("#header_out_div_bg div .out_tab").eq(index).show().siblings("#header_out_div_bg div .out_tab").hide(); 
	}); 
	
	$("#header_login_btn").click(function(){
		//登陆
		accountNo = $("#login_account").val();
		password = $("#login_passwd").val();
		header.verifyAccount(accountNo, password);		
	});
	
	$("#header_out_div_bg input").click(function(){
		$("#header_out_div_bg input").removeClass("error_txt");
		$(this).addClass("error_txt");
	});
	
	$("#login_passwd").keydown(function (evt) {
		var evt = evt ? evt : (window.event ? window.event : null);// 兼容IE和FF
		if (evt.keyCode == 13) {
			$("#header_login_btn").trigger("click");
		}
	});
});
 
 /* 弹出框效果*/
 // 显示
header.showDiv = function (id,type){
	if ($(".login_panel") != undefined) {
		$(".login_panel").hide();
	}
	reloadImage("verifyImage");
 	var div = document.getElementById(id);
 	div.style.display = "block";
 	if(type == "register"){
 		$("#header_title_message ul li a :eq(0)").removeClass("cur");
 		$("#header_title_message ul li a :eq(1)").addClass("cur");
 		$(".out_tab :eq(0)").hide();
 		$(".out_tab :eq(1)").show();
 	}else{
 		$("#header_title_message ul li a :eq(1)").removeClass("cur");
 		$("#header_title_message ul li a :eq(0)").addClass("cur");
 		$(".out_tab :eq(1)").hide();
 		$(".out_tab :eq(0)").show();
 	}
 };

 // 隐藏
 header.hideDiv = function (id){
 	var div = document.getElementById(id);
 	div.style.display = "none";
 	if ($(".login_panel") != undefined) {
		$(".login_panel").show();
	}
 };
 
 header.verifyAccount = function (accountNo, password){
	if($.trim(accountNo) == ""){
		$(".login_account_verify").show().text("请输入账号");
		return false;
	}else if($.trim(password) == ""){
		$(".login_account_verify").hide();
		$(".login_passwd_verify").show().text("请输入密码");
		return false;
	}else if (!/^\w{6,26}$/.test(password)){
		$(".login_passwd_verify").show().text("密码由6-26字符组成，请重新输入!");
		return false;
	}else{
		$(".login_passwd_verify").hide();
		if(header.checkUserName(accountNo)){
			header.loginIndex(accountNo, password);
		}
	}
};

 
 /**
  * 校验用户名
  */
header.checkUserName = function (accountNo) {
 	var result = false;
 	$.ajax({
 		url : ctx + "/userAjax/validUserName",
	type : "post",
	data: {
		userName: accountNo
	}, 
	async: false,
	dataType : 'json',
	success : function(data) {
		if (data.user != undefined && data.user != "" && data.user.enabled == "1") {
			$(".login_account_verify").hide();
			$(".login_passwd_verify").hide();
			result = true;
		} else {
			$(".login_account_verify").show().text("您输入的用户名无效!");
			result = false;
 		}
	}
 	});
 	return result;
 };
 
//登陆
 header.loginIndex = function (accountNo, password) {
 	var form = $('<form  action="abcd" method="POST" name="login_form"></form>');	
 	$('<input type="hidden" name="username" value="'+accountNo+'" />').appendTo(form);
 	$('<input type="hidden" name="password" value="'+password+'" />').appendTo(form);
 	if (document.getElementById("rememberMe").checked) {
 		$('<input type="hidden" name="rememberMe" value="'+true+'" />').appendTo(form);
 	}
 	jQuery(form).appendTo('body');
// 	document.login_form.action = cas_login+"?service="+ portal_url + "/generalize/" + $("#type").val();
 	//登陆后回到当前页
 	var serviceUrl = window.location.href;
 	if(typeof header.param != "undefined") {
 		serviceUrl += '?'+header.param;
 	}
	document.login_form.action = cas_login+"?service="+ serviceUrl;
 	$(document.login_form).submit();
 };