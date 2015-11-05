<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@ include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<title>系统后台-无权限页面</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<link href="${ctx}/web/css/common/jNotify.jquery.css" rel="stylesheet" type="text/css"> 
<link href="${ctx}/web/css/index/login.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="${ctx }/web/js/common/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx }/web/js/index/index.js"></script>
</head>
	<body>
	<p>你没有相应的权限，请联系管理员！<a href="${ctx }/index/wecome">回到欢迎页面</a></p>
</body>
</html>