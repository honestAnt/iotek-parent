<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-操作资源</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>

<link href="${ctx}/web/css/common/common.css" rel="stylesheet" type="text/css">
<link href="${ctx}/web/css/system/system .css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/web/js/common/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/web/js/common/tablejs.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/operation_resource.js"></script>
<script type="text/javascript" src="${ctx}/web/js/common/pagination/jquery.pagination.js"></script>

<script type="text/javascript">
	$(function() {
		$("#system_manage_ul").show();
		$("#system_manage_h3").addClass("subNav currentDd");
		$("#operation_resource_li").addClass("li_current");
		getPage();
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
				<div class="crumbs">
					<p>
						<a href="#">首页</a><span>&gt;</span><a href="#">系统管理 </a><span>&gt;</span><a href="${ctx}/systemManage/operationResourceIndex">操作资源</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">

					<div class="sys_zy">
						<p class="add_1">
							<a href="javascript:void(0);" class="add_btn" onclick='showDiv("popup_class")'>添加资源</a>
						</p>
						<table width="100%" class="sys_zy_list">
							<thead>
								<tr>
									<th width="5%"><b>序号</b></th>
									<th width="27%"><b>URL</b></th>
									<th width="30%"><b>描述</b></th>
									<th width="28%"><b>详情</b></th>
									<th width="10%"><b>操作</b></th>
								</tr>
							</thead>
							<tbody id="resource_list_tbody">
								<tr>
									<td>1</td>
									<td>http://wwww.*****************.com/</td>
									<td>描述内容</td>
									<td>详情内容</td>
									<td>编辑</td>
								</tr>
							</tbody>
						</table>
						<!-- 	分页 -->
						<div class="m-page">
							<div id="turnpages" class="sabpages"></div>
						</div>
						<!--  分页 -->
					</div>
				</div>
			</div>
			<!--right end-->

			<!-- 弹出层start -->
			<div class="popup" id="popup_class" style="display: none;">
				<div class="popup_box1">
					<div class="pop_title">
						<span class="fl" id="title_operate">添加资源</span> <b class="fr" onclick='hideDiv("popup_class")' id="close">×</b>
					</div>
					<div class="pop_con">
						<table width="100%" class="s_cz_tab">
							<tr>
								<td colspan="2" height="10" align="center"><span id="error_msg" style="color: red; display: none;"></span></td>
							</tr>
							<tr>
								<td align="right" height="48">操作路径：</td>
								<td>
									<input id="url" type="text" class="g_txt" onblur="validUrl();" maxlength="200" placeholder=" URL格式: /xxx/xxx"/>
								</td>
							</tr>
							<tr>
								<td height="48" align="right">类型：</td>
								<td>
									<select name="type" id="type">
					                 	<option value="">请选择</option>
					                 	<c:forEach items="${operationResourceTypeEnum}" var="resourceType">
					                 		<option value="${resourceType.value}">${resourceType.label}</option>	
					                 	</c:forEach>
			                 		</select>
			                 	</td>
							</tr>
							<tr>
								<td height="48" align="right">描 述：</td>
								<td><input id="module" type="text" class="g_txt" maxlength="200"/></td>
							</tr>
							<tr>
								<td align="right">详 情：</td>
								<td><textarea id="detail" class="g_area" maxlength="200"></textarea></td>
							</tr>
							<tr>
								<td colspan="2" height="12"><span id="resource_id" style="display: none;"></span></td>
							</tr>
							<tr>
								<td height="28"><span id="operate_type" style="display: none;"></span></td>
								<td><input type="button" class="btn_ok" value="确定" id="add_btn"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<!-- 弹出层end -->
		</div>
	</div>
</body>
</html>
