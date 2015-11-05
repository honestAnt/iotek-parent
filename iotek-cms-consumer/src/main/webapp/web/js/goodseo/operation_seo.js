

/**
 * 点击确定按钮
 */
$(function(){
	
	$("#operation_btn").click(function(){
		
		var seoTitle = $("#seoTitle").val();
		if(seoTitle==""){
			$("#seoTitle_msg").text("请填写标题!");
			$("#seoTitle_msg").show();
			return false;
		}
		hideMsg('seoTitle_msg');
		
		var seoKeywords = $("#seoKeywords").val();
		if(seoKeywords==""){
			$("#seoKeywords_msg").text("请填写关键字!");
			$("#seoKeywords_msg").show();
			return false;
		}
		hideMsg('seoKeywords_msg');
		
		var seoKeywordsDesc = $("#seoKeywordsDesc").val();
		if(seoKeywordsDesc==""){
			$("#seoKeywordsDesc_msg").text("请填写关键字描述!");
			$("#seoKeywordsDesc_msg").show();
			return false;
		}
		hideMsg('seoKeywordsDesc_msg');
		
		var msg="SEO信息添加成功并提交审核!";
		if(updateFlag){
			msg="SEO信息更新成功并提交审核!";
		}
		
		var operation_url = "/goodsSeoInfo/addGoodsSeoInfo";

		if(seoStatus !=''){
			if(seoStatus==2){
				operation_url = "/goodsSeoInfo/submitGoodsSeoInfo";
			}
			if(seoStatus==3){
				operation_url = "/goodsSeoInfo/updateGoodsSeoInfo";
			}
		}
		
		$.ajax({
			url: ctx + operation_url, 
			type: 'post',
			data: {
				updateFlag: updateFlag,
				seoStatus: seoStatus,
				seoId: seoId,
				goodsId: goodsId,
				seoTitle: seoTitle,
				seoKeywords: seoKeywords,
				seoKeywordsDesc: seoKeywordsDesc
			},
			dataType: 'json',
			async: false,
			success:function(data){
				if(data != undefined && data >0 ){
					showMessage(function() {
						location.href= ctx+'/goodsSeoInfo/goodsSeoListIndex';
						publicbox_close();
					},null,publicbox_close,msg);
				}else{
					showMessage(publicbox_close,null,publicbox_close,'操作异常,请重试!');
					return false;
				}
			}
		});
	});
	
	
	/**
	 * 点击 发布 按钮
	 */
	$("#seo_audit_btn").click(function(){
		showMessage(
			function() {
				publicbox_close(); 
				auditSeoInfo();
			}, publicbox_close, publicbox_close, "发布后SEO信息将在前台显示,确认发布?");
	});
	
	/**
	 * 点击确认退回按钮
	 */
	
	$("#confirm_back_btn").click(function(){
		var seoBackReason = $("#seoBackReason").val();
		if(seoBackReason==''){
			$("#seoBackReason_msg").text("请填写退回理由!");
			$("#seoBackReason_msg").show();
			return false;
		}
		
		var seoId = $("#seoId").val();
		
		$.ajax({
			url: ctx + "/goodsSeoInfo/sendBackGoodsSeoInfo",
			type: 'post',
			data: {
				seoId: seoId,
				seoBackReason: seoBackReason
			},
			dataType: 'json',
			async: false,
			success:function(data){
				if(data != undefined && data > 0){
					$('#payforpbbg').hide();
					$('#payforpbbox').hide();
					
					showMessage(
						function() {
							location.href= ctx+'/goodsSeoInfo/goodsSeoAuditListIndex';
							publicbox_close();
					}, null, publicbox_close, "SEO信息已退回!");
				}else{
					showMessage(publicbox_close,null,publicbox_close,'操作异常,请重试!');
					return false;
				}
			}
		});
	});
});



/**
 *确认审核发布商品SEO信息
 */
function auditSeoInfo(){
	var seoStatus = $("#seoStatus").val();
	var seoId = $("#seoId").val();
	
	$.ajax({
		url: ctx + "/goodsSeoInfo/auditGoodsSeoInfo",
		type: 'post',
		data: {
			seoId: seoId
		},
		dataType: 'json',
		async: false,
		success:function(data){
			if(data > 0 && data != undefined){
				showMessage(
					function() {
						location.href= ctx+'/goodsSeoInfo/goodsSeoAuditListIndex';
						publicbox_close();
				}, null, publicbox_close, "SEO信息发布成功!");
			}else{
				showMessage(publicbox_close,null,publicbox_close,'操作异常,请重试!');
				return false;
			}
		}
	});
}



/**
 * 隐藏错误信息提示
 * @param id
 */
function hideMsg(id){
	$("#"+id).text("");
	$("#"+id).hide();
}