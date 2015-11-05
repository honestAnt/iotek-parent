<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cms系统-添加首页广告</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/seo/seo.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript" src="${ctx}/web/js/plubload/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/web/js/banner/init_plupload.js"></script>
<script type="text/javascript" src="${ctx}/web/js/banner/add_banner.js"></script>
</head>
<script type="text/javascript">
$(function() {
	$("#home_ul").show();
	$("#home_manage_h3").addClass("subNav currentDd");
	$("#home_banner_li").addClass("li_current");
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
							href="${ctx }/banner/bannerList">首页广告</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
					<form action="${ctx}/banner/saveBannerInfo" method="post" id="banner_form">
						<input type="hidden" id="homeBannerId" value="${id }" name="id"/>
					<table width="100%" class="table_tc" id="pubfoe1">
						<tbody>
							<tr height="50px">
								<td width="10%" class="seo"><span></span>图片名称：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="name" type="text" id="name" value="${homeBanner.name }"></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span></span>图片悬浮提示：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="imgAlt" type="text" id="imgAlt" value="${homeBanner.imgAlt }"></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>图片链接：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="imgUrl" type="text" id="imgUrl" value="${homeBanner.imgUrl }"></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>排序：</td>
								<td class="seo1"><label><input class="seo_date_03"
										name="sortNumber" type="text" id="sortNumber" value="${homeBanner.sortNumber }"></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>是否可用：</td>
								<td class="seo1"><label>
									<select class="seo_date_02" name="enabled" id="enabled">
										<c:forEach items="${typeList }" var="homeBannerType">
											<option value="${homeBannerType.value}" id="type_opt_${homeBannerType.value}"
											<c:if test="${homeBanner.enabled == homeBannerType.value}"> selected="selected"</c:if>
											>${homeBannerType.label}</option>
										</c:forEach>
									</select>
								</label></td>
							</tr>
							<tr height="50px">
								<td width="12%" class="seo"><span>*</span>背景图片(尺寸1920*370)：</td>
								<td class="seo1"><label>
								<img class="w_240" width="240px" height="280px" id="banner_picture_src" 
								<c:if test="${!empty homeBanner}">
									src="${img_suffix }${homeBanner.imgPath }" 
								</c:if>
								<c:if test="${empty homeBanner}">
									style="display: none"
								</c:if>
								/>
								<br>
								<input type="hidden"  id="imgPath" name="imgPath" value="${homeBanner.imgPath }"/>
								<div id="container">
									<a id="uploadFile" href="javascript:void(0);">选择文件</a>
									<span id="uploadFilePercent" style="display: none;">选择文件</span> 
								</div>
								</label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span></span></td>
								<td class="seo1"><label><input id="save_btn" class="masee" type="button" value="确定"></label></td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
				<!--内容 end-->
			</div>
			<!--right end-->

		</div>
	</div>
</body>
</html>
