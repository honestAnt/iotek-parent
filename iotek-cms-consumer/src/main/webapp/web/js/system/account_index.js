/*
 * 胡静
 */
var roleId=0;
var username="";
var password="";
var comment="";
var realName="";
var unitId=0;
var courseIds="";
//页面加载完成后执行该方法
$(document).ready(function() {
	/**
	 * 
	 * 获取管理员类型
	 */
	$("#account_ul").find("li").each(function(){
		$(this).click(function(){
			typeId = $(this).attr("typeId");
		});
	});
	initAccountInfo();
	$("#iotek_submit_btn").click(function(){
		var type = $("#type").val();
		//var accountId = $("#accountId").val();
		var flag = iotekAccountValue();
		if(flag){
			//添加新用户
			if(type == 1){
				addAccount(1);
			}else{//更新用户
				//alert(accountId);
				updateAccount(1);
			}
		}
	});
	$("#college_submit_btn").click(function(){
		var type = $("#type").val();
		//var accountId = $("#accountId").val();
		var flag = collegeAccountValue();
		if(flag){
			//添加新用户
			if(type == 1){
				addAccount(2);
			}else{//更新用户
				//alert(accountId);
				updateAccount(2);
			}
		}
	});
	$("#company_submit_btn").click(function(){
		var type = $("#type").val();
		//var accountId = $("#accountId").val();
		var flag = companyAccountValue();
		if(flag){
			//添加新用户
			if(type == 1){
				addAccount(3);
			}else{//更新用户
				//alert(accountId);
				updateAccount(3);
			}
		}
	});
});

/**
 * 初始化页面数据
 */
function initAccountInfo() {
	var params = {};
	//加载网校管理员数据
	params.pageId="iotek-pages";
	typeId = 1;
	getPage(params);
	//加载院校管理员数据
	params.pageId="college-pages";
	typeId = 2;
	getPage(params);
	//加载企业管理员数据
	params.pageId="company-pages";
	typeId = 3;
	getPage(params);

}
/**
 * 获取页码信息，并初始化分页组件
 * @param params
 */
function getPage(params) {
	$.ajax({
		url : ctx + "/systemManage/showAccount",
		type : "post",
		data : {
			type : typeId
		},
		dataType : 'json',
		async:false,
		success : function(data) {
			// 初始化页数和条数
			initPatination(params, data.page.dataCount, data.page.pageSize);
		}
	});
}

/**
 * 分页方法，参数showPage为显示页面的容器ID，callbackfunction为回调函数名,totleCount为数据总条数，pageNum为每页显示条数
 * @param params
 * @param dataCount
 * @param pagesize
 */
function initPatination(params,dataCount,pagesize){

	var callbackFun="";
	if(params.pageId == "iotek-pages"){
		callbackFun = iotekPageCallback;
	}else if(params.pageId == "college-pages"){
		callbackFun = collegePageCallback;
	}else{
		callbackFun = companyPageCallback;
	}
	$("#"+params.pageId).pagination(dataCount, {
		num_edge_entries: 2, //边缘页数
		num_display_entries: 4, //主体页数
		callback:callbackFun,
		items_per_page:pagesize, //每页显示1项
		prev_text:"<em class='Em_prev'></em>",
		next_text:"<em class='Em_next'></em>",
		link_to:"javascript:void(0)"
	});
}

/**
 * 分页回调函数()
 */
function iotekPageCallback(page_index, jq){
	$("#iotek_tbody").html("");
	if(typeId != 1){
		typeId =1;
	}
	$.ajax({
		url : ctx + "/systemManage/showAccount",
		type : "post",
		data: {
			pageNo : page_index+1,
			type : typeId
		}, 
		dataType : 'json',
		async: false,
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.page.maxPage==1){
				$('#iotek-pages').hide();
			}
			resolveData(data.page.result,"iotek_tbody");
		}
	});
}

/**
 * 分页回调函数()
 */
function collegePageCallback(page_index, jq){
	$("#college_tbody").html("");
	$.ajax({
		url : ctx + "/systemManage/showAccount",
		type : "post",
		data: {
			pageNo : page_index+1,
			type : typeId
		}, 
		dataType : 'json',
		async: false,
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.page.maxPage==1){
				$('#college-pages').hide();
			}
			resolveData(data.page.result,"college_tbody");
		}
	});
}

/**
 * 分页回调函数()
 */
function companyPageCallback(page_index, jq){
	$("#company_tbody").html("");
	$.ajax({
		url : ctx + "/systemManage/showAccount",
		type : "post",
		data: {
			pageNo : page_index+1,
			type : typeId
		}, 
		dataType : 'json',
		async: false,
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.page.maxPage==1){
				$('#company-pages').hide();
			}
			resolveData(data.page.result,"company_tbody");
		}
	});
}

//处理返回数据拼接字符串加载到页面上
function resolveData(list,tbody){
	var contentshow = "";
	$(list).each(function (i,account){
		if(account.username == 'administrator'){
			contentshow +="<tr><td>"+account.roleName+"</td><td>"+account.username+"</td>";
			contentshow +="<td>"+account.createTime.replace("T",' ')+"</td><td><span></span></td></tr>";
		}else{
			contentshow +="<tr><td>"+account.roleName+"</td><td>"+account.username+"</td>";
			contentshow +="<td>"+account.createTime.replace("T",' ')+"</td>";
			contentshow +="<td class='operation_td'><a href='javascript:payforbox_over("+account.id+","+account.type+");'>编辑</a><span>|</span><a href='javascript:toDeleteAccount("+account.id+","+account.type+")'>删除</a></td>"+
			"</tr>";
		}
	});
	$("#"+tbody).html(contentshow);
}

//显示弹框
function showBox(){
	$("#payforpbbg").show();
	$("#payforpbbox").show();
	publicbox_close();
}

//添加网校管理员用户
function iotekAccountValue(){
	roleId = $("#iotek_role").val();
	username = $("#iotek_name").val();
	password = $("#iotek_password").val();
	comment = $("#iotek_comment").val();
	realName="";
	unitId=0;
	courseIds="";
	$("#payforpbbg").hide();
	$("#payforpbbox").hide();
	if(username == undefined || username=='' || username==null){
		showMessage(showBox,null,publicbox_close,"请输入用户名！");
		return false;
	}else{
		var flag = verifyUsername(username);
		if(flag && username != $("#username").val()){
			showMessage(showBox,null,publicbox_close,"用户名已存在！");
			return false;
		}
	}
	if(password == undefined || password=='' || password==null){
		showMessage(showBox,null,publicbox_close,"请输入密码！");
		return false;
	}else if(!/^[\s\S]{6,12}$/.test(password)){
		showMessage(showBox,null,publicbox_close,"密码请限制在6-12位之间！");
		return false;
	}
	if(roleId == undefined || roleId == '' || roleId == 0){
		showMessage(showBox,null,publicbox_close,"请选择角色！");
		return false;
	}
	return true;
}

//添加院校管理员用户
function collegeAccountValue(){
	roleId = $("#college_role").val();
	username = $("#college_name").val();
	password = $("#college_password").val();
	comment = $("#college_comment").val();
	realName = $("#college_realname").val();
	unitId = $("#college_unitId").val();
	//获取多选框内容
	courseIds = "";
	$("input:checkbox[name=CheckboxGroup1]:checked").each(function(){  
		courseIds+=$(this).val()+",";  
	});
	$("#payforpbbg").hide();
	$("#payforpbbox").hide();
	if(courseIds == undefined || courseIds=='' || courseIds==null){
		showMessage(showBox,null,publicbox_close,"请选择课程！");
		return false;
	}
	if(unitId == undefined || unitId=='' || unitId==0){
		showMessage(showBox,null,publicbox_close,"请选择学校！");
		return false;
	}
	if(realName == undefined || realName=='' || realName==null){
		showMessage(showBox,null,publicbox_close,"请输入教师姓名！");
		return false;
	}
	if(username == undefined || username=='' || username==null){
		showMessage(showBox,null,publicbox_close,"请输入用户名！");
		return false;
	}else{
		var flag = verifyUsername(username);
		if(flag && username != $("#username").val()){
			showMessage(showBox,null,publicbox_close,"用户名已存在！");
			return false;
		}
	}
	if(password == undefined || password=='' || password==null){
		showMessage(showBox,null,publicbox_close,"请输入密码！");
		return false;
	}else if(!/^[\s\S]{6,12}$/.test(password)){
		showMessage(showBox,null,publicbox_close,"密码请限制在6-12位之间！");
		return false;
	}
	if(roleId == undefined || roleId == '' || roleId == 0){
		showMessage(showBox,null,publicbox_close,"请选择角色！");
		return false;
	}
	return true;
}

//添加企业管理员用户
function companyAccountValue(){
	roleId = $("#company_role").val();
	username = $("#company_username").val();
	password = $("#company_password").val();
	comment = $("#company_comment").val();
	realName = $("#company_realName").val();
	unitId = $("#company_company").val();
	//获取多选框内容
	courseIds = "";
	$("input:checkbox[name=CheckboxGroup2]:checked").each(function(){  
		courseIds+=$(this).val()+",";  
	});
	$("#payforpbbg").hide();
	$("#payforpbbox").hide();
	if(courseIds == undefined || courseIds=='' || courseIds==null){
		showMessage(showBox,null,publicbox_close,"请选择课程！");
		return false;
	}
	if(unitId == undefined || unitId=='' || unitId==0){
		showMessage(showBox,null,publicbox_close,"请选择企业！");
		return false;
	}
	if(realName == undefined || realName=='' || realName==null){
		showMessage(showBox,null,publicbox_close,"请输入领导姓名！");
		return false;
	}
	if(username == undefined || username=='' || username==null){
		showMessage(showBox,null,publicbox_close,"请输入用户名！");
		return false;
	}else{
		var flag = verifyUsername(username);
		if(flag && username != $("#username").val()){
			showMessage(showBox,null,publicbox_close,"用户名已存在！");
			return false;
		}
	}
	if(password == undefined || password=='' || password==null){
		showMessage(showBox,null,publicbox_close,"请输入密码！");
		return false;
	}else if(!/^[\s\S]{6,12}$/.test(password)){
		showMessage(showBox,null,publicbox_close,"密码请限制在6-12位之间！");
		return false;
	}
	if(roleId == undefined || roleId == '' || roleId == 0){
		showMessage(showBox,null,publicbox_close,"请选择角色！");
		return false;
	}
	
	return true;
}

//添加用户
function addAccount(type){
	$.ajax({
		url : ctx+"/systemManage/toAddAccount",
		type : "post",
		data : {
			username : username,
			password : password,
			comment : comment,
			roleId : roleId,
			type : type,
			realName : realName,
			courseIds : courseIds,
			unitId : unitId
		},
		dataType : 'json',
		async: false,
		success : function(data){
			showMessage(function(){
				initAccountInfo();
				/*$("#all_letter_1").attr("class","hovertab_mywork");
				$("#all_letter_2").attr("class","nor_mywork");
				$("#all_letter_3").attr("class","nor_mywork");
				$("#all_letter_01").attr("class","all_letter_con");
				$("#all_letter_02").attr("class","unall_letter_net");
				$("#all_letter_03").attr("class","unall_letter_net");*/
				publicbox_close();
			},null,publicbox_close,"用户添加成功！");
		}
	});
}

//校验用户名是否存在
function verifyUsername(username){
	var flag=false;
	$.ajax({
		url : ctx + "/systemManage/findByUsername",
		type : "post",
		data : {
			username:username
		},
		async: false,
		dataType : 'json',
		success : function(data){
			if(data.code == "200"){
				//showMessage(showBox,null,publicbox_close,"用户名已存在！");
				flag=true;
			}
		}
	});
	return flag;
}

/**
 * 查找用户是否存在
 * @param accountId
 * @returns
 */
function findAccountById(accountId){
	var dataInfo;
	$.ajax({
		url : ctx + "/systemManage/findAccountById",
		type : "post",
		data : {
			id:accountId
		},
		async: false,
		dataType : 'json',
		success : function(data){
			if(data.code == "200"){
				//showMessage(publicbox_close,null,publicbox_close,"该角色已不存在！");
				//dataInfo=data.manageAccount;
				dataInfo = data;
			}
		}
	});
	return dataInfo;
}



//确认是否删除账户
function toDeleteAccount(accountId,type){
	showMessage(function(){
		deleteAccount(accountId,type);
	},publicbox_close,publicbox_close,"确认要删除吗！");
}

//删除账户
function deleteAccount(accountId,type){
	publicbox_close();
	var dataInfo = findAccountById(accountId);
	if((dataInfo != undefined || dataInfo != null) && dataInfo.manageAccount != null){
		$.ajax({
			url : ctx + "/systemManage/deleteAccount",
			type : "post",
			data : {
				id : accountId,
				type : type
			},
			dataType : 'json',
			async: false,
			success : function(data){
				showMessage(function(){initAccountInfo();publicbox_close();},null,publicbox_close,"删除成功！");
			}
		});
	}else{
		showMessage(function(){
			initAccountInfo();
			publicbox_close();
			},null,publicbox_close,"该用户已不存在！");
	}
}
//更新账户
function updateAccount(type){
	var accountId = $("#accountId").val();
	$.ajax({
		url : ctx+"/systemManage/toUpdateAccount",
		type : "post",
		data : {
			id : accountId,
			username : username,
			password : password,
			comment : comment,
			roleId : roleId,
			type : type,
			realName : realName,
			courseIds : courseIds,
			unitId : unitId
		},
		dataType : 'json',
		async: false,
		success : function(data){
			showMessage(function(){
				initAccountInfo();
				/*$("#all_letter_1").attr("class","hovertab_mywork");
				$("#all_letter_2").attr("class","nor_mywork");
				$("#all_letter_3").attr("class","nor_mywork");
				$("#all_letter_01").attr("class","all_letter_con");
				$("#all_letter_02").attr("class","unall_letter_net");
				$("#all_letter_03").attr("class","unall_letter_net");*/
				publicbox_close();
			},null,publicbox_close,"用户更新成功！");
		}
	});
}

/**
 * 重写弹框事件
 */
function payforbox_over(accountId,type) {
	//清空数据
	clearAll();
	//新增用户数据
	if (accountId == 0) {
		$("#type").val("1");
		payforbox();
	} else {//更新用户数据
		$("#add_account_apan").css("display","none");
		$("#edit_account_apan").css("display","block");
		$("#type").val("2");
		$("#accountId").val(accountId);
		var dataInfo = findAccountById(accountId);
		if((dataInfo != undefined || dataInfo != null) && dataInfo.manageAccount != null){
			editAll(dataInfo);
		}else{
			showMessage(function(){
				initAccountInfo();
				publicbox_close();
				},null,publicbox_close,"该用户已不存在！");
		}
		switch (type) {
		case 1:
			$("#pubfoe1").css("display","block");
			$("#pubfoe2").css("display","none");
			$("#pubfoe3").css("display","none");
			$("#topic_type1").attr("checked",true);
			payforbox();
			break;
		case 2:
			$("#pubfoe1").css("display","none");
			$("#pubfoe2").css("display","block");
			$("#pubfoe3").css("display","none");
			$("#topic_type2").attr("checked",true);
			payforbox();
			break;
		case 3:
			$("#pubfoe1").css("display","none");
			$("#pubfoe2").css("display","none");
			$("#pubfoe3").css("display","block");
			$("#topic_type3").attr("checked",true);
			payforbox()
			break;
		default:
			break;
		}
	}
}

//清空数据
function clearAll(){
	$("#add_account_apan").css("display","block");
	$("#edit_account_apan").css("display","none");
	$("#pubfoe1").css("display","block");
	$("#pubfoe2").css("display","none");
	$("#pubfoe3").css("display","none");
	$("#topic_type1").attr("checked",true);
	$("#topic_type2").attr("checked",false);
	$("#topic_type3").attr("checked",false);
	$("#username").val("");
	$("#accountId").val("");
	$("#type").val("");
	$("#iotek_role").val("");
	$("#iotek_name").val("");
	$("#iotek_password").val("");
	$("#iotek_comment").val("");
	$("#college_role").val("");
	$("#college_name").val("");
	$("#college_password").val("");
	$("#college_comment").val("");
	$("#college_realname").val("")
	$("input:checkbox[name=CheckboxGroup1]:checked").each(function(){  
		$(this).attr("checked",false);  
	});
	$("#college_unitId").val("");
	$("#company_role").val("");
	$("#company_username").val("");
	$("#company_password").val("");
	$("#company_comment").val("");
	$("#company_realName").val("")
	$("input:checkbox[name=CheckboxGroup2]:checked").each(function(){  
		$(this).attr("checked",false);    
	});
	$("#company_company").val("");
}

/**
 * 填充用户数据
 */
function editAll(dataInfo){
	var courseIds = dataInfo.courseIds;
	var result=courseIds.split(",");
	$("#username").val(dataInfo.manageAccount.username);
	$("#iotek_role").attr("value",dataInfo.roleId);
	$("#iotek_name").val(dataInfo.manageAccount.username);
	$("#iotek_password").val("******");//默认给六个*
	$("#iotek_comment").val(dataInfo.manageAccount.comment);
	$("#college_role").attr("value",dataInfo.roleId);
	$("#college_name").val(dataInfo.manageAccount.username);
	$("#college_password").val("******");//默认给六个*
	$("#college_comment").val(dataInfo.manageAccount.comment);
	$("#company_role").attr("value",dataInfo.roleId);
	$("#company_username").val(dataInfo.manageAccount.username);
	$("#company_password").val("******");//默认给六个*
	$("#company_comment").val(dataInfo.manageAccount.comment);
	$("#college_unitId").attr("value",dataInfo.manageAccount.unitId);
	$("#college_realname").val(dataInfo.manageAccount.realName);
	for(var i=0;i<result.length;i++){
		$("input:checkbox[name=CheckboxGroup1][value='"+result[i]+"']").attr('checked', 'true');
	}
	$("#company_company").attr("value",dataInfo.manageAccount.unitId);
	$("#company_realName").val(dataInfo.manageAccount.realName)
	for(var i=0;i<result.length;i++){
		$("input:checkbox[name=CheckboxGroup2][value='"+result[i]+"']").attr('checked', 'true');
	}
}