
//当前页码
var pageNumber;
var id;
var name;
var linkType;
$(function(){
	getPage();
	$("#query_btn").click(function(){
		getPage();
	});
});

//获取页码信息，并初始化分页组件
function getPage(){
	name = $("#name").val();
	linkType = $("#link_type").val();
	$.ajax({
		url : ctx + "/friendlyLinkManage/indexSearch",
		type : "post",
		data: {
			name:name,
			type:linkType
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
	pageNumber = page_index;
	$.ajax({
		url : ctx + "/friendlyLinkManage/indexSearch",
		type : "post",
		data: {
			pageNo : page_index + 1,
			name:name,
			type:linkType
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.page.maxPage==1){
				$('#turnpages').hide();
			} else {
				$('#turnpages').show();
			}
			resolveData(data);
		}
	});
}


//处理返回数据拼接字符串加载到页面上
function resolveData(data){
	var contentshow="";
	var edit=validPermission("/friendlyLinkManage/updateLink");
	var del=validPermission("/friendlyLinkManage/toDelete");
	$(data.page.result).each(function (i,friendlyLink){
		contentshow +="<tr><td>"+(pageNumber*data.page.maxPage+i+1)+"</td>"+
		"<td>"+friendlyLink.name+"</td>"+
		"<td>"+friendlyLink.url+"</td>"+
		"<td>"+friendlyLink.friendlyLinkTypeEnumLabel+"</td>"+
		"<td>"+friendlyLink.createTime.replace("T",' ')+"</td>";
		contentshow += "<td class='operation_td'>";
		if(edit){
			contentshow += "<a href='javascript:void(0);' onclick='toDetail("+friendlyLink.id+");'>编辑</a>";
		}
		contentshow += "<span>|</span>";
		if(del){
			contentshow += "<a href='javascript:void(0);' onclick='toDelete("+friendlyLink.id+");'>删除</a>";
		}
		contentshow += "</td></tr>";
	});
	$("#friendly_link_body").html(contentshow);
}

/**
 * 删除前判断专题是否存在
 * @param dissertation
 */
function toDelete(id){
	$.ajax({
		url : ctx + "/friendlyLinkManage/findById",
		type : "post",
		data: {
			id:id
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			if(data == '200'){
				deleteFriendlyLink(id);
			}else{
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx+"/friendlyLinkManage/index";
				}, null,function(){
					publicbox_close();
					window.location.href = ctx+"/friendlyLinkManage/index";
				},"该友情链接已被删除!");
			}

		}
	});


}

/**
 * 删除友情链接
 * @param id
 * @param timestampName
 */
function deleteFriendlyLink(id){
	$.ajax({
		url:ctx+"/friendlyLinkManage/toDelete",
		type:"post",
		data:{
			id:id,
		},
		dataType : 'json',
		success : function(data) {
			if(data == '200'){
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx+"/friendlyLinkManage/index";
				}, null, publicbox_close, "删除成功！");
			}else{
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx+"/friendlyLinkManage/index";
				}, null, publicbox_close, "删除失败！");
			}
		}
	});
}

/**
 * 打开编辑页
 * @param id
 */
function toDetail(id){
	$.ajax({
		url : ctx + "/friendlyLinkManage/findById",
		type : "post",
		data: {
			id:id
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			if(data == '200'){
				window.location.href = ctx+"/friendlyLinkManage/toDetailLink?id="+id;
			}else{
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx+"/friendlyLinkManage/index";
				}, null,function(){
					publicbox_close();
					window.location.href = ctx+"/friendlyLinkManage/index";
				},"该友情链接已被删除，您不能再执行该操作!");
			}

		}
	});


}

