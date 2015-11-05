
var name = '';
var url = '';
var openWay = '';
var id = '';
var file='';
$(function(){
	$("#word_name").blur(function(){changeName('word_name','picture_name');});
	$("#picture_name").blur(function(){changeName('picture_name','word_name');});
	$("#word_url").blur(function(){changeUrl('word_url','picture_url');});
	$("#picture_url").blur(function(){changeUrl('picture_url','word_url');});
	$("#word_open_way").change(function(){changeOpenWay('word_open_way','picture_open_way');});
	$("#picture_open_way").change(function(){changeOpenWay('picture_open_way','word_open_way');});
	$("#picture_title").blur(changeTitle);
	$("#word_ok_btn").click(function(){
		if(!changeName('word_name','picture_name') || !changeUrl('word_url','picture_url') || !changeOpenWay('word_open_way','picture_open_way')){
			return ;
		}
		id = $("#linkId").val();
		if(id == '' || id == undefined || id == null ){
			validLinkName('word_name','picture_name','word_form');
		}else{
			$("#word_form").submit();
		}
	});
	$("#picture_ok_btn").click(function(){
		if(!changeName('picture_name','word_name') || !changeUrl('picture_url','word_url') 
			|| !changeOpenWay('picture_open_way','word_open_way') || !changeTitle() || !changeFile()){
			return ;
		}
		id = $("#linkId").val();
		if(id == '' || id == undefined || id == null ){
			validLinkName('picture_name','word_name','picture_form');
		}else{
			$("#picture_form").submit();
		}
	});
	
	var swfu = new SWFUpload({
		upload_url : ctx + "/friendlyLinkManage/uploadLinkImage",
		file_post_name : "imgFile",
		// File Upload Settings
		file_size_limit : "5 mb",
		file_types : "*.gif;*.jpg;*.jpeg;*.png;*.bmp",
		file_types_description : "图片",
		file_queue_limit : 0,
		file_upload_limit : 0,
		custom_settings : {
			progressTarget : "progress_span",
			cancelButtonId : "btnCancel"
		},
		// Button Settings
		button_placeholder_id : "link_picture",
		button_width : 91,
		button_height : 29,
		button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor : SWFUpload.CURSOR.HAND,
		// Flash Settings
		flash_url : ctx + "/web/js/swfupload/swfupload.swf",
		//自定义class
		class_name : "swfupload",
		// Debug Settings
		debug : false, // 是否显示调试窗口

		// The event handler functions are defined in handlers.js
		swfupload_loaded_handler : swfUploadLoaded,
		//file_queued_handler : homeworkFileQueued,
		file_queue_error_handler : fileQueued,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadFriendlyLinkSuccess,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete, // Queue plugin event\

		// SWFObject settings
		minimum_flash_version : "9.0.28",
		swfupload_pre_load_handler : swfUploadPreLoad,
		swfupload_load_failed_handler : swfUploadLoadFailed

	});
	
	$("#SWFUpload_0").attr("class","swfupload_1");
	/**
	 * 上传作业回调函数
	 * @param file
	 * @param serverData
	 */
	function uploadFriendlyLinkSuccess(file, serverData) {
		// 作业上传成功标识
		homework_uplaod_flag = true;
		//去除自动添加的所有"
		serverData = serverData.replace(/"/g,"");
		if (serverData != '500') {
			$("#inputPicture").val(serverData.split(":")[1]);//设置文件路径
			$("#link_picture_src").attr("src",img_suffix+serverData.split(":")[1]);//设置完整路径
			$("#link_picture_src").show();
		}
	}
});

/**
 * 验证用户名称唯一性
 * @param nameId1
 * @param nameId2
 */
function validLinkName(nameId1,nameId2,formId){
	//判断该链接名称是否使用
	$.ajax({
		url:ctx+"/friendlyLinkManage/validLinkName",
		type:"post",
		data:{
			name:name
		},
		dataType : 'json',
		success : function(data) {
			if (data == '200') {
				$("#"+nameId1).val("");
				$("#"+nameId2).val("");
				showMessage(function () {
					publicbox_close();	
				}, null, publicbox_close, "友情链接名称不能重复");
			}else{
				$("#"+formId).submit();
			}	
		}
	});

}

/**
 * 校验链接名称是否为空
 * @param nameId1
 * @param nameId2
 * @returns {Boolean}
 */
function changeName(nameId1,nameId2){
	name = $("#"+nameId1).val();
	$("#"+nameId2).val(name);
	if(name == undefined || name == "" || name == null){
		showMessage(publicbox_close, null,publicbox_close,"链接名称不能为空！")
		return false;
	}
	return true;
}
/**
 * 校验链接地址是否为空
 * @param nameId1
 * @param nameId2
 * @returns {Boolean}
 */
function changeUrl(urlId1,urlId2){
	url = $("#"+urlId1).val();
	$("#"+urlId2).val(url);
	if(url == undefined || url == "" || url == null){
		showMessage(publicbox_close, null,publicbox_close,"链接地址不能为空！")
		return false;
	}
	if(!isURL(url)){
		showMessage(publicbox_close, null,publicbox_close,"链接地址不合法！")
		return false;
	}
	return true;
}

/**
 * 校验打开方式是否为空
 * @param nameId1
 * @param nameId2
 * @returns {Boolean}
 */
function changeOpenWay(openWayId1,openWayId2){
	openWay = $("#"+openWayId1).val();
	$("#"+openWayId2).val(openWay);
	if(openWay == undefined || openWay == "" || openWay == null){
		showMessage(publicbox_close, null,publicbox_close,"打开方式不能为空！")
		return false;
	}
	return true;
}

/**
 * 校验title是否为空
 * @param nameId1
 * @param nameId2
 * @returns {Boolean}
 */
function changeTitle(){
	title = $("#picture_title").val();
	if(title == undefined || title == "" || title == null){
		showMessage(publicbox_close, null,publicbox_close,"鼠标移动显示不能为空！")
		return false;
	}
	return true;
}

/**
 * 校验title是否为空
 * @param nameId1
 * @param nameId2
 * @returns {Boolean}
 */
function changeFile(){
	file = $("#inputPicture").val();
	if(file == undefined || file == "" || file == null){
		showMessage(publicbox_close, null,publicbox_close,"文件未上传,请重新上传！")
		return false;
	}
	return true;
}



