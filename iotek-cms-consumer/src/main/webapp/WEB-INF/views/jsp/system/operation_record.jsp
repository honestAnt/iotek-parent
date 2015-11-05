<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-操作日志</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/system/system .css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/common/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/operation_record.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#system_manage_ul").show();
		$("#system_manage_h3").addClass("subNav currentDd");
		$("#operation_record_li").addClass("li_current");
	});
</script>
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
				<div class="crumbs">
					<p>
						<a href="#">首页</a><span>&gt;</span><a href="#">系统管理 </a><span>&gt;</span><a
							href="#">操作日志</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">

					<div class="sys_zy">
						<!-- 筛选start -->
						<div class="good_search">
							<table width="100%">
								<tr>
									<td height="45">账号：<input type="text" class="s_txt"
										value="${username}" id="username"> 时间：<input name="startTime"
										value="${startTime}" id="startTime" class="time_txt"
										type="text" onClick="WdatePicker({readOnly:true})" /> - <input
										name="endTime" value="${endTime}" id="endTime"
										class="time_txt" type="text"
										onClick="WdatePicker({readOnly:true})" /> <input
										type="button" value="查询" class="btn_1" id="query_btn"/><span id="timeMsg" style="color: red"></span></td>
								</tr>
								<tr>
									<td height="20"></td>
								</tr>
							</table>
						</div>
						<!-- 筛选end -->
						<table width="100%" class="sys_zy_list" id="tb_list">
						</table>
						<!-- 	分页 -->
						<div class="m-page">
							<div id="turnpages" class="sabpages"></div>
						</div>
						<!--  分页 -->
					</div>
				</div>
				<!--right end-->
			</div>
		</div>
	</div>
</body>
</html>
