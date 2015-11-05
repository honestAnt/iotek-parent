$(function () {
	$("#save_btn").click(validSubmit);
});

function validSubmit() {
	var imgUrl = $.trim($("#imgUrl").val());
	var sortNumber = $.trim($("#sortNumber").val());
	var imgPath = $.trim($("#imgPath").val());
	if (imgUrl == '') {
		showMessage(publicbox_close, null, null, "图片链接不能为空!");
		return;
	} else {
		if (!checkUrl(imgUrl)) {
			showMessage(publicbox_close, null, null, "图片链接格式不正确!");
			return;
		}
	}
	if (sortNumber == '') { //判断非空
		showMessage(publicbox_close, null, null, "排序不能为空!");
		return;
	} else if (!/^\d{1,2}$/.test(sortNumber)) {//判断是否为数字
		var value = parseInt(sortNumber);
		showMessage(publicbox_close, null, null, "排序号应该在0~99之间!");
		return;
	}
	if (imgPath == '') {
		showMessage(publicbox_close, null, null, "背景图片不能为空!");
		return;
	}
	if ($("#homeBannerId").val() == '') {
		$("#homeBannerId").val(0);
	}
	
	//提交数据
	$("#banner_form").submit();
}

/**
 * 删除信息
 * @param id
 */
function delInfo(id) {
	showMessage(function () {
		$.ajax({
			url: ctx+"/banner/delBannerInfo",
			type : "post",
			data: {
				id:id
			},
			dataType:'json',
			async:false,
			success : function(data){
				publicbox_close();
				$("#listForm").submit();
			}
		});
	}, publicbox_close, publicbox_close,"你确定要删除此条信息?");
}