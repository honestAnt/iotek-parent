<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-促销列表</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/discount/discount.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/common/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/homepage/friendly_link_list.js"></script>
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
						<a href="#">首页</a><span>&gt;</span><a href="#">网站设置 </a><span>&gt;</span><a
							href="#">友情链接</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
					<!--体验卡规则-->
					<div class="check_sure">
					<shiro:hasPermission name="/friendlyLinkManage/addLink:0x002">
						<a class="input_btn" href="${ctx }/friendlyLinkManage/toAddLink">创建友情链接</a>
					</shiro:hasPermission>
					</div>
					<div class="check_sure_02">
						<ul class="height clearfix">
							<li class="fl"><span class="activi">链接名称：</span> <label>
									<input class="text_04" type="text" name="name" id="name">
							</label></li>
							<li class="fl"><span class="activi">链接类型：</span> <label>
									<select class="text_03" name="type" id="link_type">
										<option value="0">请选择</option>
										<c:forEach items="${friendlyLinkTypeEnum }" var="friendlyLinkType">
										<option value="${friendlyLinkType.value}">${friendlyLinkType.label}</option>
										</c:forEach>
								</select>
							</label></li>
							<li class="fl"><label> 
							<shiro:hasPermission name="/friendlyLinkManage/indexSearch:0x005">
								<input id="query_btn" class="input_btn_02" type="button" value="查询" name="">
							</shiro:hasPermission>
							</label></li>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="exper_table">
						<table class="Exper_Tables" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th width="">序号</th>
									<th width="">链接名称</th>
									<th width="">链接地址</th>
									<th width="">链接类型</th>
									<th width="">添加时间</th>
									<th width="">操作</th>
								</tr>
							</thead>
							<shiro:hasPermission name="/friendlyLinkManage/indexSearch:0x005">
								<tbody id="friendly_link_body">
								</tbody>
							</shiro:hasPermission>
						</table>
					</div>
					<!--未通过账号表格 end-->
					<!--商品名称 end-->
					<div class="m-page">
						<div class="sabpages" id="turnpages"></div>
					</div>
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->

		</div>
	</div>
</body>
</html>
