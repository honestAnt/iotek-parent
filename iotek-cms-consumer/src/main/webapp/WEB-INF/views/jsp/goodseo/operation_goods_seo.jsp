<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-专题列表</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/discount/discount.css" rel="stylesheet" type="text/css">
<link href="${ctx}/web/css/goodseo/seo.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${ctx}/web/js/goodseo/operation_seo.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
	
<script type="text/javascript">
	var seoStatus = "${dataMap.seoInfo.status}";
	$(function() {
		$("#goods_seo_ul").show();
		$("#goods_seo_h3").addClass("subNav currentDd");
		if(seoStatus != 1 || seoStatus==''){
			$("#goods_seo_cms").addClass("li_current");
		}else{
			$("#goods_seo_audit_cms").addClass("li_current");
		}
	});
	
	var updateFlag = ${dataMap.updateFlag};
	var goodsId = "${dataMap.good.id}";
	var seoId = "${dataMap.seoInfo.id}";
	
	console.log("updateFlag: "+ updateFlag +"          	goodsId: "+goodsId+"          	seoId: "+seoId);
	
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
						<a href="#">首页</a><span>&gt;</span><a href="#">商品管理</a><span>&gt;</span><a href="${ctx}/goodsSeoInfo/goodsSeoListIndex">SEO信息列表</a><span>&gt;</span><a href="javascript:void(0);">编辑SEO信息</a>
					</p>
				</div>
				<!--面包屑 ends-->
				<!--内容-->
				<div class="dash_wrapper">
				   <table width="100%" class="table_tc">
				     <tbody>
							<tr height="50px">
								<td width="10%" class="seo">商品名称：</td>
								<td class="seo1"><h3>${dataMap.good. goodsName}</h3></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>分类一：</td>
								<td class="seo1"><label><select class="seo_date_02"  disabled="disabled"><option
												value="0">${dataMap.firstLevel.content}</option></select></label></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>分类二：</td>
								<td class="seo1">
								<c:forEach items="${dataMap.secondLevelList}"  var="secondLevel">
									<label> <input class="account_02"  id="${secondLevel.id}_check"  type="checkbox" name="CheckboxGroup1" value="${secondLevel.id }" disabled="disabled">${secondLevel.content }</label>
								</c:forEach> 
								</td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>seo标题：</td>
								<td class="seo1"><label><input id="seoTitle" maxlength="60"  class="seo_date_03"  type="text"  value="${dataMap.seoInfo.title }"  <c:if test="${dataMap.seoInfo.status ==1 }">disabled="disabled"</c:if>></label><span id="seoTitle_msg" style="color: red; display:none;"></span></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>seo关键字：</td>
								<td class="seo1"><label><input id="seoKeywords" maxlength="100" class="seo_date_03" name="" type="text" value="${dataMap.seoInfo.keywords }"  <c:if test="${dataMap.seoInfo.status ==1 }">disabled="disabled"</c:if>></label><span id="seoKeywords_msg" style="color: red; display:none;"></span></td>
							</tr>
							<tr height="50px">
								<td width="10%" class="seo"><span>*</span>seo描述：</td>
								<td class="seo1"><label>
									<textarea id="seoKeywordsDesc"  maxlength="150"  name="" cols="7" rows="7"  class="saey"  style=" width: 350px; height: 100px; margin-bottom: 20px;" <c:if test="${dataMap.seoInfo.status ==1 }">disabled="disabled"</c:if>>${dataMap.seoInfo.keywordsDesc}</textarea>
									<div id="seoKeywordsDesc_msg"  style="display:none; color: red;"></div>
								</td>
							</tr>
							<c:if test="${dataMap.seoInfo.status ==2 }">
								<tr height="50px">
									<td width="10%" class="seo">退回理由：</td>
									<td><label><textarea name="" cols="" rows="" class="saey"  disabled="disabled" style="width: 350px; height: 100px; margin-bottom: 20px;">${dataMap.seoInfo.backReason}</textarea></label></td>
								</tr>
							</c:if>
							<tr height="50px" style="display: none">
								<td width="10%" class="seo"><input id="goodsId" class="seo_date_03" name="" type="text" value="${dataMap.good.id }" disabled="disabled"></td>
								<td width="10%" class="seo"><input id="seoId" class="seo_date_03" name="" type="text" value="${dataMap.seoInfo.id }" disabled="disabled"></td>
								<td width="10%" class="seo"><input id="seoStatus" class="seo_date_03" name="" type="text" value="${dataMap.seoInfo.status }" disabled="disabled"></td>
							</tr>
						</tbody>
				   </table>
				    <div class="marn_top" align="left">
				    <c:choose>
				    	<c:when test="${dataMap.seoInfo.status ==1 }">
				    		<input id="seo_audit_btn" name="" class="masee"  type="button" value="发布">
				    		<input id="seo_back_btn" name="" class="masee"  type="button" value="退回" onclick="javascript:$('#payforpbbg').show();$('#payforpbbox').show();">
				    	</c:when>
				    	<c:otherwise>
				   			<input id="operation_btn" name="" class="masee"  type="button" value="确定">
				    	</c:otherwise>
				    </c:choose>
				   </div>
				 </div>
				<!--内容 end-->
			</div>
			<!--right end-->

		</div>
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
						<tr height="40px">
							<td width="100%">退回理由：</td>
						</tr>
						<tr height="40px">
							<td>
								<textarea id="seoBackReason" name="" cols="" rows="" placeholder="退回理由" class="saey" style="width: 350px; height: 100px; margin-bottom: 20px;"></textarea>
								<div id="seoBackReason_msg" style="display:none; color: red;"></div>
							</td>
						</tr>
						<tr height="50px">
							<td><input id="confirm_back_btn" name="" type="button" value="确定" class="masee"></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!--main end-->
		</div>
	</div>
</body>
</html>
