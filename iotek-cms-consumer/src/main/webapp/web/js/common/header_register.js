$(function(){
	
	//渲染placeholder
//	$('#userName,#password,#password2,#verifyCode').placeholder({isUseSpan:true});
	reloadImage("header_register_verifyImage");
	header.clearAllValid("all");
	//输入提醒
	$("#header_register_userName").autocomplete({  
		source: function( request, response ) { 
    	var name=$.ui.autocomplete.escapeRegex( request.term ); 
    	response( $.grep( DataSouce2(name), function( item ){ 
    		return item; 
    	})); 
    }});

  
   
   //利用ajax根据输入的到数据库查找 相当于 
   function DataSouce2(name) { 
   	var array_tags=['qq.com','126.com','163.com','sina.com','sohu.com','yeah.net'];  
   	var mycars=new Array(); 
   	if (name.indexOf("@") == name.length -1 && name.indexOf("@") == name.lastIndexOf("@")) {
		    for (var i = 0; i <array_tags.length; i++) { 
		   		 mycars[i]=name+array_tags[i]; 
		    }; 
   	}
	    return mycars; 
   } 
});

 /**
  * 校验用户名
  */
 header.validUserName = function () {
 	var username = $.trim($("#header_register_userName").val());
 	var phoneReg = /^0{0,1}(13[0-9]|15[0-9]|147|177|18[0-9])[0-9]{8}$/;
 	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
 	
 	var flag = false;
 	if (username == ''){
// 		$("#header_register_userName").val('请输入手机号/邮箱');
// 		$("#header_register_userName").css("color","#c4c4c4");
 		$("#header_register_valid_userName").html("请输入您的用户名!");
 		$("#header_register_valid_userName").show();
 		return false;
 	} 
 	
 	if (/^[0-9]*[1-9][0-9]*$/.test(username)) {
 		if (!phoneReg.test(username) ) {
 			$("#header_register_valid_userName").html("输入的手机号格式有误,请重新输入!");
 			flag = false;
 		} else {
 			flag = true;
 		}
 	} else {
 		if (!emailReg.test(username)){
 			flag = false;
 			$("#header_register_valid_userName").html("输入的邮箱格式有误,请重新输入!");
 		} else {
 			flag = true;
 		}
 	}

 	if (flag) {
 		$("#header_register_valid_userName").html("");
 		$("#header_register_valid_userName").hide();
 	} else {
 		$("#header_register_valid_userName").show();
 		return false;
 	}
 	header.checkUserName2(username);
 	
 };

 /**
  * 校验用户名
  */
 header.checkUserName2 = function (accountNo) {
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
 		if (data.user != undefined && data.user != "") {
 			$("#header_register_valid_userName").show().text("您输入的用户名已被注册!");
 			result = false;
 		} else {
 			$("#header_register_valid_userName").hide();
 			$("#header_register_valid_password").hide();
 			result = true;
 		}
 	}
 	});
 	return result;
 };

 /**
  * 获取焦点时清空用户名输入框,并改变该框背景颜色
  */
 header.clearUserName = function () {
 	
 	header.clearAllValid("all");
 };

 /**
  * 清除所有非法提示的信息/或指定的
  */

 header.clearAllValid = function (par) {
 	
 	if (par == 'all') {
 		
 		$("#header_register_valid_userName").html("");
 		$("#header_register_valid_userName").hide();
 		
 		$("#header_register_valid_password").html("");
 		$("#header_register_valid_password").hide();
 		
 		$("#header_register_valid_password2").html("");
 		$("#header_register_valid_password2").hide();
 		
 		$("#header_register_validVerify").html("");
 		$("#header_register_validVerify").hide();
 		
 	} else if(par != '') {
 		$("#"+par).html("");
 		$("#"+par).hide();
 	}
 	$("#loginbutton").css("background-color","#ff8155");
 };

 /**
  * 校验密码
  */
 header.validPassword = function () {
 	var password = $.trim($("#header_register_password").val());
 	$("#header_register_valid_password").show();
 	if (password == ''){
 		$("#header_register_valid_password").html("请输入您的密码!");
 		return;
 	} else if (!/^\w{6,26}$/.test(password)){
 		$("#header_register_valid_password").html("密码由6-26字符组成，请重新输入!");
 		return;
 	} else {
 		$("#header_register_valid_password").html("");
 		$("#header_register_valid_password").hide();
 	}
 };

 /**
  * 清除密码校验提示
  */
 header.clearPassword = function () {

 	header.clearAllValid("validPassword");
 };

 /**
  * 校验密码2
  */
 header.validPassword2 = function () {
 	var password = $.trim($("#header_register_password").val());
 	var password2 = $.trim($("#header_register_password2").val());
 	
 	if (password != password2){
 		$("#header_register_valid_password2").html("两次输入密码不一致,请重新输入!");
 		$("#header_register_valid_password2").show();
 		return;
 	} else {
 		$("#header_register_valid_password2").html("");
 		$("#header_register_valid_password2").hide();
 	}
 };

 /**
  * 清除密码校验提示
  */
 header.clearPassword2 = function () {

 	header.clearAllValid("validPassword2");
 };

 /**
  * 校验验证码
  */
 header.validVerify = function () {
 	
 	var verifyCode = $.trim($("#header_register_verifyCode").val());
 	if (verifyCode == '' || verifyCode == '验证码') {
 		$("#header_register_validVerify").html("验证码不能为空!");
 		$("#header_register_validVerify").show();
 		return;
 	} else {
 		$("#header_register_validVerify").html("");
 		$("#header_register_validVerify").hide();
 	}
 	
 	if(!validCaptch(verifyCode)) {
 		reloadImage("header_register_verifyImage");
 		$("#header_register_validVerify").html("验证码输入错误，请重新输入!");
 		$("#header_register_validVerify").show();
 	} else {
 		$("#header_register_validVerify").html("");
 		$("#header_register_validVerify").hide();
 	}
 };


 /**
  * 获取焦点时清空验证码输入框,并改变该框背景颜色
  */
 header.clearVerify = function () {
 	
 	var verifyCode = $.trim($("#header_register_verifyCode").val());
 	if (verifyCode == '' || verifyCode == '验证码') {
 		$("#header_register_verifyCode").val("");
 	}
 	header.clearAllValid("validVerify");
 };

 /**
  * 回车事件
  * @param evt
  */
 header.enterLogin = function (evt) {
 	var evt = evt ? evt : (window.event ? window.event : null);// 兼容IE和FF
 	if (evt.keyCode == 13) {
 		header.validSubmit();
 	}
 };

 /**
  * 提交
  */
 		
 header.validSubmit = function () {
 	//提交前校验、用户名、密码、验证码有效性
 	header.validUserName();
 	header.validPassword();
 	header.validPassword2();
 	header.validVerify();
 	
 	if ($("#header_register_valid_userName").html() != '' || $("#header_register_valid_password").html() != ''
 		|| $("#header_register_valid_password2").html() != '' || $("#header_register_validVerify").html() != '') {
 		return;
 	}
 	//document.fm1.action=cas_register+"?service="+baseUrl+ctx+"/registerSuccess";
	var serviceUrl = window.location.href;
 	if(typeof header.param != "undefined") {
 		serviceUrl += '?'+header.param;
 	}
 	document.fm1.action=cas_register+"?service="+ serviceUrl;
 	$("#header_register_fm").submit();
 };
