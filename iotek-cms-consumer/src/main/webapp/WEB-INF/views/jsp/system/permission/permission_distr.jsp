<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统后台-权限分配</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="../../js/common/html5.js"></script>
<![endif]-->
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<link href="${ctx}/web/css/system/system .css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/web/js/system/playmain.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/teacherscroll.js"></script>
<script type="text/javascript" src="${ctx}/web/js/system/permission_apply.js"></script>
<script type="text/javascript">
$(function(){
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
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">系统管理 </a><span>&gt;</span><a href="#">权限管理 </a></p></div>
 <!--面包屑 ends-->
<!--内容-->
 <div class="dash_wrapper">
    <div class="nav">
     <ul class="clearfix">
     			<input type="hidden" id="roleId" value="${resourceData.role.id }"/>
               <li class="outl_Title_01">角色名：${resourceData.role.name }</li>
               <li class="outl_Title_02">查看</li>
               <li class="outl_Title_02">添加</li>
               <li class="outl_Title_02">删除</li>
			   <li class="outl_Title_02">修改</li>
			   <li class="outl_Title_02">查询</li>
			   <li class="outl_Title_02">导入</li>
			   <li class="outl_Title_02">导出</li>
			   <li class="outl_Title_02">下载</li>
			   <li class="outl_Title_02">上传</li>
              </ul>
              </div>
               <!--C++学科-->
                <div id="featured">
                  <ul class="outlinemenu">
                  <!-- 角色权限资源信息 第一层-->
                   <c:forEach items="${resourceData.data }" var="parentRoot" varStatus="status">
	                   	<li>
	                      <div
		                      <c:if test="${status.index == 0 }">
		                      	class="outl_header"
		                      </c:if>
		                      <c:if test="${status.index > 0 }">
		                      	class="outl_header border02"
		                      </c:if>
		                       ><a href="javascript:void(0)">
		                       <i 
		                       <c:choose>
		                       	  <c:when test="${childRoot.root.sortNumber == 3 }">class="mgin_em1"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 4 }">class="mgin_em2"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 5 }">class="mgin_em3"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 6 }">class="mgin_em4"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 7 }">class="mgin_em5"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 8 }">class="mgin_em6"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 9 }">class="mgin_em7"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 10 }">class="mgin_em8"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 11 }">class="mgin_em9"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 12 }">class="mgin_em10"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 13 }">class="mgin_em11"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 14 }">class="mgin_em12"</c:when>
		                       	  <c:when test="${childRoot.root.sortNumber == 15 }">class="mgin_em13"</c:when>
		                       	  <c:otherwise>class="mgin_em"</c:otherwise>
		                       </c:choose>
		                       ></i><span>${parentRoot.root.name }</span>
		                       </a>
		                      
		                   <!-- 如果没有有二级菜单 -->
	                       <c:if test="${not empty parentRoot.isSecond }">
			                     <c:forEach items="${parentRoot.leaf }" var="childRoot">
			                      	<!-- 如果菜单下直接跟权限 -->
	                      				<!-- url为空的为无权限资源 -->
	                 				<c:if test="${not empty childRoot.root.url}">
	                    				<label class="free_learning">
		                   					<input  class="account_02" type="checkbox" id="permiss_${childRoot.root.id }" value="${childRoot.root.id }" 
		                   					<c:if test="${childRoot.permission }">checked="checked"</c:if>
		                   					onclick="checkFirst(this)"/>
	                    				</label>
	                				</c:if>
									<c:if test="${empty childRoot.root.url}">
	                      				<i class="account_03"></i>
	                     			</c:if>			                      					
		                      	</c:forEach>
	                       </c:if>
	                       </div>
	                       
	                       <!-- 如果有二级菜单 -->
	                       <c:if test="${empty parentRoot.isSecond }">
		                      <ul class="second_menu" style="display:none;">
		                      	<!-- 第二层菜单 -->
		                      	<c:forEach items="${parentRoot.leaf }" var="childRoot">
		                      		<li><span class="section_name">${childRoot.root.name }</span>
	                      				<!-- 第三层权限信息 -->
	                      				<c:forEach items="${childRoot.leaf }" var="permissRoot" varStatus="status_child">
		                      				<!-- url为空的为无权限资源 -->
	                      					<c:if test="${not empty permissRoot.root.url}">
				                      			<label class="free_learning">
			                      					<input  class="account_02" type="checkbox" id="permiss_${permissRoot.root.id }" value="${permissRoot.root.id }" 
			                      						<c:if test="${permissRoot.permission }">checked="checked"</c:if>
			                      					onclick="checkFirst(this)"/>
				                      			</label>
	                      					</c:if>
											<c:if test="${empty permissRoot.root.url}">
		                      					<i class="account_03"></i>
	                      					</c:if>			                      					
	                      				</c:forEach>
		                      		</li>
		                      	</c:forEach>
		                       </ul>
	                       </c:if>
                    	</li>
                   	
                   </c:forEach>
                 </ul>
                </div>
				<div class="dash_sure1">
<input class="input_btn" type="button" value="保存" name="" onclick="calPermissionStr()"><input class="input_btn1" type="button" value="清空" name="" onclick="clearPermissionStr()">
</div>
   </div>
   <!--内容 end-->
</div>
<!--right end-->

</div>
</div>
</body>
</html>
