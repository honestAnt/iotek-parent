<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-体验卡列表</title>
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
	src="${ctx}/web/js/system/account_index.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#system_manage_ul").show();
		$("#system_manage_h3").addClass("subNav currentDd");
		$("#account_li").addClass("li_current");
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
							href="#">账号管理</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
					<!--体验卡规则-->
					<div class="check_sure">
						<input class="input_btn" type="button" onClick="payforbox_over(0,0);"
							name="" value="创建新用户">
					</div>
					<div class="homework_main_1">
						<!--账号切换 tlt-->
						<div id="all_letter_" class="mywork_title_1">
							<ul id="account_ul">
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
						<!--账号切换 tlt end-->
						<!--账号信息 content-->
						<!--网校管理员账号信息-->
						<div id="all_letter_01" class="all_letter_con">
							<div class="homewk_content_1">
								<!--体验卡表格-->
								<div class="exper_table">
									<table class="Exper_Tables" width="100%" border="0"
										cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th width="25%">所属角色</th>
												<th width="25%">用户名</th>
												<th width="25%">创建时间</th>
												<th width="25%">操作</th>
											</tr>
										</thead>
										<tbody id="iotek_tbody"></tbody>
									</table>
								</div>
								<!--未通过账号表格 end-->
								<div class="m-page">
									<div class="sabpages" id="iotek-pages"></div>
								</div>
							</div>
						</div>
						<!--网校管理员账号 end-->
						<!--院校管理员账号信息-->
						<div id="all_letter_02" class="unall_letter_net">
							<div class="homewk_content_1">
								<!--通过账号表格-->
								<div class="exper_table">
									<table class="Exper_Tables" width="100%" border="0"
										cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th width="25%">所属角色</th>
												<th width="25%">用户名</th>
												<th width="25%">创建时间</th>
												<th width="25%">操作</th>
											</tr>
										</thead>
										<tbody id="college_tbody"></tbody>
									</table>
								</div>
								<!--体验卡表格 end-->
								<div class="m-page">
									<div class="sabpages" id="college-pages"></div>
								</div>
							</div>
						</div>
						<!--院校管理员账号信息 end-->
						<!--企业管理员账号信息-->
						<div id="all_letter_03" class="unall_letter_net">
							<div class="homewk_content_1">
								<!--通过账号表格-->
								<div class="exper_table">
									<table class="Exper_Tables" width="100%" border="0"
										cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th width="25%">所属角色</th>
												<th width="25%">用户名</th>
												<th width="25%">创建时间</th>
												<th width="25%">操作</th>
											</tr>
										</thead>
										<tbody id="company_tbody"></tbody>
									</table>
								</div>
								<!--体验卡表格 end-->
							</div>
							<div class="m-page">
								<div class="sabpages" id="company-pages"></div>
							</div>
						</div>
						<!--企业管理员账号信息 end-->
					</div>
					<div class="clear"></div>
					<!--商品名称 end-->
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->
			<!--公共弹出框-->
			<div id="payforpbbg" class="payforpbbg" style="display: none;"></div>
			<div id="payforpbbox" class="payforpbbox1" style="display: none;">
				<div class="w_490">
					<!-- 提交标志 -->
					<input type="hidden" id="type">
					<input type="hidden" id="accountId">
					<input type="hidden" id="username" value="">
					<h2 class="pmp_title">
						<span id="add_account_apan">添加新账户</span><span id="edit_account_apan" style="display: none">编辑账户</span><span class="pmp_close" onClick="payforclose()">关闭</span>
					</h2>
					<!--main-->
					<div class="prompt_main cx">
						<ul>
							<li class="rel_problem_1"><span class="dash_check_02">请选择类型</span>
								<c:forEach items="${manageRoleTypeEnum}" var="manageRoleType"
									varStatus="status">
									<label class="check"><input class="account_01"
										id="topic_type${status.index+1}" name="a1" type="radio"
										value="" 
										<c:if test="${status.index == 0}">checked="checked"</c:if>>${manageRoleType.label}</label>
								</c:forEach></li>
						</ul>
						<div id="pubfoe1" style="display: none;">
							<ul>
								<li class="rel_problem_1"><span class="dash_check_02">请选择角色</span><label><select
										class="exper_date_02" id="iotek_role">
											<c:forEach items="${iotekRoleList}" var="role">
												<option value="${role.id }">${role.name}</option>
											</c:forEach>
									</select></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">用&nbsp;户&nbsp;名</span><label><input
										class="exper_date_03" id="iotek_name" type="text" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">密&nbsp;&nbsp;&nbsp;码</span><label><input
										class="exper_date_03" id="iotek_password" type="password" ></label></li>
								<li class="rel_problem_1 clearfix"><p
										class="dash_check_05 fl">备&nbsp;&nbsp;&nbsp;注</p>
									<p class="account_03 fl">
										<textarea class="account_04" id="iotek_comment" cols="" rows=""></textarea>
									</p></li>
								<li class="li_btn2"><input type="button" name="" value="确定"
									class="confirm_btn" id="iotek_submit_btn"></li>
							</ul>
						</div>
						<div id="pubfoe2" style="display: none;">
							<ul>
								<li class="rel_problem_1"><span class="dash_check_02">请选择角色</span><label><select
										class="exper_date_02" id="college_role">
											<c:forEach items="${collegeRoleList}" var="role">
												<option value="${role.id }">${role.name}</option>
											</c:forEach>
									</select></label></li>
								<li class="rel_problem_1 clearfix"><p
										class="dash_check_02 fl">所教课程</p>
									<p class="account_03 fl">
										<c:forEach items="${courseList}" var="course" begin="0" end="6">
											<label><input class="account_02" type="checkbox"
												name="CheckboxGroup1" value="${course.id}">
												${course.courseName}</label>
										</c:forEach>
									</p></li>
								<li class="rel_problem_1"><span class="dash_check_02">学校名</span><label><select
										class="exper_date_02" id="college_unitId">
										<c:forEach items="${schoolList}" var="school" >
											<option value="${school.id}">${school.name }</option>
										</c:forEach>
											
									</select></label></li>
								<li class="rel_problem_1"><span class="dash_check_02" >教师姓名</span><label><input
										class="exper_date_03" name="" type="text" placeholder="教师姓名" id="college_realname"></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">用&nbsp;户&nbsp;名</span><label><input
										class="exper_date_03" name="" type="text" id="college_name" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">密&nbsp;&nbsp;&nbsp;码</span><label><input
										class="exper_date_03" name="" type="password" id="college_password"></label></li>
								<li class="rel_problem_1 clearfix"><p
										class="dash_check_05 fl">备&nbsp;&nbsp;&nbsp;注</p>
									<p class="account_03 fl">
										<textarea class="account_04" name="" cols="" rows="" id="college_comment"></textarea>
									</p></li>
								<li class="li_btn2"><input type="button" name="" value="确定"
									class="confirm_btn" id="college_submit_btn"></li>
							</ul>
						</div>
						<div id="pubfoe3" style="display: none;">
							<ul>
								<li class="rel_problem_1"><span class="dash_check_02">请选择角色</span><label><select
										class="exper_date_02" id="company_role">
											<c:forEach items="${companyRoleList}" var="role">
												<option value="${role.id }">${role.name}</option>
											</c:forEach>
									</select></label></li>
								<li class="rel_problem_1 clearfix"><p
										class="dash_check_02 fl">所教课程</p>
									<p class="account_03 fl">
										<c:forEach items="${courseList}" var="course" begin="0" end="6">
											<label><input class="account_02" type="checkbox"
												name="CheckboxGroup2" value="${course.id}">
												${course.courseName}</label>
										</c:forEach>
									</p></li>
								<li class="rel_problem_1"><span class="dash_check_02">企业名</span><label><select
										class="exper_date_02" id="company_company">
										<c:forEach items="${companyList}" var="company">
											<option value="${company.id }">${company.name}</option>
										</c:forEach>
									</select></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">领导姓名</span><label><input
										class="exper_date_03" name="" type="text" placeholder="领导姓名" id="company_realName"></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">用&nbsp;户&nbsp;名</span><label><input
										class="exper_date_03" name="" type="text" id="company_username" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></label></li>
								<li class="rel_problem_1"><span class="dash_check_02">密&nbsp;&nbsp;&nbsp;码</span><label><input
										class="exper_date_03" name="" type="password" id="company_password"></label></li>
								<li class="rel_problem_1 clearfix"><p
										class="dash_check_05 fl">备&nbsp;&nbsp;&nbsp;注</p>
									<p class="account_03 fl">
										<textarea class="account_04" name="" cols="" rows="" id="company_comment"></textarea>
									</p></li>
								<li class="li_btn2"><input type="button" name="" value="确定" id="company_submit_btn" 
									class="confirm_btn"></li>
							</ul>
						</div>
					</div>
					<!--main end-->
				</div>
				
			</div>
			<!--添加用户end  -->
		</div>
	</div>
</body>
</html>
