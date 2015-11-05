/*
 * 胡静
 */
var typeId=1;
var name = "";
var roleId=0;
//页面加载完成后执行该方法
$(document).ready(function() {
	$("#submit_btn").click(function(){
		roleId = $("#roleId").val();
		typeId = $("#manageRoleType").val();
		name = $("#role_name").val();
		$("#payforpbbg").hide();
		$("#payforpbbox").hide();
		if(name == undefined || name=='' || name==null){
			showMessage(showBox,publicbox_close,publicbox_close,"名字不能为空！");
			return;
		}else{
			var flag = verifyName(name,typeId);
			if(flag && name != $("#input_name").val()){
				showMessage(showBox,null,publicbox_close,"用户名已存在！");
				return;
			}
			if(roleId == 0){
				addRole();
			}else{//更新
				updateRole();
			}
		}
	});

	/**
	 * 
	 * 获取管理员类型
	 */
	$("#role_ul").find("li").each(function(){
		$(this).click(function(){
			typeId = $(this).attr("typeId");
		});
	});
	initRolesInfo();


});

/**
 * 初始化页面数据
 */
function initRolesInfo() {
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
		url : ctx + "/systemManage/showRole",
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
		url : ctx + "/systemManage/showRole",
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
		url : ctx + "/systemManage/showRole",
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
		url : ctx + "/systemManage/showRole",
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
	$(list).each(function (i,role){
		if(role.name == 'administrator'){
			contentshow += "<tr>"+
			"<td>"+role.name+"</td><td></td></tr>";
		}else{
			contentshow +="<tr>"+
			"<td>"+role.name+"</td>"+
			"<td class='operation_td'><a href='javascript:payforbox_override("+role.id+");'>编辑</a><a href='"+ctx+"/systemManage/distribute?id="+role.id+"' target='_blank'>分配权限</a><a href='javascript:toDeleteRole("+role.id+")'>删除</a></td>"+
			"</tr>"
		}
	});
	$("#"+tbody).html(contentshow);
}

//重写弹出框函数
function payforbox_override(roleId){
	clearAll();
	//新增
	$("#roleId").val(roleId);
	if(roleId == 0){
		payforbox();
	}else{//编辑
		var dataInfo = findById(roleId);
		if(dataInfo != null){
			$("#role_name").val(dataInfo.name);
			$("#manageRoleType").attr("value",dataInfo.type);
			$("#input_name").val(dataInfo.name);
			payforbox();
		}else{
			showMessage(function(){
				initRolesInfo();
				publicbox_close();
			},null,publicbox_close,"该角色已不存在！");
		}
	}

}

//添加新角色
function addRole(){
	$.ajax({
		url : ctx+"/systemManage/addRole",
		type : "post",
		data : {
			type : typeId,
			name : name
		},
		dataType : 'json',
		success : function(data){
			showMessage(function(){
				initRolesInfo();
				publicbox_close();
			},null,publicbox_close,"新角色添加成功！");
		}
	}
	);
}
//更新角色
function updateRole(){
	$.ajax({
		url : ctx+"/systemManage/updateRole",
		type : "post",
		data : {
			type : typeId,
			name : name,
			id : roleId
		},
		dataType : 'json',
		success : function(data){
			showMessage(function(){
				initRolesInfo();
				publicbox_close();
			},null,publicbox_close,"角色更新成功！");
		}
	});
}

function toDeleteRole(roleId){
	showMessage(function(){
		deleteRole(roleId);
		},publicbox_close,publicbox_close,"确定要删除该角色吗！");
}
function deleteRole(roleId){
	publicbox_close();
	var dataInfo = findById(roleId);
	if(dataInfo != null){
		$.ajax({
			url : ctx+"/systemManage/deleteRole",
			type : "post",
			data : {
				id : roleId
			},
			dataType : 'json',
			success : function(data){
				if(data.code == "200"){
					showMessage(function(){
						initRolesInfo();
						publicbox_close();
					},null,publicbox_close,"角色删除成功！");
				}else{
					showMessage(publicbox_close,null,publicbox_close,"该角色正在使用，删除失败！");
				}
			}
		});
	}else{
		showMessage(function(){
			initRolesInfo();
			publicbox_close();},null,publicbox_close,"该角色已不存在！");
	}

}
//验证名字是否存在
function verifyName(name,type){
	var flag=false;
	$.ajax({
		url : ctx + "/systemManage/verifyName",
		type : "post",
		data : {
			name:name,
			type:type
		},
		async: false,
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
 * 验证当前角色是否还存在
 * @param id
 * @returns
 */
function findById(id){
	var dataInfo;
	$.ajax({
		url : ctx + "/systemManage/findById",
		type : "post",
		data : {
			id:id
		},
		async: false,
		success : function(data){
			if(data.code == "200"){
				//showMessage(publicbox_close,null,publicbox_close,"该角色已不存在！");
				dataInfo=data.manageRole;
			}
		}
	});
	return dataInfo;
}

//显示弹出框
function showBox(){
	$("#payforpbbg").show();
	$("#payforpbbox").show();
	publicbox_close();
}

//清空所有选项
function clearAll(){
	$("#role_name").val("");
	$("#input_name").val("");
	$("#manageRoleType").attr("value",'1');
}

