<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<title>系统后台-促销列表</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/static/js/common/html5.js"></script>
<![endif]-->
</head>
<script type="text/javascript">
	$(function() {
		$("#home_ul").show();
		$("#home_manage_h3").addClass("subNav currentDd");
		$("#friendly_link_manage").addClass("li_current");
	});
</script>
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
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">用户管理 </a><span>&gt;</span><a href="#">资讯信息</a></p></div>
 <!--面包屑 ends-->
<!--内容-->
 <div class="dash_wrapper">
   <table width="100%" class="table_tc">
     <tbody>
	  <tr height="50px">
	  <td width="10%" class="seo"><span>*</span>所属分类：</td>
	  <td class="seo1"><label><select class="seo_date_021"><option value="0">选择一级分类</option></select></label><label><select class="seo_date_021"><option value="0">选择二级分类</option></select></label></td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo"><span>*</span>标题：</td>
	  <td class="seo1"><label><label><input class="seo_date_031" name="" type="text"></label><span>最多输入60字</span></td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo"><span>*</span>关键字：</td>
	  <td class="seo1"><label><input class="seo_date_031" name="" type="text"></label></td>
	  </tr>
      <tr height="145px">
		<td class="discount_top seo" width="10%"><span>*</span>备注：</td>
		<td>
		<textarea class="discount_top_01" rows="" cols="" name=""></textarea>
		</td>
	 </tr>
	  <tr height="50px">
	  <td width="10%" class="seo">发布时间：</td>
	  <td class="seo1"><label><input class="seo_date_03" name="" type="text"></label></td>
	  </tr>
	   <tr height="50px">
	  <td width="10%" class="seo">来源：</td>
	  <td class="seo1"><label><input class="seo_date_031" name="" type="text"></label></td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo">文章分页：</td>
	  <td class="seo1"><label><input name="" class="masee"  type="button" value="分页标记"></label>在需要文章分页的地方点击“分页标记”</td>
	  </tr>
	  <tr height="50px">
	  <td width="10%" class="seo">首页新闻：</td>
	  <td class="seo1"><label><select class="seo_date_021"><option value="0">选择一级分类</option></select></label></td>
	  </tr>
	  <tr height="145px">
		<td class="discount_top seo" width="10%"><span>*</span>内容：</td>
		<td>
		<textarea class="discount_top_01" rows="" cols="" name=""></textarea>
		</td>
	 </tr>
	 </tbody>
   </table>
    <div class="marn_top">
   <input name="" class="masee"  type="button" value="审核">
   </div>
 </div>
<!--内容 end-->
</div>
<!--right end-->

</div>
</body>
</html>
