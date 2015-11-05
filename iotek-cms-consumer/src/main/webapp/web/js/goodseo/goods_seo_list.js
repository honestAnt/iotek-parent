
/**
 * 定义页面参数变量
 */
var firstLevelId,secondLevelId,thirdLevelId,startTime,endTime,searchStr;

/**
 * 每个商品状态页的页码
 */
var onTheShelfPageNum, sendBackNum, pageNumber;


//获取页码信息，并初始化分页组件
function getPage(){
	$.ajax({
		url : ctx + "/goodsSeoInfo/searchGoodsSeoList",
		type : "post",
		data: {
			// 传参数packageId:packageId
			firstLevelId:firstLevelId,
			secondLevelId:secondLevelId,
			thirdLevelId:thirdLevelId,
			startTime:startTime,
			endTime:endTime,
			searchStr:searchStr,
			goodsStatus:goodsStatus,
			fromGoodsListVisitFlag: fromGoodsListVisitFlag,
			seoStatus: seoStatus
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

	if (pageNumber != undefined && pageNumber != '' && pageNumber != 0 && pageNumber != page_index) {
		page_index = pageNumber;
		$("#turnpages").children().eq(pageNumber+1).trigger("click");
		pageNumber = 0;
		return false;
	}
	
	$.ajax({
		url : ctx + "/goodsSeoInfo/searchGoodsSeoList",
		type : "post",
		data: {
			pageNo : page_index+1,
			firstLevelId:firstLevelId,
			secondLevelId:secondLevelId,
			thirdLevelId:thirdLevelId,
			startTime:startTime,
			endTime:endTime,
			searchStr:searchStr,
			goodsStatus:goodsStatus,
			fromGoodsListVisitFlag: fromGoodsListVisitFlag,
			seoStatus: seoStatus
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			if (data.maxPage==1){
				$('#turnpages').hide();
			} else {
				$('#turnpages').show();
			}
			
			if(seoStatus == 1){
				analysisCheckData(data);
			}else{
				analysisData(data);
			}
			
		}
	});
}


/**
 * 加载SEO信息列表 
 */
function analysisData(data){
	var list  = data.result;
	var dataCount = data.dataCount;
	
	var statusStr="发布";
	if(seoStatus==2){
		statusStr="退回";
	}
	
	var goodsStatusHTML = "<td height='35'>"+statusStr+"<em class='color1' id='goodsCount_em'> "+dataCount+"</em> 件商品</td>";
	$("#goods_status_count_tr").html(goodsStatusHTML);
	
	if (data.dataCount <= 0) {
		$("#goods_list_tbody").html("");
		return ;
	}
	
	var definedHTML = "";
	
	//是否有权限
	var addPermiss = validPermission("/goodsSeoInfo/addGoodsSeoIndex");
	var updatePermiss = validPermission("/goodsSeoInfo/updateGoodsSeoIndex");
	var deletePermiss = validPermission("/goodsSeoInfo/validGoodsSeoInfo");
	
	$.each(list, function(i, goods){
		definedHTML += "<tr><td>"+(++i)+"</td>";
		definedHTML += "<td>"+goods.goodsName+"</td>";
		definedHTML += "<td>"+goods.firstLevelName+"</td>";
		
		definedHTML += "<td>";
		$.each(goods.goodsLevelList, function(i, secondLevel){
			definedHTML += "<p>"+secondLevel.content+"</p><p></p>";
		});
		definedHTML += "</td>";
		
		definedHTML += "<td>"+goods.thirdLevelName+"</td>";
		
		var auditTime="";
		if(goods.auditTime != undefined){
			auditTime  = goods.auditTime.replace("T", " ");
		}
		
		definedHTML += "<td>"+auditTime+"</td>";
		definedHTML += "<td>";
		
		if(updatePermiss){
			if(goods.seoId != 0 && goods.seoId != undefined){
				definedHTML += "<a href='"+ctx+"/goodsSeoInfo/updateGoodsSeoIndex?goodsId="+goods.id+"'>编辑</a>&nbsp;&nbsp;";
			}
		}
		
		if(addPermiss){
			if(goods.seoId == 0 || goods.seoId == undefined){
				definedHTML += "<a href='"+ctx+"/goodsSeoInfo/addGoodsSeoIndex?goodsId="+goods.id+"'>添加</a>&nbsp;&nbsp;";
			}
		}
		
		if(deletePermiss){
			if(goods.status==1){
				definedHTML += "<a href='javascript:void(0);' onClick='validDeleteSeoInfo("+goods.id+")'>删除</a>";
			}else{
				definedHTML += "<a href='javascript:void(0);' onClick='validDeleteSeoInfo("+goods.id+")'>删除</a>";
			}
		}
		
		definedHTML += "</td>";
		
	});
	$("#goods_list_tbody").html(definedHTML);
	
}



/**
 * 加载商品SEO信息审核列表
 */
function analysisCheckData(data){
	var list  = data.result;
	var dataCount = data.dataCount;
	
	var goodsStatusHTML = "<td height='35'>待审核<em class='color1' id='goodsCount_em'> "+dataCount+"</em> 件商品SEO信息</td>";
	$("#goods_status_count_tr").html(goodsStatusHTML);
	
	if (data.dataCount <= 0) {
		$("#goods_list_tbody").html("");
		return ;
	}
	$("#time_th").text("提交时间");
	
	//权限控制
	var updatePermiss = validPermission("/goodsSeoInfo/goodsSeoAuditIndex");
	
	var definedHTML = "";
	$.each(list, function(i, goods){
		definedHTML += "<tr><td>"+goods.id+"</td>";
		definedHTML += "<td>"+goods.goodsName+"</td>";
		definedHTML += "<td>"+goods.firstLevelName+"</td>";
		definedHTML += "<td>";
		$.each(goods.goodsLevelList, function(i, secondLevel){
			definedHTML += "<p>"+secondLevel.content+"</p><p></p>";
		});
		definedHTML += "</td>";
		definedHTML += "<td>"+goods.thirdLevelName+"</td>";
		var create_time  = goods.seoCreateTime.replace("T", " ");
		if(goods.seoUpdateTime != undefined){
			create_time = goods.seoUpdateTime.replace("T"," ");
		}
		definedHTML += "<td>"+create_time+"</td>";
		definedHTML += "<td>";
		
		if(updatePermiss){
			definedHTML += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+ctx+"/goodsSeoInfo/goodsSeoAuditIndex?goodsId="+goods.id+"'>审核</a>";
		}
		definedHTML += "</td></tr>";
	});
	$("#goods_list_tbody").html(definedHTML);
}


$(function(){
	/**
	 * 分类一选中事件
	 */
	$("#firstLevel_select").click(function(){
		var firstLevelId = $("#firstLevel_select").val();
		if(firstLevelId !=0){
			$.ajax({
				url: ctx+"/goodsSeoInfo/getSecondGoodsLevel",
				type : "post",
				data: {
					firstLevelId:firstLevelId
				},
				dataType:'json',
				async:false,
				success : function(data){
					var definedHTML = "";
					definedHTML += "<option value='0'>请选择</option>";
					$.each(data, function(i, secondLevel){
						definedHTML += "<option value=" + secondLevel.id + ">" + secondLevel.content + "</option>";
					});
					$("#secondLevel_select").html(definedHTML);
				}
				
			});
		}else{
			var definedHTML = "<option value='0'>请选择</option>";
			$("#secondLevel_select").html(definedHTML);
		}
	});
	
	/**
	 * 点击查询按钮
	 */
	$("#query_btn").click(function(){
		if(!getParams()){
			return false;
		}
		onTheShelfPageNum ='';sendBackNum='';
		getPage();
	});
});


/**
 * 获取参数
 */
function getParams() {
	firstLevelId = $("#firstLevel_select").val();
	secondLevelId = $("#secondLevel_select").val();
	thirdLevelId = $("#thirdLevel_select").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	searchStr = $("#searchStr").val();

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



/**
 * 校验是否需要删除
 * @param goodsId
 */
function validDeleteSeoInfo(goodsId){
	$.ajax({
		url: ctx+'/goodsSeoInfo/validGoodsSeoInfo',
		type: 'post',
		data: {
			goodsId:goodsId
		},
		dataType: 'json',
		async: false,
		success:function(data){
			if(data == true && data != undefined ){
				showMessage(
					function() {
						publicbox_close(); 
						deleteGoodsSeoInfo(goodsId);
					},publicbox_close,publicbox_close,"确认需要删除该SEO信息?点击确定后将进行删除!");
			}else{
				showMessage(publicbox_close,null,publicbox_close,'该商品没有添加SEO信息!');
			}
		}
	});
}


/**
 * 删除SEO信息
 * @param goodsId
 */
function deleteGoodsSeoInfo(goodsId){
	$.ajax({
		url: ctx+'/goodsSeoInfo/deleteGoodsSeoInfo',
		type: 'post',
		data: {
			goodsId:goodsId
		},
		dataType: 'json',
		async: false,
		success:function(data){
			if(data != undefined && data >0){
				showMessage(function() {
					location.href= ctx+'/goodsSeoInfo/goodsSeoListIndex';
					publicbox_close();
				},null,publicbox_close,"SEO信息删除成功!");
			}else{
				
			}
		}
	});
}



/**
 * 点击商品状态进行查询
 * @param goodsStatus
 */
function changeGoodsStatus(status,obj){
	if(!getParams()){
		return false;
	}
	//前一个tab分页页数
	var currentPage = $("#turnpages .current").html();
	
	//记录前一个tab分页页码
	if (seoStatus != status) {
		if (seoStatus == 3) {
			onTheShelfPageNum = currentPage -1;
		} else if (seoStatus == 2) {
			sendBackNum = currentPage -1;
		}
	}

	//找到当前页的分页数
	if (status == 3) {
		pageNumber = onTheShelfPageNum;
	} else if (status == 2) {
		pageNumber = sendBackNum;
	}
	
	seoStatus = status;
	
	$(obj).addClass('hover').parent().siblings().find("a").removeClass('hover');
	//调用分页方法
	getPage();
}