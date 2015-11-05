<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<script src="${ctx}/web/js/common/jquery-1.8.2.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctx }/web/js/cms/information/information.js"></script>
<title>预览资讯</title>
</head>
<body>

<h2>${information.title}</h2>
<c:if test="${fn:length(contentList) <1}">
			${contentList.content }
</c:if>
<c:if test="${fn:length(contentList)>=1}">
<c:forEach items="${contentList}" var="contentPart" varStatus="index" >
	<div  id="content_div_${index.index+1 }"
		<c:if test="${index.index>0 }">
			style="display:none;"
		</c:if>
	>
		${contentPart}
	</div>
</c:forEach>
</c:if>
<c:forEach items="${contentList}" var="contentPart" varStatus="index" >
	<a href="javascript:showCurrentPage(${index.index+1 })">第${index.index+1 }页</a>
</c:forEach>

</body>
</html>

