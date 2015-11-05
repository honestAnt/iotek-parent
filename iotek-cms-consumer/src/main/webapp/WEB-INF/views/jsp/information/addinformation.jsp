<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<title>添加资讯</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/static/js/common/html5.js"></script>
<![endif]-->
<link href="${ctx}/web/css/seo/seo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/web/js/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctx}/web/js/My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="${ctx}/web/js/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${ctx}/web/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			filterMode: false,//是否开启过滤模式
			resizeType : 1,
			allowPreviewEmoticons : false,
			width:"585px",
			height : "200px",
			allowImageUpload : true,
			imageUploadLimit : 3,
			imageSizeLimit  : '5MB',
			items : ['bold', 'italic','justifyleft','justifycenter','justifyright',
			         'justifyfull','insertorderedlist','insertunorderedlist',
			         'link','image','subscript','superscript','source'],
			uploadJson : ctx+'/information/uploadPicture'
		});
		var content = '${infor.content}';
		editor.html(content);
		if("${infor.id}">0&&"${statusType}"==0){
			editor.readonly();
		}
	});
</script>
<script type="text/javascript">
$(function(){
	$(function() {
		$("#home_information_ul").show();
		$("#information_h3").addClass("subNav currentDd");
		$("#information_li").addClass("li_current");
	});
});
</script>
<script type="text/javascript" src="${ctx }/web/js/cms/information/information.js"></script>
</head>


<body>
<div id="box">
   <%@ include file="/WEB-INF/views/jsp/common/header.jsp"%>
<!--头部 ends-->
<div class="container">
<!--left-->
<%@ include file="/WEB-INF/views/jsp/common/menu.jsp"%>
<!--left end-->
<!--right-->
<div class="main_content">
 <!--面包屑-->
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">资讯管理 </a><span>&gt;</span><a href="#">资讯列表</a></p></div>
 <!--面包屑 ends-->
<!--内容-->
<c:if test="${infor.id==null||infor.id=='' }">
	<form action="${ctx }/information/saveInformation" id="informationForm" method="post">
      	<input type="hidden" id="id" name="id" value="0"/>
</c:if>
<c:if test="${infor.id!=null&&infor.id!='' }">
		<form action="${ctx }/information/updateInformation" id="informationForm" method="post">
      	<input type="hidden" id="id" name="id" value="${infor.id }"/>
</c:if>
 <div class="dash_wrapper">
   <table width="100%" class="table_tc">
     <tbody>
      
	  <tr height="50px">
	  <td width="10%" class="seo"><span>*</span>所属分类：</td>
	  <td class="seo1">
	  <label>
	  <select class="seo_date_021" id="firstLevelId" name="firstLevelId">
	  		<option value="-1">选择一级分类</option>
	  		<c:forEach items="${firstLevelList}" var="first">
	  			<option value="${first.id}" <c:if test="${first.id==infor.firstLevelId}">selected="true"</c:if>> ${first.sortName}</option>
	  		</c:forEach>
	  </select>
	  <input id="first_level_id_input" type="hidden" value="${infor.firstLevelId }"/>
	  <input id="second_level_id_input" type="hidden" value="${infor.secondLevelId }"/>
	  </label>
	  <shiro:hasPermission name="/information/secondLevel:0x002">
	  <label><select class="seo_date_021" id="secondLevelId" name="secondLevelId">
	  <option value="-1">选择二级分类</option>
	  </select>
	  </label>
	  </shiro:hasPermission>
	  </td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo"><span>*</span>标题：</td>
	  <td class="seo1"><label><label><input class="seo_date_031" name="title" type="text" id="title" value="${infor.title }" maxlength=60></label><span  id="titleSpan">最多输入60字</span></td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo"><span>*</span>关键字：</td>
	  <td class="seo1"><label><input class="seo_date_031" name="keywords" type="text" id="keywords" value="${infor.keywords }" maxlength=100></label><span  id="keywordsSpan">最多输入100字</span></td>
	  </tr>
      <tr height="145px">
		<td class="discount_top seo" width="10%"><span>*</span>备注：</td>
		<td style="vertical-align:middle;">
		<textarea class="discount_top_01" rows="" cols="" name="description" id="description"  maxlength=150>${infor.description }</textarea><span id="descriptionSpan">  &nbsp;　　最多输入150字</span>
		</td>
	 </tr>
	  <tr height="50px">
	  <td width="10%" class="seo">发布时间：</td>
	  <td class="seo1"><label><input class="seo_date_03" name="deliveryTime_Param" value="${deliveryTime }"
	  			id="deliveryTime" type="text" onClick="WdatePicker({readOnly:true})"></label></td>
	  </tr>
	   <tr height="50px">
	  <td width="10%" class="seo">来源：</td>
	  <td class="seo1"><label><input class="seo_date_031" name="source" id="source" type="text" value="${infor.source }"  maxlength=100></label></td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo">文章分页：</td>
	  <td class="seo1"><label>在需要文章分页的地方写【page】</label></td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo">首页新闻：</td>
	  <td class="seo1"><label>
	  <select class="seo_date_021" name="featured" id="featured">
	  		<option value="0">请选择推荐位</option>
	  		<c:forEach items="${recommendPosition}" var="recommend">
	  			<option value="${recommend.id}" <c:if test="${recommend.id==infor.featured}">selected="true"</c:if>>${recommend.name}</option>
	  		</c:forEach>
	  </select></label></td>
	  </tr>
	  <tr height="145px">
		<td class="discount_top seo" width="10%"><span>*</span>内容：</td>
		<td class="seo1">
		<textarea class="discount_top_01" rows="" cols="" name="content" id="content"></textarea>
		</td>
	 </tr>
	 </tbody>
   </table>
    <div class="marn_top">
    <!-- statusTypeDisabled的值为0表示审核页面，要置灰 -->
    <input type="hidden" value="${statusType}" id="statusTypeDisabled">
    <!-- statusType==0 表示审核 -->
    <c:if test="${statusType==0}">
    	<shiro:hasPermission name="/information/reviewInformation:0x004">
    	<input name="" class="masee"  type="button" value="发布" id="releaseButton" onclick="releaseButtons('${infor.id}',1)" >
    	<input name="" class="masee"  type="button" value="退回" id="returnButton" onclick="returnButtons('${infor.id}',2)">
    	</shiro:hasPermission>
    </c:if>
    <!-- statusType==1 表示添加或者修改 -->
    <shiro:hasPermission name="/information/saveInformation:0x002 or /information/updateInformation:0x004">
   	<c:if test="${statusType==1}">
    	<input name="" class="masee"  type="button" value="保存" id="addButton">
    </c:if>
    </shiro:hasPermission>
   </div>
 </div>
 </form>
<!--内容 end-->
</div>
<!--right end-->

</div>

</body>
<script type="text/javascript">
	statusTypeDisabled();
</script>
</html>

