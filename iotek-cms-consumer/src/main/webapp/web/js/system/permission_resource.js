/**
 * 权限资源js
 * */
/**
 * 1:新增,2：修改
 */
var type = 0;

/**
 * 父资源id 
 */
var parent;

/**
 * 当前资源id
 */
var resource;

/**
 * 编辑前资源名称
 */
var oldName;

/**
 * 编辑前的url
 */
var oldUrl;

$(function (){ 
	$("#first_save_btn").bind("click",save_resource_info);
	$("#second_save_btn").bind("click",save_resource_info);
	//绑定光标移除事件
	$("input[id*='0x00'][type='text']").each(function(index,item) {
		$("#"+item.id).bind("blur",validUrlUsed(this));
		$("#"+item.id).bind("focus",dealOldUrl(this));
	});
	
});

function dealOldUrl(obj) {
	return function () {
		oldUrl = $("#"+obj.id).val();
	};
}

/**
 * 校验url是否被使用了
 * @param url
 */
function validUrlUsed(obj) {
	return function()
	{
		var url = $("#"+obj.id).val();
		var valNum = 1;
		var repeatObjId = '';
		
		//校验url合法性
		$("input[id*='0x00'][type='text']").each(function(index,item) {
			var urlStr = $("#"+item.id).val();
			if (urlStr != '') {
				if (urlStr.indexOf(",")) {
					var urlSp = urlStr.split(",");
					for (var int = 0; int < urlSp.length; int++) {
						if ((urlSp[int].indexOf("/") > 0) || 
								(urlSp[int].lastIndexOf("/") == (urlSp[int].length -1)) ||
								(urlSp[int].indexOf("/") == urlSp[int].lastIndexOf("/"))) {
							showMessage(publicbox_close, null, null, "资源路径格式不规范!");
							url = '';
							return ;
						}
					}
				} else {
					if ((urlStr.indexOf("/") > 0) || 
							(urlStr.lastIndexOf("/") == (urlStr.length -1)) ||
							urlStr.indexOf("/") == urlStr.lastIndexOf("/")) {
						showMessage(publicbox_close, null, null, "资源路径格式不规范!");
						url = '';
						return ;
					}
				}
			}
		});
		
		//校验当前页面不能有重复的url
		var flag = true;
		$("input[id*='0x00'][type='text'][value!='']").each(function(index,item) {
			if (flag) {
				$("input[id*='0x00'][type='text'][value!='']").each(function(index2,item2) {
					if (index != index2 && $("#"+item.id).val() == $("#"+item2.id).val()) {
						valNum ++;
						repeatObjId = item2.id;
						flag = false;
					}
				});
			} else {
				return false;
			}
		});
		/*if (valNum >= 2) {
			showMessage(function () {
				 $("#"+repeatObjId).focus();
				 publicbox_close();
			 }, null, null, "资源不能重复");
			return false;
		}
		
		//校验数据库中是否有重复的
		if (url != '' && valNum < 2) {
			//如果新的url中包含,，则只校验新的那一部分
			var newUrl = '/';
			if ((oldUrl+",") == url || oldUrl.indexOf(url+",") > -1 || url.indexOf(oldUrl+",") > -1) {
				if (oldUrl != '') {
					newUrl = "/";
				} else {
					newUrl = url;
				}
			} else if(oldUrl != url) {
				if(url.indexOf(",") > -1) {
					var urlSp = url.split(",");
					for (var int = 0; int < urlSp.length; int++) {
						if (urlSp[int] != '' && oldUrl != urlSp[int]) {
							newUrl += urlSp[int]+",";
						}
					}
				} else {
					newUrl = url;
				}
			}
			
			//校验url是否重复
			$.ajax({
				url : ctx + "/systemManage/permission/validResourceUrl",
				type : "post",
				data : {
					url: newUrl
				},
				dataType : 'json',
				async:false,
				success : function(data) {
					if (!data.valid) {
						showMessage(function () {
							 $("#"+obj.id).focus();
							 oldUrl = '';
							 publicbox_close();
						 }, null, null, "资源不能重复");
					}
				}
			});
		}*/
	};
}

/**
 * 保存资源信息
 */
function save_resource_info() {
	
	//获取页面数据
	var resourceName = "";
	var flag = false;
	if (parent > 0) {
		if ($.trim($("#second_input").val()) == '') { 
			showMessage(function () {
				publicbox_close();
			}, null, null, "资源名称不能为空!");
		} else  {
			flag = true;
			resourceName = $.trim($("#second_input").val());
		}
	} else {
		if ($.trim($("#first_input").val()) == '') { 
			showMessage(function () {
				publicbox_close();
			}, null, null, "资源名称不能为空!");
		} else {
			flag = true;
			resourceName = $.trim($("#first_input").val());
		}
	}
	
	//校验资源名称是否重复
	if (type == 2 && oldName == resourceName && parent == 0) {
		$("#popup_class_first").hide();
		$("#popup_class_second").hide();
		return false;
	} else {
		
		var formatFlag = true;
		//初步校验格式是否规范(至少含有两个//,且第一个/前无字符，第二个/后有字符)
		if (parent > 0 && flag) {
			//校验url规范性
			$("input[id*='0x00'][type='text']").each(function(index,item) {
				//
				var urlStr = $("#"+item.id).val();
				if (urlStr != '') {
					if (urlStr.indexOf(",")) {
						var urlSp = urlStr.split(",");
						for (var int = 0; int < urlSp.length; int++) {
							if ((urlSp[int].indexOf("/") > 0) || 
									(urlSp[int].lastIndexOf("/") == (urlSp[int].length -1)) ||
									(urlSp[int].indexOf("/") == urlSp[int].lastIndexOf("/"))) {
								showMessage(publicbox_close, null, null, "资源路径格式不规范!");
								formatFlag = false;
								flag = false;
								return ;
							}
						}
					} else {
						if ((urlStr.indexOf("/") > 0) || 
								(urlStr.lastIndexOf("/") == (urlStr.length -1)) ||
								urlStr.indexOf("/") == urlStr.lastIndexOf("/")) {
							showMessage(publicbox_close, null, null, "资源路径格式不规范!");
							formatFlag = false;
							flag = false;
							return ;
						}
					}
				}
			});
		}
		
		if (formatFlag && parent == 0) {
			//验证是否重复
			$.ajax({
				url : ctx + "/systemManage/permission/validResourceName",
				type : "post",
				data : {
					name: resourceName
				},
				dataType : 'json',
				async:false,
				success : function(data) {
					if (!data.valid) {
						showMessage(function () {
							publicbox_close();
						}, null, null, "资源名称不能重复!");
					}
					flag = data.valid;
				}
			});
		}
	}
	if (flag) {
		var url  = ctx + "/systemManage/permission/";
		//区分是否是编辑
		if(type == 1) { 
			url += "savePermissionResource";
		} else {
			url += "updatePermissionResource";
		}
		
		if (parent > 0) {
			$("#second_id_input").val(resource);
			$("#add_second_form").attr("action",url);
			$("#add_second_form").submit();
		} else {
			$("#first_id_input").val(resource);
			$("#add_first_form").attr("action",url);
			$("#add_first_form").submit();
		}
	}
	
}

/* *
 * 清空页面输入框
 */
function clearPermissionStr() {
	$("input[type='text']").each(function (index,item) {
		$(item).val("");
	});
}

/**
 * 更新菜单状态
 * @param id
 * @param state
 */
function updateResourceState(id,parentId,state) {
	$.ajax({
		url : ctx + "/systemManage/permission/updatePermissionState",
		type : "post",
		data : {
			resourceId: id,
			parentId: parentId,
			state: state
		},
		dataType : 'json',
		async:false,
		success : function(data) {
			location.href= ctx + "/systemManage/permissionResource";
		}
	});
}

/**
 * 删除菜单
 * @param id
 * @param state
 */
function deleteResource(id,parentId) {
	
	//校验链接是否被使用
	$.ajax({
		url : ctx + "/systemManage/permission/validResourceUse",
		type : "post",
		data : {
			resourceId: id,
			parentId: parentId
		},
		dataType : 'json',
		async:false,
		success : function(data) {
			if (data.valid) {
				//删除菜单及权限资源
				$.ajax({
					url : ctx + "/systemManage/permission/delResource",
					type : "post",
					data : {
						resourceId: id,
						parentId: parentId
					},
					dataType : 'json',
					async:false,
					success : function(data) {
						location.href= ctx + "/systemManage/permissionResource";
					}
				});
			} else {
				showMessage(publicbox_close, null, null, "资源正在使用中,不能删除!");
			}
		}
	});
	
	
}

/**
 * 新增资源
 * @param obj
 * @param parentId
 */
function showResourceDiv(obj,parentId) {
	clearPermissionStr();
	$("#"+obj).show();
	type = 1;
	resource = 0;
	parent = parentId;
	
	if (parentId > 0) {
		var parentName = $("#parent_resource_"+parentId).text();
		var content = "&nbsp;&nbsp;&nbsp;父类名称：&nbsp;";
		content += "<input id='parent_input' type='text'>";
		content += "</input>";
		content += "<input id='parent_id_input' type='hidden' name='parentId'>";
		content += "</input>";
		$("#parent_label").html(content);
		$("#parent_input").val(parentName);
		$("#parent_id_input").val(parentId);
		$("#parent_input").attr("disabled","disabled");
		$("#parent_input").bind("keydown",function () {
			$(this).attr("disabled","disabled");
			return;
		});
		
		/*$.ajax({
			url : ctx + "/systemManage/permission/dealResource",
			type : "post",
			data : {},
			dataType : 'json',
			async:false,
			success : function(data) {
				
				var content = "&nbsp;&nbsp;&nbsp;父类名称：&nbsp;";
					content += "<select id='parent_input' name='parentId'>";
				for (var int = 0; int < data.firstResources.length; int++) {
					var resouce = data.firstResources[int];
					content += "<option value='"+resouce.id+"'>";
					content += resouce.name;
					content += "</option>";
				}
				content += "</select>";
				$("#parent_label").html(content);
			}
		});*/
	}
	
}

/**
 * 编辑资源
 * @param obj
 * @param id
 * @param parentId
 */
function editResourceDiv(obj,id,parentId) {
	type  = 2;
	resource = id;
	parent = parentId;
	if (parentId > 0) {
		oldName = $("#second_resource_"+id).text();
		//组装页面父资源数据
		var parentName = $("#parent_resource_"+parentId).text();
		var content = "&nbsp;&nbsp;&nbsp;父类名称：&nbsp;";
		content += "<input id='parent_input' type='text'>";
		content += "</input>";
		content += "<input id='parent_id_input' type='hidden' name='parentId'>";
		content += "</input>";
		$("#parent_label").html(content);
		$("#parent_input").val(parentName);
		$("#parent_id_input").val(parentId);
		$("#parent_input").attr("disabled","disabled");
		$("#parent_input").bind("keydown",function () {
			$(this).attr("disabled","disabled");
			return;
		});
		
		$("#second_input").val($("#second_resource_"+id).text());
		//获取权限数据
		$.ajax({
			url : ctx + "/systemManage/permission/dealResource",
			type : "post",
			data : {
				parentId: id
			},
			dataType : 'json',
			async:false,
			success : function(data) {
//				showMessage(publicbox_close, null, null, "保存成功!");
				for (var int = 0; int < data.firstResources.length; int++) {
					var resouce = data.firstResources[int];
					$("#"+resouce.name+"_input").val(resouce.url);
					$("#"+resouce.name+"_id_input").val(resouce.id);
				}
			}
		});
	} else {
		$("#first_input").val($("#parent_resource_"+id).text());
		oldName = $("#parent_resource_"+id).text();
	}
	
	$("#"+obj).show();
}

/**
 * 隐藏div
 * @param obj
 */
function hideDiv(obj) {
	$("#"+obj).hide();
	clearPermissionStr();
}