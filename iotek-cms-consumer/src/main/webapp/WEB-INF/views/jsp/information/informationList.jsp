<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<title>咨询列表</title>
<link href="${ctx}/web/css/seo/seo.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/web/js/cms/information/information.js"></script>
<script type="text/javascript" src="${ctx}/web/js/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx}/web/css/information/questionlist.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

$(function(){
	$(function() {
		$("#home_information_ul").show();
		$("#information_h3").addClass("subNav currentDd");
		$("#information_li").addClass("li_current");
	});
});
	
</script>
</head>
<body>
<div id="box">
<%--头部--%>
<%@ include file="/WEB-INF/views/jsp/common/header.jsp"%>
<%--头部 end--%>
<div class="container">
<%@ include file="/WEB-INF/views/jsp/common/menu.jsp"%>
<!--头部 ends-->
<!--right-->
<div class="main_content">
 <!--面包屑-->
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">资讯管理</a><span>&gt;</span><a href="#">资讯列表</a></p></div>
 <!--面包屑 ends-->
 <!--问答列表-->
 <div class="asksion_list">
 <!-- 添加资讯按钮 -->

 <div class="asktion" >
<shiro:hasPermission name="/information/addInformation:0x002">
<a href="${ctx }/information/addInformation?id=0&statusType=1" ><input class="next_btn" style="margin-left:0;" type="submit" value="添加资讯"></a>
</shiro:hasPermission>
</div>
<br><br>
 <!--查询-->
	 <div class="asktion" >
	 <form action="${ctx }/information/informationListSearch" name="informationList" id="informationList" method="post">
		 <label>
	 	 <span>
		 	 <select class="seo_date_021" id="firstLevelId" name="firstLevelId">
		  		<option value="0">请选择分类一</option>
		  		<c:forEach items="${firstLevelList}" var="first">
		  			<option value="${first.id}" <c:if test="${first.id==firstLevelId}">selected="true"</c:if>> ${first.sortName}</option>
		  		</c:forEach>
		  	</select>
	  	</span>
	  	<input id="first_level_id_input" type="hidden" value="${firstLevelId }"/>
	  	<input id="second_level_id_input" type="hidden" value="${secondLevelId }"/>
	  	</label>
	  	<label>
	  	<span>
	  		<select class="seo_date_021" id="secondLevelId" name="secondLevelId">
			  	<option value="0">请选择分类二</option>
			  	<c:forEach items="${secondstLevelList}" var="first">
		  		</c:forEach>
		    </select>
	  	</span>
	  	</label>
	     <span class="asktion_01">发布时间</span><label><input name="beginTime" value="${beginTime}" id="comm_beginTime" class="asktion_02" type="text" onClick="WdatePicker({readOnly:true})"> </label>
	     <em>--</em><label><input class="exper_date_02" name="endTime" value="${endTime}" id="comm_endTime" type="text" onClick="WdatePicker({readOnly:true})" /></label>
	     <span class="asktion_03">标题</span>
		 <input name="title" id="title" type="text" value="${title }"/>
		 <input name="status" value="${status}" id="status" type="hidden"/>
		 <shiro:hasPermission name="/information/informationListSearch:0x005">
		 <input class="next_btn" type="submit" value="查询">
		 </shiro:hasPermission>
	  </form>
	 </div>
	 <!--查询 end-->
	 <shiro:hasPermission name="/information/informationListSearch:0x005">
	 <!--列表导航-->
	 	<div class="asktion_menu">
			<ul>
         			<li 
        				<c:if test="${status ==1}">
                		class="hovertab_mywork" 
        				</c:if>
					>
         			<a href="javascript:formSumint(1);"><span>已发布</span></a>
         			</li>
         			<li 
        				<c:if test="${status ==2}">
                		class="hovertab_mywork" 
                		</c:if>
					>
         			<a href="javascript:formSumint(2);"><span>退回</span></a>
         			</li>

           </ul>
		 </div>
	 <!--列表导航 end-->
		    <!--查询表格-->
	<div class="saktion_table">
     <table class="Exper_Tables" width="100%" border="0" cellpadding="0" cellspacing="0">
       <thead>
          <tr>
             <th width="5%">序号</th>
             <th width="15%">分类一</th>
             <th width="15%">分类二</th>
             <th width="35%">标题</th>       
             <th width="5%">发布时间</th>
             <th width="15%">操作</th>
          </tr>
       </thead>
       <tbody>
       <c:forEach items="${page.result}" var="information" varStatus="index">
         <tr>
           <td>${index.index+1}</td>
           <td>${information.firstLevelName}</td>
           <td>
           		<c:if test="${information.secondLevelName==null||information.secondLevelName==''}">
           			--
           		</c:if>
           		<c:if test="${information.secondLevelName!=null&&information.secondLevelName!=''}">
           			${information.secondLevelName}
           		</c:if>
           </td>
           <td>${information.title}</td>
           <td><fmt:formatDate pattern="yyyy-MM-dd" value="${information.deliveryTime}" type="both"/></td>
           <td class="operation_td">
           <shiro:hasPermission name="/information/editInformation:0x004">
           <a href="${ctx}/information/editInformation?id=${information.id}&statusType=1">编辑</a>
           </shiro:hasPermission>
           <span>&nbsp;</span>
             <c:if test="${information.status==2}">
                	<a href="#" onclick="returnReasonClick('${information.returnReason}')">退回理由</a>
                	<span>&nbsp;</span>
        	 </c:if>
        	 <shiro:hasPermission name="/information/previewInformation:0x005">
        	 <a href="${ctx}/information/previewInformation?id=${information.id}" target="_blank">预览</a>
        	 </shiro:hasPermission>
        	<span>&nbsp;</span>
        	<shiro:hasPermission name="/information/deleteInformation:0x003">
           <a href="javascript:void(0)" onclick="deleteInformation(${information.id},${information.status})">删除</a>
           </shiro:hasPermission>
           </td>
         </tr>
        </c:forEach>
       </tbody>      
     </table>
     <div class="m-page">
      <div class="sabpages"> 
      <shiro:hasPermission name="/information/informationListSearch:0x005">
      <tags:pagination page="${page}" isPost="true" formName="informationList" />
      </shiro:hasPermission>
       </div>
       </div>
   </div>
	 <!--查询表格 end-->
</shiro:hasPermission>
	 </div>
	 <!--问答列表 end-->
</div>
<!--right end-->
</div>
</div>
		<%---弹出框------%>
		<%-- 弹出框 end --%>
</body>

</html>