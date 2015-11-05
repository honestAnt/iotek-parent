
/**
 * 定义页面参数变量
 */
var firstLevelId,secondLevelId,thirdLevelId,startTime,endTime,searchStr;

/**
 * 每个商品状态页的页码
 */
var onTheShelfPageNum,offTheShelfPageNum,notPassPageNum,notTheShelfPageNum,pageNumber;


function formSumint(status) {
	$("#status").val(status);
	$("#informationList").submit();
}

$(function(){
	/**
	 * 分类一选中事件
	 */
	$("#firstLevelId").change(function(){
		var firstLevelId = $("#firstLevelId").val();
		if(firstLevelId !=0){
			$.ajax({
				url: ctx+"/information/secondLevel",
				type : "post",
				data: {
					levelId:firstLevelId
				},
				dataType:'json',
				async:false,
				success : function(data){
					var definedHTML = "";
					definedHTML += "<option value='0'>请选择</option>";
					$.each(data.list, function(i, secondLevel){
						definedHTML += "<option value=" + secondLevel.id;
						if ($("#second_level_id_input").val() == secondLevel.id) {
							definedHTML += " selected='true' ";
						}
						definedHTML +=  ">" + secondLevel.sortName + "</option>";
					});
					$("#secondLevelId").html(definedHTML);
				}
				
			});
		}else{
			var definedHTML = "<option value='0'>请选择</option>";
			$("#second_level_id").html(definedHTML);
		}
	});
	
	
	
	/**
	 * 点击添加按钮
	 */
	$("#addButton").click(function(){
		
		if($("#firstLevelId").val()==-1){
			showMessage(publicbox_close,null,publicbox_close,"请选择一级分类!");
			return;
		}
		if($("#title").val()==null||$.trim($("#title").val())==''){
			showMessage(publicbox_close,null,publicbox_close,"标题不能为空!");
			return;
		}
		if($("#keywords").val()==null||$.trim($("#keywords").val())==''){
			showMessage(publicbox_close,null,publicbox_close,"关键字不能为空!");
			return;
		}
		if($("#description").val()==null||$.trim($("#description").val())==''){
			showMessage(publicbox_close,null,publicbox_close,"备注不能为空!");
			return;
		}
		
		var s =unescape(editor.html());
		if(s==null || $.trim(s)==''){
			showMessage(publicbox_close,null,publicbox_close,"内容不能为空!");
			return;
		}
		$("#content").val(s);
		$("#informationForm").submit();
	});
	
	
	//如果分类一选中的 话
	if ($("#first_level_id_input").val() != '') {
		firstLevelId = $("#first_level_id_input").val();
		$("#firstLevelId").trigger("change");
	}
	
	
});



/**
 * 咨询二级分类seo
 */
function secondLevelChange(){
	var secondLevelId = $("#secondLevel_select").val();
	if(firstLevelId !=0){
		$.ajax({
			url: ctx+"/information/secondLevelSeo",
			type : "post",
			data: {
				levelId:secondLevelId
			},
			dataType:'json',
			async:false,
			success : function(data){
				$("#seoTitle").val(data.seoTitle);
				$("#seoKeywords").val(data.seoKeywords);
				$("#seoDescr").val(data.seoDescr);
			}
			
		});
	}else{
		var definedHTML = "<option value='0'>请选择</option>";
		$("#secondLevel_select").html(definedHTML);
	}
}


/**
 * 点击删除按钮，删除咨询信息
 * @param id
 * @param status
 */
function deleteInformation(id,status){
	showMessage(function(){
		$.ajax({
			url: ctx + "/information/deleteInformation",
			type: 'post',
			data: {
				id:id,status:status
			},
			dataType: 'json',
			async: false,
			success:function(data){
				$("#status").val(status);
				$("#informationList").submit();
			}
		});
	},publicbox_close,publicbox_close,"确定要删除?");
	
}

/**
 * 发布处理
 */
function releaseButtons(id,status){
	showMessage(function(){
		$.ajax({
			url: ctx + "/information/reviewInformation",
			type: 'post',
			data: {
				id:id,status:status
			},
			dataType: 'json',
			async: false,
			success:function(data){
				window.location.href=ctx + "/information/informationListReview";
			}
		}),publicbox_close();
	},publicbox_close,publicbox_close,"确定要发布?");
	
}

/**
 * 退回
 */
function returnButtons(id,status){
	var reason = "<p><span class='fl'>退回理由：</span></p><br><textarea  rows='5' style='width:100%;' name='reason' id='reason' maxlength=150></textarea>";
	showMessage(function(){
		var reasonText = $("#reason").val();
		if(reasonText==null||reasonText.trim()==""){
			showMessage(function(){publicbox_close()},null,function(){publicbox_close()},"退回理由不能为空！");
			return;
		}
		$.ajax({
			url: ctx + "/information/reviewInformation",
			type: 'post',
			data: {
				id:id,status:status,returnReason:reasonText
			},
			dataType: 'json',
			async: false,
			success:function(data){
				window.location.href=ctx + "/information/informationListReview";
			}
		}),publicbox_close();
	},null,publicbox_close,reason);
}

/**
 * 退回理由弹窗
 */
function returnReasonClick(returnReasonText){
	var reason = "<p><span class='fl'>退回理由：</span></p><br><textarea  rows='5' style='width:100%;'  name='reason' id='reason' readonly='true'>"+returnReasonText+"</textarea>";
	showMessage(function(){
		publicbox_close();
	},null,publicbox_close,reason);
}

function statusTypeDisabled(){
	var statusTypeDisabled = $("#statusTypeDisabled").val();
	if(statusTypeDisabled==0){
		$("#firstLevelId").attr("disabled","disabled");
		$("#secondLevelId").attr("disabled","disabled");
		$("#title").attr("disabled","disabled");
		$("#keywords").attr("disabled","disabled");
		$("#description").attr("disabled","disabled");
		$("#keywords").attr("disabled","disabled");
		$("#deliveryTime").attr("disabled","disabled");
		$("#source").attr("disabled","disabled");
		$("#featured").attr("disabled","disabled");
		$("#pageTags").attr("disabled","disabled");
		$("#titleSpan").hide();
		$("#keywordsSpan").hide();
		$("#descriptionSpan").hide();
	}
}

/**
 * 展示当前页面信息
 */
function showCurrentPage(id){
	$("div[id *='content_div_']").each(function (index,item){
		if(item.id == ('content_div_'+id)){
			$("#"+item.id).show();
		}else{
			$("#"+item.id).hide();
		}
	});
}



