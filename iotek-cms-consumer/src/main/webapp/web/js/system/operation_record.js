
/**
 * 定义页面参数变量
 */
var username;
var startTime;
var endTime;

$(function(){
	getPage();
	/**
	 * 点击查询按钮
	 */
	$("#query_btn").click(function(){
		if(!getParams()){
			return false;
		}
		getPage();
	});
});


/**
 * 获取参数
 */
function getParams() {
	username = $("#username").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();

	if(startTime != '' && endTime!=''){
		var start = new Date(startTime).getTime();
		var end = new Date(endTime).getTime();
		
		if(start > end){
			$("#timeMsg").text("结束时间不能小于开始时间!");
			$("#timeMsg").show();
			return false;
		}else{
			$("#timeMsg").hide();
		}
	}
	return true;
}

//获取页码信息，并初始化分页组件
function getPage(){
	$.ajax({
		url : ctx + "/systemManage/searchOperationRecord",
		type : "post",
		data: {
			username:username,
			startTime:startTime,
			endTime:endTime
		}, 
		dataType : 'json',
		success : function(data) {
			// 初始化页数和条数
			initPatination('turnpages',data.page.dataCount,data.page.pageSize);
		}
	});
}


//分页方法，参数showPage为显示页面的容器ID，callbackfunction为回调函数名,totleCount为数据总条数，pageNum为每页显示条数	
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
		url : ctx + "/systemManage/searchOperationRecord",
		type : "post",
		data: {
			pageNo : page_index + 1,
			username:username,
			startTime:startTime,
			endTime:endTime
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.page.maxPage==1){
				$('#turnpages').hide();
			} else {
				$('#turnpages').show();
			}
			resolveData(data.page.result);
		}
	});
}


//处理返回数据拼接字符串加载到页面上
function resolveData(list){
	var contentshow="";
	contentshow += "<tr><th width='20%'>账号</th><th width='20%'>操作时间</th><th>描述</th></tr>";
	$(list).each(function (i,opetarionRecord){
		contentshow +="<tr><td>"+opetarionRecord.username+"</td>"+
		"<td>"+opetarionRecord.createTime.replace("T",' ')+"</td>"+
		"<td>"+opetarionRecord.detail+"</td></tr>"
	});
	$("#tb_list").html(contentshow);
}



