
//当前页码
var pageNumber;
var name ='';
$(function(){
	getPage();
	$("#add_recommend_btn").click(function(){
		$("#payforpbbox").hide();
		name = $("#recommend_name").val();
		if(name == null || name == '' || name == undefined){
			showMessage(showBox, null, showBox, "推荐位名称不能为空！");
			return ;
		}
		//限制30个中文
		var length = name.replace(/[^\x00-\xff]/g,"NB").length; 
		if(length > 60){
			showMessage(showBox, null, showBox, "推荐位名称过长,请重新输入！");
			return ;
		}
		//验证名称
		if(validRecommendName(name)){
			showMessage(showBox, null,showBox,"推荐位名称不能重复");
			return;
		} 
		
		var recommendId=$("#recommend_id").val();
		if(recommendId!=undefined &&recommendId!=0&&recommendId!=""){
			upd();
		}else{
			toAddRecommend();
		}
		
	});
});

/**
 * 验证名称是否重复
 * @param name 要验证的名称
 * @returns {Boolean} 
 */
function validRecommendName(name){
	var flag=false;
	$.ajax({
		url: ctx+"/recommendPositionManage/validRecommendName",
		type: "post",
		data: {
			name:name
		},
		dataType:"json",
		async: false,
		success:function(data){
			if(data == '200'){
				flag=true;
			}
		}
	});
	return flag;
}
/**
 * 按确定按钮时 校验失败显示弹层
 */
function showBox(){
	publicbox_close();
	$("#payforpbbox").show();
}
/**
 * 添加推荐位
 */
function toAddRecommend(){
	$.ajax({
		url:ctx+"/recommendPositionManage/addRecommend",
		type:"post",
		data:{
			name:name
		},
		dataType:"json",
		success:function(data){
			if(data > 0){
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx +"/recommendPositionManage/index"	
				}, null,function(){
					publicbox_close();
					window.location.href = ctx +"/recommendPositionManage/index"	
				},"推荐位添加成功！");
			}else{
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx +"/recommendPositionManage/index"	
				}, null,function(){
					publicbox_close();
					window.location.href = ctx +"/recommendPositionManage/index"	
				},"推荐位添加失败！");
			}
		}
	});
}

/**
 * 删除推荐位
 * @param id 要删除的ID
 */
function del(id){
	$.ajax({
		url : ctx + "/recommendPositionManage/delRecommend",
		type : "post",
		data: {id:id }, 
		dataType : 'json',
		success : function(data) {
			if(data=="500"){
				showMessage(  publicbox_close 	 , null,  publicbox_close  ,"此推荐位已在使用,删除失败！");
			}else if(data=="505"){
				showMessage(  publicbox_close 	 , null,  publicbox_close  ,"操作失败！");
			}else if(data=="200"){
				showMessage(function(){
					publicbox_close();
					window.location.href = ctx +"/recommendPositionManage/index"	
				}, null,function(){
					publicbox_close();
					window.location.href = ctx +"/recommendPositionManage/index"	
				},"删除成功！");
			}else{
				showMessage(  publicbox_close 	 , null,  publicbox_close  ,"系统出错！");
			}
			
		}
	});
}

/**
 * 删除提示
 * @param id 要删除的信息ID
 */
function toDel(id){
	showMessage(function(){
		del(id);
		},publicbox_close,publicbox_close,"确定要删除吗！");
}

/**
 * 执行修改 
 */
function upd(){
	$.ajax({
		url : ctx + "/recommendPositionManage/updRecommend",
		type : "post",
		data: {id:$("#recommend_id").val(),name:$("#recommend_name").val()}, 
		dataType : 'json',
		success : function(data) {
			if(data=="200"){
				showMessage(function (){publicbox_close();
				window.location.href = ctx +"/recommendPositionManage/index"
				}, null,publicbox_close,"更新成功！");
			}else{
				showMessage(function (){
				publicbox_close()
				window.location.href = ctx +"/recommendPositionManage/index"
				}, null,publicbox_close,"更新失败！");
			}
		}
	});
}
//获取页码信息，并初始化分页组件
function getPage(){
	$.ajax({
		url : ctx + "/recommendPositionManage/indexSearch",
		type : "post",
		data: {
		}, 
		dataType : 'json',
		success : function(data) {
			// 初始化页数和条数
			initPatination('turnpages',data.dataCount,data.pageSize);
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
		url : ctx + "/recommendPositionManage/indexSearch",
		type : "post",
		data: {
			pageNo : page_index + 1,
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.maxPage==1){
				$('#turnpages').hide();
			} else {
				$('#turnpages').show();
			}
			resolveData(data);
		}
	});
}

/**
 * 打开修改弹层
 */
function toUpd(id){
	$.ajax({
		url : ctx + "/recommendPositionManage/toUpdRecommend",
		type : "post",
		data: {id:id }, 
		dataType : 'json',
		success : function(data) {
			 //console.log(data);
			$("#recommend_id").val(data.id);
			$("#recommend_name").val(data.name);
			payforbox();
		}
	});
}

//处理返回数据拼接字符串加载到页面上
function resolveData(data){
	var contentshow="";
	var update = validPermission("/recommendPositionManage/updRecommend");
	var del = validPermission("/recommendPositionManage/delRecommend");
	$(data.result).each(function (i,recommend){
		//console.log(recommend);
		contentshow +="<tr><td>"+(i+1)+"</td>";
		contentshow +="<td>"+recommend.name+"</td>";
		contentshow +="<td>"+recommend.createTime.replace("T",' ');
		contentshow +="</td><td>";
		if(update){
			contentshow +="<a href='javascript:void(0);' onclick='toUpd("+recommend.id+")'>编辑　</a>";
		}
		if(del){
			contentshow +="<a href='javascript:void(0);' onclick='toDel("+recommend.id+")'>删除</a>";
		}
		contentshow +="</td></tr>";
	});
	$("#recommend_position_body").html(contentshow);
}
