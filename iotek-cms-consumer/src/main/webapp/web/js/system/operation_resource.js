
var old_url_edit=""; 


/* 弹出框效果*/
 function showDiv(id){
 	var div = document.getElementById(id);
 	$("#operate_type").text("add");
 	$("#title_operate").text("添加资源");
 	old_url_edit="";
 	div.style.display = "block";
 }
 // 隐藏
 function hideDiv(id){
 	var div = document.getElementById(id);
 	div.style.display = "none";
	$("#url").val("");
	$("#type").val("");
	$("#module").val("");
	$("#detail").val("");

	$("#error_msg").text("");
	$("#error_msg").hide();

	
 }
 
 

//获取页码信息，并初始化分页组件
function getPage(){
	$.ajax({
		url : ctx + "/systemManage/searchResourcesList",
		type : "post",
		data: {
		}, 
		dataType : 'json',
		success : function(data) {
			// 初始化页数和条数
			initPatination('turnpages',data.page.dataCount,data.page.pageSize);
		}
	});
}


/**
 * 分页方法，参数showPage为显示页面的容器ID，callbackfunction为回调函数名,totleCount为数据总条数，pageNum为每页显示条数
 */
function initPatination(showPage,dataCount,pagesize){
	$("#"+showPage).pagination(dataCount, {
		num_edge_entries: 2, //边缘页数
		num_display_entries: 4, //主体页数
		callback: pageselectCallback,
		items_per_page:pagesize, //每页显示1项
		prev_text:"<em class='Em_prev'></em>",
		next_text:"<em class='Em_next'></em>",
		link_to:"javascript:void(0)"
	});
}

//分页回调函数
function pageselectCallback(page_index, jq){
	$.ajax({
		url : ctx + "/systemManage/searchResourcesList",
		type : "post",
		data: {
			pageNo : page_index+1
		}, 
		dataType : 'json',
		success : function(data) {
			// 初始化页数和条数
			//initPatination('turnpages',data.page.dataCount,data.page.pageSize);
			var jointHTML = "";
			$.each(data.page.result,function(i,resource){
				jointHTML += "<tr>";
				jointHTML += "<td>"+(++i)+"</td>";
				jointHTML += "<td>"+resource.url+"</td>";
				jointHTML += "<td>"+resource.module+"</td>";
				jointHTML += "<td>"+resource.detail+"</td>";
				jointHTML += "<td> <a href='javascript:void(0);' id='editLink_"+resource.id+"'>编辑</a></td>";
				jointHTML += "</tr>";
			});
			$("#resource_list_tbody").html(jointHTML);
			
			$("a[id^='editLink_']").each(function (index,item) {
				/**
				 * 点击列表上的编辑按钮
				 */
				$("#"+item.id).bind("click",function () {
						var resourceId = (item.id).split("_")[1];
						$.ajax({
							url: ctx + "/systemManage/searchResourceCondition",
							type: 'post',
							data: {
								resourceId: resourceId
							},
							dataType: 'json',
							async: false,
							success: function(data){
								$("#title_operate").text("更新资源");
								$("#url").val(data.oResource.url);
								$("#type").find("option[value='"+data.oResource.type+"']").attr("selected",true);
								$("#module").val(data.oResource.module);
								$("#detail").val(data.oResource.detail);
								$("#operate_type").text("update");
								$("#resource_id").text(data.oResource.id);
								old_url_edit = data.oResource.url;
								$("#popup_class").show();
							}
							
						});
				});
			});
		}
	});
}



/**
 * 校验url唯一性
 */
function validUrl(){
	var url = $("#url").val();
	var validFlag = false;
	if(url == '' || url==null){
		$("#error_msg").text("url不能为空! ");
		$("#error_msg").show();
		return validFlag ;
	}
	
	var urlArray = $.trim(url).split("/");
	
	if(urlArray.length == 3 && urlArray[0]=='' && urlArray[1]!=''  && urlArray[2] !=''){
		if(old_url_edit != url){
			$.ajax({
				url: ctx+'/systemManage/validUniquenessUrl',
				type: 'post',
				data: {
					url:url
				},
				async: false,
				dataType: 'json',
				success: function(data){
					if(data.flag != undefined && data.flag==true){
						$("#error_msg").text("");
						$("#error_msg").hide();
						validFlag = true;
					}else{
						$("#error_msg").text("url已存在! ");
						$("#error_msg").show();
					}
				}
			});
		}else{
			$("#error_msg").text("");
			$("#error_msg").hide();
			validFlag =true;
		}
	}else{
		$("#error_msg").text("url格式不正确,请重新输入! ");
		$("#error_msg").show();
	}
	return validFlag;
}



$(function(){
	
	/**
	 * 点击添加按钮
	 */
	$("#add_btn").click(function(){
		var url = $("#url").val();
		var type = $("#type").val();
		var module = $("#module").val();
		var detail = $("#detail").val();
		var resourceId = $("#resource_id").text();
		var operate_type = $("#operate_type").text();
		
		if(url=='' || type=='' || module=='' || detail==''){
			$("#error_msg").text("请将信息填写完整! ");
			$("#error_msg").show();
			return false ;
		}
		//校验url唯一性
		if(!validUrl()){
			return false;
		}
		
		$("#error_msg").text("");
		$("#error_msg").hide();
		var flag,msg;
		
		if(operate_type =="update"){
			flag=true;
			msg = "操作资源更新成功!"
		}else{
			flag=false;
			msg = "操作资源新增成功!";
		}

		$.ajax({
			url : ctx + "/systemManage/operationResourceOperate",
			type : "post",
			data: {
				url: url,
				type: type,
				flag: flag,
				module: module,
				detail: detail,
				resourceId: resourceId
			}, 
			dataType : 'json',
			success : function(data) {
				// 初始化页数和条数
				if(data.amount != undefined && data.amount > 0){
					showMessage(function(){
						hideDiv('popup_class');
						getPage();
						publicbox_close();
					},null,null,msg);
				}else{
					$("#error_msg").text("保存异常,请重试! ");
					$("#error_msg").show();
				}
			}
		});
	});
	
});

