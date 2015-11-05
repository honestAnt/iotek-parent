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
<link href="${ctx}/web/css/live/live.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/common/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/homepage/recommend_position_list.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#home_ul").show();
		$("#home_manage_h3").addClass("subNav currentDd");
		$("#recommend_position_manage").addClass("li_current");
	});
	
	function over_payforbox(){
		$("#recommend_name").val("");
		$("#recommend_id").val("");
		payforbox();
	}
	
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
							href="#">推荐位</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
					<!--体验卡规则-->
					<div class="check_live">
					<shiro:hasPermission name="/recommendPositionManage/addRecommend:0x002">
						<input class="input_btn" type="button" onClick="over_payforbox()" name="" value="添加推荐位">
					</shiro:hasPermission>
					</div>
					<div class="homework_main_1">
						<!--账号切换 tlt end-->
						<!--账号信息 content-->
						<!--体验卡表格-->

						<table class="Exper_Tables" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th>序号</th>
									<th>推荐位名称</th>
									<th>添加时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<shiro:hasPermission name="/recommendPositionManage/indexSearch:0x005 or /recommendPositionManage/indexSearch:0x005">
							<tbody id="recommend_position_body">
							</tbody>
							</shiro:hasPermission>
						</table>
					</div>
					<!--未通过账号表格 end-->

					<!--商品名称 end-->
					<div class="m-page">
						<div class="sabpages" id= "turnpages">
						</div>
					</div>
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->
			<!--公共弹出框-->
			<div id="payforpbbg" class="payforpbbg" style="display: none;"></div>
			<div id="payforpbbox" class="payforpbbox1" style="display: none;">
				<div class="w_500">
					<h2 class="pmp_title">
						<span></span><span class="pmp_close" onClick="payforclose()"></span>
					</h2>
					<!--main-->
					<div class="prompt_main cx" style="padding-left: 70px;">
						<table width="100%" class="table_tc">
							<tr height="50px">
								<td width="23%"><span>*</span>推荐位名称</td>
								<td><input name="" type="text" class="set_text" id="recommend_name"></td>
							</tr>
							<tr height="50px">
								<td width="10%"></td>
								<td>
								<input name="" type="hidden" value="${recommend.id }" id="recommend_id">
								<shiro:hasPermission name="/recommendPositionManage/addRecommend:0x002 or /recommendPositionManage/updRecommend:0x004">
								<input name="" type="button" value="确定" class="input_live_01" id="add_recommend_btn">
								</shiro:hasPermission>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!--main end-->
			</div>
		</div>
	</div>
</body>
</html>
