<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-权限分配</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/system/system .css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/teacherscroll.js"></script>
<script type="text/javascript">
$(function(){
	$("#system_manage_ul").show();
	$("#system_manage_h3").addClass("subNav currentDd");
	$("#permission_li").addClass("li_current");
});
</script>
</head>

<body>
<div id="box">
<%--头部--%>
<%@include file="/WEB-INF/views/jsp/common/header.jsp"%>
<%--头部 end--%>
<div class="container">
<!--left-->
<%@include file="/WEB-INF/views/jsp/common/menu.jsp"%>
<%--left end--%>
<!--right-->
<div class="main_content">
 <!--面包屑-->
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">系统管理 </a><span>&gt;</span><a href="#">权限管理 </a></p></div>
 <!--面包屑 ends-->
<!--内容-->
 <div class="dash_wrapper">
     <ul class="outl_Title clearfix">
               <li class="outl_Title_01">角色名：市场</li>
               <li class="outl_Title_02">查看</li>
               <li class="outl_Title_02">添加</li>
               <li class="outl_Title_02">删除</li>
			   <li class="outl_Title_02">修改</li>
			   <li class="outl_Title_02">查询</li>
			   <li class="outl_Title_02">导入</li>
			   <li class="outl_Title_02">导出</li>
			   <li class="outl_Title_02">下载</li>
			   <li class="outl_Title_02">上传</li>
              </ul>
               <!--C++学科-->
                <div id="featured">
                  <ul class="outlinemenu">
                    <li>
                      <div class="outl_header"><a href="javascript:void(0)"><i class="mgin_em"></i><span>用户管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                         <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                         <li><span class="section_name">批量创建渠道用户</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
						 <li><span class="section_name">批量创建校园用户</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                         <li><span class="section_name">商品有效期延长</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                       </ul>
                    </li>
                    <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_1"></i><span>运营管理</span></a></div>
                      <ul class="second_menu" style="display:none;">
                       <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
                    <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_2"></i><span>营销管理</span></a></div>
                      <ul class="second_menu" style="display:none;">
                       <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				   <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_3"></i><span>商品管理</span></a></div>
                      <ul class="second_menu" style="display:none;">
                       <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				    <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_4"></i><span>订单管理</span></a></div>
                      <ul class="second_menu" style="display:none;">
                       <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				    <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_5"></i><span>资源管理</span></a></div>
                      <ul class="second_menu" style="display:none;">
                       <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				   <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_6"></i><span>促销管理</span></a></div>
                      <ul class="second_menu" style="display:none;">
                       <li><span class="section_name">用户信息</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				   <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_7"></i><span>社区管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                       <li><span class="section_name">问答列表</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				   <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_8"></i><span>认证管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                       <li><span class="section_name">理论考试</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
					   <li><span class="section_name">项目考试</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
					   <li><span class="section_name">省份认证</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
					   <li><span class="section_name">模拟面试</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				    <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_9"></i><span>试题管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                       <li><span class="section_name">试题列表</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框">
                       </label></li>
                      </ul>
                   </li>
				   <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_10"></i><span>资料管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                       <li><span class="section_name">资料列表</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
				   <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_11"></i><span>作业管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                       <li><span class="section_name">作业练习列表</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
					    <li><span class="section_name">作业批阅</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
                    <li>
                      <div class="outl_header border02"><a href="javascript:void(0)"><i class="mgin_em_11"></i><span>教师管理</span></a></div>
                      <ul class="second_menu" style="display:block;">
                       <li><span class="section_name">班级管理</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
					   <li><span class="section_name">账号分配</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                       <li><span class="section_name">学习管理</span><label class="free_learning"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"><input  class="account_02" type="checkbox" name="CheckboxGroup1" value="复选框"></label></li>
                      </ul>
                   </li>
                 </ul>
                </div>
				<div class="dash_sure1">
<input class="input_btn" type="button" value="保存" name="" onclick="payforbox()"><input class="input_btn1" type="button" value="清空" name="" onclick="payforbox()">
</div>
   </div>
   <!--内容 end-->
</div>
<!--right end-->

</div>
</div>
</body>
</html>
