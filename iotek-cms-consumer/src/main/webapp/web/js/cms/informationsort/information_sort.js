/**********
 *资讯分类
 *Joshua
 *2015-7-14 17:22:08 
 ************/
/*---------------*/

var sortName="";//分类名称
var seoTitle=""; //SEO标题
var seoKeywords=""; //SEO关键字
var seoDescr=""; //SEO备注
var sortNameHidden="";//隐藏分类名称

/**
 * 页面打开时加载分类列表
 */
$(document).ready(function() {fillContent();});
 
/**
 * 执行更新
 * @param id 更新信息ID
 */
function upd(id){
	getAttr();//赋值
	$.ajax({
	    cache: true,
	     type: "POST",
	     url:ctx +"/informationSort/updInfoSort",
	     data:{
				id:id,
				sortName:sortName,
				seoTitle:seoTitle,
				seoKeywords:seoKeywords,
				seoDescr:seoDescr
			},
	     async: false,
	     error: function(request) {
	      	 showMessage(publicbox_close,null,publicbox_close,"Connection error！");
	      },
	      success: function(data) {
			if(data=="200"){
		         showMessage(function(){ fillContent(); publicbox_close(); },null,publicbox_close,"更新成功！");
			}else{
				 showMessage(publicbox_close,null,publicbox_close,"更新失败！");
			}
	      }
	});
}
 
/**
 * 执行新增
 * @param id 添加子分类是 作为子分类的父ID
 */
function add(id){
	getAttr();//赋值
	$.ajax({
	     type: "POST",
	     url:ctx +"/informationSort/addInfoSort",
	     data:{
				parentId:id,
				sortName:sortName,
				seoTitle:seoTitle,
				seoKeywords:seoKeywords,
				seoDescr:seoDescr
			},
	     async: false,
	     error: function(data) {
	      	 showMessage(publicbox_close,null,publicbox_close,"Connection error！");
	      },
	      success: function(data) {
			 if(data=="200"){
		          showMessage(function(){ fillContent(); publicbox_close();},null,publicbox_close,"添加成功！");
			 }else{
				  showMessage(publicbox_close,null,publicbox_close,"添加失败！");
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
		},publicbox_close,publicbox_close,"确定删除该分类吗！");
}

/**
 * 执行删除
 * @param id 删除的信息ID
 */
function del(id){
	publicbox_close();
	$.ajax({
		url : ctx+"/informationSort/delInfoSort",
		type : "post",
		data : {
			id : id
		},
		dataType : 'json',
		success : function(code){
			if(code == "200"){ //可以删除
				showMessage(function(){ fillContent(); publicbox_close();
				},null,publicbox_close,"删除成功！");
			}else if(code=="500"){  //删除信息为一级分类并且下面有二级分类
				showMessage(publicbox_close,null,publicbox_close,"此分类下面有二级分类,删除失败！");
			}else if(code=="505"){//在使用的分类
				showMessage(publicbox_close,null,publicbox_close,"此分类已经在使用，删除失败！");
			}else{
				showMessage(publicbox_close,null,publicbox_close,"系统出错，请联系管理员！");
			}
		}
	});
}
/**
 * 处理返回数据拼接字符串加载到页面上
 * @param data 要展示的JSON数据集合
 * @param tbody_id 填充到容器的ID
 */
function resolveData(data,tbody_id){
	var content = ""; //拼组body
	$("#"+tbody_id).html(content);//加载前清空内容
	var edit=validPermission("/informationSort/updInfoSort");
	var del=validPermission("/informationSort/delInfoSort");
	var add=validPermission("/informationSort/addInfoSort");
	//console.log(edit);
	$(data).each(function (i,map){
		var parent = map.parent;
		var childList = map.children;
		content+="<tr>";
		content+="<td height='35'><b>";
		content+=parent.sortName;
		content+="</b></td>";
		content+="<td>";
		if(edit){
			content+="<a href='javascript:;' onclick='updSort("+parent.id+",0,\"parent\")'>编辑</a>　";
		}
		if(del){
			content+="<a href='javascript:;' onclick='toDel("+parent.id+");'>删除</a>　";
		}
		if(add){
			content+="<a href='javascript:;' onclick='addSort("+parent.id+",\"children\")'>添加子类</a>";
		}
		content+="</td>";
		content+="</tr>";
		
		$(childList).each(function (i,child){
			content+="<tr class='class_2'>";
			content+="<td height='35'>";
			content+=child.sortName;
			content+="</td>";
			content+="<td>";
			if(edit){
				content+="<a href='#' onclick='updSort("+child.id+","+child.parentId+",\"children\")'>编辑</a>　";
			}
			if(del){
				content+="<a href='#' onclick='toDel("+child.id+");'>删除</a>";
			}
			content+="</td>";
			content+="</tr>";
		});
		
	});
	$("#"+tbody_id).html(content);//填充
}

/**
 * 获取页码信息，并初始化分页组件
 * @param params
 */
function fillContent() {
	$.ajax({
		url : ctx + "/informationSort/infoSortJosn",
		type : "post",
		data : { },
		dataType : 'json',
		async:false,
		success : function(data) {
			//填充
			resolveData(data,"info_sort_content");
		}
	});
}


/**
 * 验证名字是否存在
 * @param sortName 验证名称
 * @returns {Boolean} 是否存在
 */
function verifySortName(sortName){
	var flag=false;
	$.ajax({
		url : ctx + "/informationSort/verifySortName",
		type : "post",
		data : {
			sortName:sortName
		},
		async: false,
		success : function(data){
			if(data == "200"){
				flag=true;
			}
		}
	});
	return flag;
}

/**
 * 数据校验
 * @returns {Boolean}
 */
function verifyDate(){
	
	sortName=$("#sort_name").val();
	if(sortName == undefined || sortName=='' || sortName==null){
		showMessage(showBox,null, null,"名称不能为空！");
		return false;
	}
	if(sortName.length > 20){//20
		showMessage(showBox,null, null,"名称过长，最多输入20个字符或汉字！");
		return false;
	}
	if($("#seo_title").val().length > 60){//60
		showMessage(showBox,null, null,"SEO标题过长，最多输入60个字符或汉字！");
		return false;
	}
	if($("#seo_keywords").val().length > 100){//100
		showMessage(showBox,null, null,"SEO关键字过长，最多输入100个字符或汉字！");
		return false;
	}
	if($("#seo_descr").val().length > 150){//150
		showMessage(showBox,null, null,"SEO描述信息过长，最多输入150个字符或汉字！");
		return false;
	}
	return true;
}
/**
 * 验证分类名称是否更改
 * @returns {Boolean}
 */
function verifyChange(){
	sortNameHidden=$("#sort_name_hidden").val();
	sortName=$("#sort_name").val();
	if(sortName==sortNameHidden){
		return false;
	}
	return true;
}

/**
 * 根据ID查询分类对象
 * @param id 查询对象的ID
 * @returns
 */
function getById(id){
	var infoSort;
	$.ajax({
		url : ctx + "/informationSort/getInfoSortById",
		type : "post",
		data : {
			id:id
		},
		async: false,
		success : function(data){
			infoSort=data;
		}
	});
	return infoSort;
}

/**
 * 填充属性值
 */
function getAttr(){
	sortNameHidden=$.trim($("#sort_name_hidden").val());
	 sortName=$.trim($("#sort_name").val());
	 seoTitle=$.trim($("#seo_title").val());
	 seoKeywords=$.trim($("#seo_keywords").val());
	 seoDescr=$("#seo_descr").val();
}

/**
 * 清空所有选项
 */
function clear(){
	$("#sort_name").val("");
	$("#seo_title").val("");
	$("#seo_keywords").val("");
	$("#seo_descr").val("");
}

/**
 * 关闭弹层
 */
function closeBox(){
	$("#upd_info_sort").hide();
}

/**
 * 弹出
 */
function showBox(){
	$("#upd_info_sort").show();
	publicbox_close();
}

/**
 * 清空数据弹出
 */
function showBox_1(){
	clear();
	$("#upd_info_sort").show();
	publicbox_close();
}

/**
 * 处理更新
 * @param id 信息ID
 * @param parentId  信息父ID
 * @param type 更新类型（'parent','children'）
 */
function updSort(id,parentId,type){
	clear();
	initMenu(parentId,type);
	var infoSort = getById(id);
	if(infoSort != null){
		$("#sort_name").val(infoSort.sortName);
		$("#sort_name_hidden").val(infoSort.sortName);
		$("#seo_title").val(infoSort.seoTitle);
		$("#seo_keywords").val(infoSort.seoKeywords);
		$("#seo_descr").val(infoSort.seoDescr);
		showBox();
		jQuery("#sub_btn").die().live("click",function(){
			closeBox();
			if(verifyDate()){//验证非空
				if(verifyChange()){//验证名称是否更改
					if(verifySortName(sortName)&&type=="parent"){
						showMessage(showBox,null,publicbox_close,"名称已存在！");
						return;
					}
				}
				upd(id);
			}
		});
	}
}

/**
 * 处理添加
 * @param parentId 父ID
 * @param type 添加类型（'parent' or 'children'）
 */
function addSort(parentId,type){
	showBox_1();
	initMenu(parentId,type);
	jQuery("#sub_btn").die().live("click",function(){
		closeBox();
		if(verifyDate()){
			if(verifySortName(sortName)&&type=="parent"){
				showMessage(showBox,null,publicbox_close,"名称已存在！");
				return;
			} 
			add(parentId);
		}
	});
}
/**
 * 填充父节点列表框
 * @param parentId
 */
function fillSelect(parentId){
	$("#parent_id").find("option").remove();
	$.ajax({
		url : ctx + "/informationSort/infoSortList",
		type : "post",
		data : { 
			parentId:0
		},
		async: false,
		success : function(data){
			$(data).each(function(){
				if(this.id==parentId)
				$("#parent_id").append(
						"<option selected='selected' value=\"" + this.id + "\" >" + this.sortName + "</option>");
				else
				$("#parent_id").append(
						"<option  value=\"" + this.id + "\" >" + this.sortName + "</option>");
			});
		}
	});
}

/**
 * 初始化菜单和一级列表框状态
 * @param parentId 
 */
function initMenu(parentId,type){
	if(type=="children"){//如果为子节点加载其所属父节点
		fillSelect(parentId);
		 $("#_parent").show();
	}else if(type=="parent"){
		 $("#_parent").hide();
	}else{
		showMessage(publicbox_close,null,publicbox_close,"参数传输有误！");
	}
}

