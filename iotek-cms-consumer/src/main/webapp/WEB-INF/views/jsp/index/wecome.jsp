<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-欢迎页面</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/web/js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<!-- <script type="text/javascript">
$(function(){
	$("#system_manage_ul").show();
	$("#system_manage_h3").addClass("subNav currentDd");
	$("#permission_li").addClass("li_current");
});
</script> -->
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
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">系统欢迎页面 </a></p></div>
 <!--面包屑 ends-->
	<!--内容-->
	<shiro:user>
 		<span>欢迎你:<shiro:principal/></span>
 	</shiro:user>
 	<shiro:guest>
 		你还没有登录，快点去登录吧!<a href="${ctx}/user/login">登录</a>
 	</shiro:guest>
   <!--内容 end-->
</div>
<!--right end-->

</div>
</div>
</body>
</html>
