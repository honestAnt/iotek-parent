<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cms系统-首页广告管理</title>
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
<script type="text/javascript" src="${ctx}/web/js/banner/add_banner.js"></script>
<script type="text/javascript">
	var enabled = '${enabled}';
	$(function() {
		$("#home_ul").show();
		$("#home_manage_h3").addClass("subNav currentDd");
		$("#home_banner_li").addClass("li_current");
		if (enabled > 0) {
			$("#type_opt_"+enabled).attr("selected","selected");
		}
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
						<a href="#">首页</a><span>&gt;</span><a href="#">网站设置 </a><span>&gt;</span><a
							href="#">首页广告管理</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
					<!--体验卡规则-->
					<div class="check_sure">
						<shiro:hasPermission name="/banner/toAddBanner:0x002">
							<a class="input_btn" href="${ctx }/banner/toAddBanner">创建首页广告</a>
						</shiro:hasPermission>
					</div>
					<div class="check_sure_02">
						<ul class="height clearfix">
							<form action="${ctx}/banner/searchBannerList" method="post" name="listForm"
							id="listForm">
								<input name="pageNo" value="${page.currentPage}" type="hidden"/>
								<li class="fl"><span class="activi">广告名称：</span> <label>
										<input class="text_04" type="text" name="name" id="name" value="${name }">
								</label></li>
								<li class="fl"><span class="activi">是否可用：</span> <label>
										<select class="text_03" name="enabled" id="link_type">
											<option value="0">请选择</option>
											<c:forEach items="${typeList }" var="homeBannerType">
												<option value="${homeBannerType.value}" id="type_opt_${homeBannerType.value}"
												>${homeBannerType.label}</option>
											</c:forEach>
									</select>
								</label></li>
								<li class="fl"><label> 
									<shiro:hasPermission name="/banner/searchBannerList:0x005">
										<input id="query_btn" class="input_btn_02" type="submit" value="查询">
									</shiro:hasPermission>
								</label></li>
							</form>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="exper_table">
						<table class="Exper_Tables" width="100%" border="0"
							cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th width="">序号</th>
									<th width="">图片名称</th>
									<th width="">图片链接</th>
									<th width="">排序</th>
									<th width="">是否可用</th>
									<th width="">添加时间</th>
									<th width="">操作</th>
								</tr>
							</thead>
							<tbody id="friendly_link_body">
								<shiro:hasPermission name="/banner/searchBannerList:0x005">
									<c:forEach items="${page.result}" var="homeBanner" varStatus="index">
										<tr>
											<td>${(page.currentPage-1)*page.pageSize+(index.index+1) }</td>
											<td>${homeBanner.name }</td>
											<td>${homeBanner.imgUrl }</td>
											<td>${homeBanner.sortNumber }</td>
											<c:forEach items="${typeList }" var="homeBannerType">
												<c:if test="${homeBannerType.value == homeBanner.enabled }">
													<td>${homeBannerType.label }</td>
												</c:if>
											</c:forEach>
											<td>
												<fmt:formatDate value="${homeBanner.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>
												<shiro:hasPermission name="/banner/toUpdateBanner:0x004">
													<a href="${ctx }/banner/toUpdateBanner?id=${homeBanner.id }">编辑</a>
												</shiro:hasPermission>
												<shiro:hasPermission name="/banner/delBannerInfo:0x003">
													<!-- 不可用的情况下才能删除 -->
													<c:if test="${homeBanner.enabled == 1 }">
														<span>|</span>
														<a href="javascript:delInfo(${homeBanner.id });">删除</a>
													</c:if>
												</shiro:hasPermission>
											</td>
										</tr>
									</c:forEach>
								</shiro:hasPermission>
							</tbody>
						</table>
					</div>
					<!--未通过账号表格 end-->
					<!--商品名称 end-->
					<!--分页-->
					<shiro:hasPermission name="/banner/searchBannerList:0x005">
						<tags:pagination page="${page}" isPost="true" formName="listForm" />
					</shiro:hasPermission>
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->

		</div>
	</div>
</body>
</html>
