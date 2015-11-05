<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-权限列表</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/system/system .css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/web/js/common/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/system/permission_index.js"></script>
<script type="text/javascript">
	$(function() {
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
				<div class="crumbs">
					<p>
						<a href="#">首页</a><span>&gt;</span><a href="#">系统管理 </a><span>&gt;</span><a
							href="#">权限管理 </a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
					<!--创建新角色-->
					<div class="check_sure">
						<!-- 判断是否有新增角色权限 -->
						<%-- <shiro:hasPermission name="/systemManage/addRole:0x002"> --%>
							<input class="input_btn" type="button" onClick="payforbox_override(0);"
								name="" value="创建新角色">
						<%-- </shiro:hasPermission> --%>
						<input type="hidden" id = "roleId"/>
					</div>
					<div class="homework_main_1">
						<!--角色切换 tlt-->
						<div id="all_letter_" class="mywork_title_1">
							<ul id="role_ul">
								<c:forEach items="${manageRoleTypeEnum }" var="manageRoleType"
									varStatus="status">
									<li id="all_letter_${status.index+1}"
										<c:choose>
										<c:when test="${status.index == 0}">class="hovertab_mywork"</c:when>
										<c:otherwise>class="nor_mywork"</c:otherwise>
									</c:choose>
										onClick="x:hover_zzjs_net(${status.index+1});"
										typeId="${manageRoleType.value}">${manageRoleType.label}</li>
								</c:forEach>
							</ul>
						</div>
						<!--角色切换 tlt end-->
						<!--网校管理员-->
						<div id="all_letter_01" class="all_letter_con">
							<div class="homewk_content_1">
								<!--网校管理员表格-->
								<div class="exper_table">
									<table class="Exper_Tables" width="100%" border="0"
										cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th width="50%">角色名</th>
												<th width="50%">操作</th>
											</tr>
										</thead>
										<tbody id="iotek_tbody"></tbody>
									</table>
								</div>
								<!--网校管理员表格 end-->
							</div>
							<div class="m-page">
								<div class="sabpages" id="iotek-pages"></div>
							</div>
						</div>
						<!--网校管理员 end-->
						<!--院校管理员-->
						<div id="all_letter_02" class="unall_letter_net">
							<div class="homewk_content_1">
								<!--院校管理员表格-->
								<div class="exper_table">
									<table class="Exper_Tables" width="100%" border="0"
										cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th width="50%">角色名</th>
												<th width="50%">操作</th>
											</tr>
										</thead>
										<tbody id="college_tbody"></tbody>
									</table>
								</div>
								<!--院校管理员表格 end-->
							</div>
							<div class="m-page">
								<div class="sabpages" id="college-pages"></div>
							</div>
						</div>
						<!--院校管理员end-->
						<!--企业管理员-->
						<div id="all_letter_03" class="unall_letter_net">
							<div class="homewk_content_1">
								<!--企业管理员表格-->
								<div class="exper_table">
									<table class="Exper_Tables" width="100%" border="0"
										cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th width="50%">角色名</th>
												<th width="50%">操作</th>
											</tr>
										</thead>
										<tbody id="company_tbody"></tbody>
									</table>
								</div>
								<!--企业管理员表格 end-->
							</div>
							<div class="m-page">
								<div class="sabpages" id="company-pages"></div>
							</div>
						</div>
						<!--企业管理员end-->
					</div>
					<div class="clear"></div>
					<!--商品名称 end-->
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->
			<div id="payforpbbg" class="payforpbbg"></div>
			<!--创建角色弹出框-->
			<div id="payforpbbox" class="payforpbbox">
				<div class="w_380">
					<h2 class="pmp_title">
						<span>创建新角色</span><span class="pmp_close" onClick="payforclose()">关闭</span>
					</h2>
					<input type="hidden" id="input_name" value="">
					<!--main-->
					<div class="prompt_main">
						<ul>
							<li class="rel_problem_1"><span>选择类型</span><label><select
									class="exper_date_02" id="manageRoleType">
										<c:forEach items="${manageRoleTypeEnum}" var="manageRoleType">
											<option value="${manageRoleType.value}" id="manageRole_${manageRoleType.value}" >${manageRoleType.label}</option>
										</c:forEach>
								</select></label></li>
							<li class="rel_problem_1"><span>角 色 名</span><label><input
									id="role_name" class="exper_date_03" name="" type="text"
									placeholder="系统管理员"></label></li>
							<li class="li_btn1"><input type="button" name="" value="确定"
								id="submit_btn" class="confirm_btn"></li>
						</ul>
					</div>
					<!--main end-->
				</div>
			</div>
			<!--创建角色弹出框end-->
		</div>
	</div>
</body>
</html>
