var uploader;

$(function () {
	uploader = new plupload.Uploader({
		//运行环境设置
		runtimes : 'html5,flash,silverlight,html4',
		//绑定文件对话框
		browse_button : 'uploadFile', 
		//文件列表容器
		container: document.getElementById('container'),
		//上传地址
		url : ctx+'/banner/uploadBannerImage',
		//flash上传方式
		flash_swf_url : ctx+'/web/js/plubload/Moxie.swf',
		//silverlight上传方式
		silverlight_xap_url : ctx+'/web/js/plubload/Moxie.xap',
		//是否允许多文件上传
		multipart: true,
		//是否允许选择多文件
		multi_selection: false,
		//上传文件名称
		file_data_name: 'file',
		// 上传文件条件
		filters : {
			max_file_size : '10mb',
			mime_types: [
			             {title : "Image files", extensions : "jpg,gif,png"},
			             ]
		},
		
		init: {
			//选择文件以后触发
			FilesAdded: function(up, files) {
				uploader.start();
			},
			
			//上传进度回调函数
			UploadProgress: function(up, file) {
				$("#uploadFilePercent").show();
				$("#uploadFilePercent").text(file.percent+"%");
			},
			
			//上传某一个文件成功后触发
			FileUploaded:function (uploader,file,responseObject) {
				//文件上传成功后返回的响应数据
				var responseData = eval('('+responseObject.response+')');
				if (responseData.imgPath != '') {
					$("#banner_picture_src").show();
					$("#imgPath").val(responseData.imgPath);
					$("#banner_picture_src").attr("src",img_suffix+responseData.imgPath);
				}
			},
			
			//列表文件都上传完后触发
			UploadComplete:function (up,files) { 
			},
			//文件上传失败后回调函数
			Error: function(up, err) {
				//上传文件失败
				if (err.code === plupload.INIT_ERROR) {
					showMessage(publicbox_close,null,publicbox_close,"上传文件失败!");
				}
				//文件格式不匹配
				if (err.code === plupload.FILE_EXTENSION_ERROR) {
					showMessage(publicbox_close,null,publicbox_close,"上传文件类型不合法(jpg,gif,png).");
				}
				//上传文件过大
				if (err.code === plupload.FILE_SIZE_ERROR) {
					showMessage(publicbox_close,null,publicbox_close,"上传文件过大(10mb以内).");
				}
			}
		}
	});
	uploader.init();
});