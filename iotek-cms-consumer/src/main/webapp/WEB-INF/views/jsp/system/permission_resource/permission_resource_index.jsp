<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-权限资源</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/system/system .css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/permission_resource.js"></script>
<script type="text/javascript">
	$(function() {
		$("#system_manage_ul").show();
		$("#system_manage_h3").addClass("subNav currentDd");
		$("#permission_resource_li").addClass("li_current");
	});
</script>
</head>

<body>
	<div id="box">
		<%--头部--%>
		<%@include file="/WEB-INF/views/jsp/common/header.jsp"%>
		<%--头部 end--%>
		<!--头部 ends-->
		<div class="container">
			<!--left-->
			<%@include file="/WEB-INF/views/jsp/common/menu.jsp"%>
			<!--left end -->
			<!--right-->
			<div class="main_content">
				<!--面包屑-->
				<div class="crumbs">
					<p>
						<a href="#">首页</a><span>&gt;</span><a href="#">系统管理 </a><span>&gt;</span><a
							href="#">权限资源</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">

					<div class="sys_zy">
						<div class="class_tab">
							<table width="60%" class="c_tab">
								<tr>
									<td colspan="2" height="60"><input type="button"
										value="添加父类" onclick='showResourceDiv("popup_class_first",0)' class="btn_1" /></td>
								</tr>
								<tr>
									<th height="40">分类名</th>
									<th width="40%">操作</th>
								</tr>
								<!-- 遍历菜单信息 -->
								<c:forEach var="resource" items="${data}">
									<tr>
										<td height="35"><b id="parent_resource_${resource.root.id}">${resource.root.name }</b></td>
										<td><a href='javascript:editResourceDiv("popup_class_first",${resource.root.id},0);'>编辑</a>
										<c:if test="${resource.root.state == 1}">
											<a href="javascript:updateResourceState(${resource.root.id },0,0);">停用</a>
										</c:if> 
										<c:if test="${resource.root.state == 0}">
											<a href="javascript:updateResourceState(${resource.root.id },0,1);">启用</a>
										</c:if> 
										<a href='javascript:deleteResource(${resource.root.id},0);'>删除</a>
											<a href='javascript:showResourceDiv("popup_class_second",${resource.root.id});'>添加子类</a></td>
									</tr>
									<!-- 遍历二级菜单 -->
									<c:forEach var="secondResource" items="${resource.leaf }">
										<c:if test="${!fn:contains(secondResource.name,'0x00') }">
											<tr class="class_2">
												<td height="35" id="second_resource_${secondResource.id }">${secondResource.name }</td>
												<td>
													<a href='javascript:editResourceDiv("popup_class_second",${secondResource.id},${resource.root.id});'>编辑</a>
													<c:if test="${secondResource.state == 1}">
														<a href="javascript:updateResourceState(${secondResource.id },${resource.root.id},0);">停用</a>
													</c:if> 
													<c:if test="${secondResource.state == 0}">
														<a href="javascript:updateResourceState(${secondResource.id },${resource.root.id},1);">启用</a>
													</c:if> 
													<a href='javascript:deleteResource(${secondResource.id},${resource.root.id});'>删除</a>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:forEach>
							</table>
						</div>
					</div>

				</div>
				<!--right end-->


				<!-- 弹出层start -->
				<!-- 添加父类 -->
				<div class="popup" id="popup_class_first">
					<div class="popup_box">
						<div class="pop_title">
							<span class="fl">添加父类</span> <b class="fr"
								onclick='hideDiv("popup_class_first")' id="close">×</b>
						</div>
						<div class="pop_con">
							<form action="" id="add_first_form" method="post">
								<input name="state" value="-1" type="hidden" />
								<table width="100%">
									<tr>
										<td height="10"></td>
									</tr>
									<tr>
										<td height="55"><label>&nbsp;&nbsp;&nbsp;父类名称：&nbsp;<input
												type="text" id="first_input" name="name" class="pop_txt" value="">
												<input type="hidden" id="first_id_input" name="resourceId" class="pop_txt" value=""></label></td>
									</tr>
									<tr>
										<td height="45" align="center"><input type="button"
											class="btn_1" id="first_save_btn" value="确定" /></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>

				<!-- 添加子类 -->
				<div class="popup" id="popup_class_second">
					<div class="popup_box2">
						<div class="pop_title">
							<span class="fl">添加子类</span> <b class="fr"
								onclick='hideDiv("popup_class_second")' id="close">×</b>
						</div>
						<div class="pop_con">
							<form action="" id="add_second_form" method="post">
								<input name="state" value="-1" type="hidden" />
								<table width="100%"> 
									<tr>
										<td height="25"><label id="parent_label">&nbsp;&nbsp;&nbsp;父类名称：&nbsp;
											<input type="text" id="parent_input" disabled="disabled"/>
											<input type="hidden" id="parent_id_input" name="parentId" disabled="disabled"/>
										</label></td>
									</tr>
									<tr><td height="5"></td></tr>
									<tr>
										<td height="25"><label>&nbsp;&nbsp;&nbsp;子类名称：&nbsp;<input
												type="text" id="second_input" name="name" class="pop_txt" value="">
												<input type="hidden" id="second_id_input" name="resourceId" class="pop_txt" value=""></label></td>
									</tr>
									<tr>
										<td>
											<table width="100%" class="qx_tab">
												<tr>
													<th height="25" width="85">权限</th>
													<th>URL(多路径，使用逗号(,)分开)</th>
												</tr>
												<tr>
													<td align="center">查看</td>
													<td><input id="0x001_input" name="url1" type="text" class="pop_txt" value=""/>
													<input id="0x001_id_input" name="urlId1" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">添加</td>
													<td><input id="0x002_input" name="url2" type="text" class="pop_txt" value="">
													<input id="0x002_id_input" name="urlId2" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">删除</td>
													<td><input id="0x003_input" name="url3" type="text" class="pop_txt" value="">
													<input id="0x003_id_input" name="urlId3" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">修改</td>
													<td><input id="0x004_input" name="url4" type="text" class="pop_txt" value="">
													<input id="0x004_id_input" name="urlId4" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">查询</td>
													<td><input id="0x005_input" name="url5" type="text" class="pop_txt" value="">
													<input id="0x005_id_input" name="urlId5" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">上传</td>
													<td><input id="0x009_input" name="url9" type="text" class="pop_txt" value="">
													<input id="0x009_id_input" name="urlId9" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">导入</td>
													<td><input id="0x006_input" name="url6" type="text" class="pop_txt" value="">
													<input id="0x006_id_input" name="urlId6" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">导出</td>
													<td><input id="0x007_input" name="url7" type="text" class="pop_txt" value="">
													<input id="0x007_id_input" name="urlId7" type="hidden" class="pop_txt" value=""/></td>
												</tr>
												<tr>
													<td align="center">下载</td>
													<td><input id="0x008_input" name="url8" type="text" class="pop_txt" value="">
													<input id="0x008_id_input" name="urlId8" type="hidden" class="pop_txt" value=""/></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="3"></td>
									</tr>
									<tr>
										<td align="center"><input type="button" class="btn_1"
											value="确定" id="second_save_btn" /></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				<!-- 弹框end -->
			</div>
		</div>
	</div>
</body>
</html>
