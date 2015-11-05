/**
 * 验证并发登陆
 */
function verifyLogOutInfo() {
	var alert_info = $.cookie('alert_info');
	if ($.trim(alert_info) != '') {
		jNotify(alert_info + '<br/>', {
			autoHide : true, // added in v2.0
			clickOverlay : true, // added in v2.0
			MinWidth : 250,
			TimeShown : 5000,
			ShowTimeEffect : 200,
			HideTimeEffect : 200,
			LongTrip : 20,
			HorizontalPosition : 'right',
			VerticalPosition : 'bottom',
			ShowOverlay : true,
			ColorOverlay : '#000',
			OpacityOverlay : 0.3,
			onClosed : function() { // added in v2.0

			},
			onCompleted : function() { // added in v2.0

			}
		});
		// 删除对应的cookie信息
		$.removeCookie('alert_info', {
			path : '/'
		});
	}
}

$(function () {
	verifyLogOutInfo();
});

/**
 * 简单提交校验
 */
function validSubmit() {
	
	$("#errorLogin").hide();
	if ($("#username").val() == '') {
		showMessage(publicbox_close, null, null, "用户名不能为空!");
		return false;
	}
	
	if ($("#password").val() == '') {
		showMessage(publicbox_close, null, null, "密码不能为空!");
		return false;
	}
	return true;
}