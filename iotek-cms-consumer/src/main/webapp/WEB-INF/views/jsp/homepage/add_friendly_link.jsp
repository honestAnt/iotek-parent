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
<link href="${ctx}/web/css/seo/seo.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript"
	src="${ctx}/web/js/homepage/add_friendly_link.js"></script>
<script type="text/javascript" src="${ctx}/web/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${ctx}/web/js/swfupload/handlers.js"></script>
<script type="text/javascript" src="${ctx}/web/js/swfupload/plugins/swfupload.queue.js"></script>
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
					<div class="radil_01">
						<c:forEach items="${friendlyLinkTypeEnum }" var="friendlyLinkType"
							varStatus="status">
							<span><input id="topic_type${status.index+1 }"
								type="radio"
								<c:if test="${status.index == 0}">checked="checked"</c:if>
								value="${friendlyLinkType.value }" name="1">
								${friendlyLinkType.label }</span>
						</c:forEach>
					</div>
					<form action="${ctx}/friendlyLinkManage/addLink" method="post" id="word_form">
						<input type="hidden" name="type" value="1"/>
						<input type="hidden" id="linkId" value="" name="id"/>
					<table width="100%" class="table_tc" id="pubfoe1">
						<tbody>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>链接名称：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="name" type="text" id="word_name"></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>链接地址：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="url" type="text" id="word_url"></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>打开方式：</td>
								<td class="seo1"><label><select class="seo_date_02" name="openWay" id="word_open_way"><c:forEach items="${friendlyLinkOpenWayEnum }" var="openWay">
									<option value="${openWay.value}">${openWay.label}</option>
								</c:forEach></select></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span></span></td>
								<td class="seo1"><label><input id="word_ok_btn" class="masee" type="button" value="确定"></label></td>
							</tr>
						</tbody>
					</table>
					</form>
					<form action="${ctx}/friendlyLinkManage/addLink" method="post" id="picture_form">
					<input type="hidden" name="type" value="2"/>
					<input type="hidden" id="linkId" value="" name="id"/>
					<table width="100%" class="table_tc" id="pubfoe2"
						style="display: none;">
						<tbody>
							<tr height="50px">
								<td width="12%" class="seo"><span>*</span>链接名称：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="name" type="text" id="picture_name"></label></td>
							</tr>
							<tr height="50px">
								<td width="12%" class="seo"><span>*</span>链接地址：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="url" type="text" id="picture_url"></label></td>
							</tr>
							<tr height="50px">
								<td width="12%" class="seo"><span>*</span>打开方式：</td>
								<td class="seo1"><label><select class="seo_date_03" name="openWay" id="picture_open_way"><c:forEach items="${friendlyLinkOpenWayEnum }" var="openWay">
									<option value="${openWay.value}">${openWay.label}</option>
								</c:forEach></select></label></td>
							</tr>
							<tr height="50px">
								<td width="13%" class="seo"><span>*</span>鼠标移动显示：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="title" type="text" id="picture_title"></label></td>
							</tr>
							<tr height="50px">
								<td width="12%" class="seo"><span>*</span>链接图片：</td>
								<td class="seo1"><label>
								<img class="w_240" width="240px" height="280px" id="link_picture_src" src="" style="display: none"/><br>
								<input name="" class="masee" type="button" value="上传图片" id="link_picture">
								<input type="hidden"  name="filePath" value="" id="inputPicture"/>
								</label></td>
							</tr>
							<tr height="50px">
								<td width="12%" class="seo"><span></span></td>
								<td class="seo1"><label><input id="picture_ok_btn" class="masee" type="button" value="确定"></label></td>
							</tr>
						</tbody>
					</table>
					</form>
					<!-- <div class="marn_top">
						<input name="" class="masee" type="button" value="确定">
					</div> -->
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->

		</div>
	</div>
</body>
</html>
