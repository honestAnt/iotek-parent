/**
 * 权限分配js
 * */

//权限字符串,以逗号拼接
var permissionStr = '';

/**
 * 计算页面上选中的权限字符串
 */
function calPermissionStr() {
	permissionStr = '';
	$(".free_learning input").each(function (index,item) {
		if (document.getElementById(item.id).checked) {
			permissionStr += item.value + ",";
		}
	});
	
	if (permissionStr == '') {
		showMessage(publicbox_close, null, null, "请选择权限!");
	} else {
		$.ajax({
			url : ctx + "/systemManage/saveRoleResource",
			type : "post",
			data : {
				roleId : $("#roleId").val(),
				permissionStr: permissionStr
			},
			dataType : 'json',
			async:false,
			success : function(data) {
				showMessage(publicbox_close, null, null, "保存成功!");
			},
			failure:function () {
				showMessage(publicbox_close, null, null, "服务器保存数据错误!");
			}
		});
	}
}

/**
 * 清空页面上选中的权限字符串
 */
function clearPermissionStr() {
	
	permissionStr = '';
	$(".free_learning input").each(function (index,item) {
		if (document.getElementById(item.id).checked) {
			document.getElementById(item.id).checked = false;
		}
	});
}

/**
 * 选中同级权限中的查看
 */
function checkFirst(va) {
	//获取当前选中的li标签下所有的同级input标签
	$("#"+va.id).parent('label').parent('li').find("label input").each(function (index,item) {
		if (document.getElementById(item.id).checked) {
			$("#"+va.id).parent('label').parent('li').find("label input")[0].checked = true;
			return false;
		}
	});
}