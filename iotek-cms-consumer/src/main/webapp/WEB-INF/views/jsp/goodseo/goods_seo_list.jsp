<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>系统后台-商品列表</title>
	<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
	<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
	<link href="${ctx}/web/css/goodseo/goods_seo_list.css" rel="stylesheet" type="text/css">
	<%-- <link rel="stylesheet" href="${ctx}/web/css/common/pagination/pagination.css" type="text/css"> --%>
	
	<script type="text/javascript" src="${ctx}/web/js/common/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="${ctx}/web/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/web/js/goodseo/teacherscroll.js"></script>
	<script type="text/javascript" src="${ctx}/web/js/goodseo/goods_seo_list.js"></script>
	
	<script type="text/javascript">
		var goodsStatus = ${goodsStatus};		
		var fromGoodsListVisitFlag = ${fromGoodsListVisitFlag};
		var seoStatus = '${seoStatus}';
		
		$(function() {
			$("#goods_seo_ul").show();
			$("#goods_seo_h3").addClass("subNav currentDd");
			if(seoStatus != 1 ){
				$("#goods_seo_cms").addClass("li_current");
			}else{
				$("#goods_seo_audit_cms").addClass("li_current");
			}
			
			getPage();
		});
		
	</script>
</head>
<body>
<div id="box">
<%--头部--%>
<%@include file="/WEB-INF/views/jsp/common/header.jsp"%>
<%--头部 end--%>
<div class="container">
<%@include file="/WEB-INF/views/jsp/common/menu.jsp"%>

<!--right-->
<div class="main_content">
 <!--面包屑-->
  <div class="crumbs">
  	<p>
  		<a href="#">首页</a><span>&gt;</span>
  		<a href="#">商品管理</a><span>&gt;</span>
  		<c:choose>
         	<c:when test="${seoStatus==3 }">
         		<a href="${ctx}/goodsSeoInfo/goodsSeoListIndex">SEO信息列表</a>
         	</c:when>
         	<c:otherwise>
         		<a href="${ctx}/goodsSeoInfo/goodsSeoAuditListIndex">SEO信息审核列表</a>
         	</c:otherwise>
		</c:choose>
	</p>
	</div>
 <!--面包屑 ends-->
<!--内容-->
   <div class="dash_wrapper">
       <div class="date_tab">
          <!-- 筛选start -->
           <div class="good_search">
             <table width="100%">
               <tr>
                 <td height="45">分类一：<select id="firstLevel_select" name="firstLevel" class="s_select">
                 	<option value="0">请选择</option>
                 	<c:forEach items="${dataMap.firstGoodsLevelList}" var="firstLevels">
                 	<option value="${firstLevels.id}">${firstLevels.content}</option>	
                 	</c:forEach>
                 </select></td>
                 <td>分类二：<select name="secondLevel_select" id="secondLevel_select" class="s_select"><option value="0">请选择</option></select></td>
                 <td>分类三：<select name="thirdLevel_select" id="thirdLevel_select" class="s_select">
                 	<option value="0">请选择</option>
                 	<c:forEach items="${dataMap.thirdGoodsLevelList}" var="thirdLevels">
                 	<option value="${thirdLevels.id}">${thirdLevels.content}</option>	
                 	</c:forEach>
                 </select></td>
                 <td>发布时间：
                 	<input name="beginTime" value="${beginTime}" id="startTime" class="time_txt" type="text" onClick="WdatePicker({readOnly:true})"/>
                 	 - 
                 	<input name="endTime" value="${endTime}" id="endTime" class="time_txt" type="text" onClick="WdatePicker({readOnly:true})" />
                 	<span id="timeMsg" style="color: red"></span>
                 </td>
               </tr>
               <tr>
                 <td height="45" colspan="4"><input id="searchStr" type="text" class="s_txt" placeholder="可使用编号\名称查询" value=""><shiro:hasPermission name="/goodsSeoInfo/searchGoodsSeoList:0x005"><input id="query_btn" type="button" value="查询"  class="btn_1" /></shiro:hasPermission></td>
               </tr>
             </table>
           </div>
           <!-- 筛选end -->

           <!-- 分类 tab -->
           <div class="bj_lists" id="goods_status_div">
            <ul>
            	<c:choose>
            		<c:when test="${seoStatus==1 }">
            			<li><a href="javascript:void(0);" class="hover" id="pending_back_a" >待审核</a></li>
            		</c:when>
            		<c:otherwise>
            			<li><a href="javascript:void(0);" class="hover" id="on_the_shelf_a" onclick="changeGoodsStatus(3,this)">发布</a></li>
             			<li><a href="javascript:void(0);" id="send_back_a" onclick="changeGoodsStatus(2,this)">退回</a></li> 
            		</c:otherwise>
            	</c:choose>
              
            </ul>
          </div>
           <!-- 分类tab end -->

           <!-- 商品列表start -->
           <div class="class_tab" id="on_the_shelf_div">
             <table width="100%">
               <tr id="goods_status_count_tr">
                 <td height="35">已上架 <em class="color1" id="goodsCount_em">${fn:length(dataMap.goodsList)}</em> 件商品</td>
               </tr>
               <tr>
                 <td>
                   <table width="100%" class="g_tab">
					<thead>
                     <tr>
                       <th>序号</th>
                       <th>商品名称</th>
                       <th>分类一</th>
                       <th>分类二</th>
                       <th>分类三</th>
                        <th id="time_th">发布时间</th>
                       <th>操作</th>
                     </tr>
                    </thead>
                    <tbody id="goods_list_tbody">
                    	<%-- <c:forEach items="${dataMap.goodsList}" var="goods">
		                	<tr id="goods_list_tr">
		                       <td>${goods.id}</td>
		                       <td>${goods.goodsName }</td>
		                       <td>${goods.firstLevelName}</td>
		                       <td>${goods.secondLevelName}</td>
		                       <td>${goods.thirdLevelName}</td>
		                       <td>${goods.price}</td>
		                       <td>${goods.salePrice}</td>
		                       <td>${goods.checkUser}</td>
		                       <td>${goods.createTime}</td>
		                       <td>
		                       	<a href="javascript:showMessage(function() {publicbox_close();editGoods(${goods.id},0);},publicbox_close,publicbox_close,'编辑商品前需要先下架，点击确定后将自动下架!')">编辑</a> 
		                       	<a href="javascript:showMessage(function() {publicbox_close(); goodsStatusOperation(${goods.id},0);},publicbox_close,publicbox_close,'操作商品下架后，商品将不在前台显示，继续下架？')">下架</a> 
		                       	<a href="${portalUrl }/goods/detail?goodsId=${goods.id}" target="_blank">预览</a> 
		                       	<a href="javascript:showMessage(function() {publicbox_close(); goodsStatusOperation(${goods.id},5);},publicbox_close,publicbox_close,'删除商品前需要先下架，点击确定后将自动下架!')">删除</a></td>
		                     </tr>
	                     </c:forEach> --%>
                     </tbody>
                   </table>
                 </td>
               </tr>
             </table>
             <!-- 	分页 -->
			  <div class="m-page" >
			   <div id="turnpages" class="sabpages"></div>
			  </div>
			  <!--  分页 -->
           </div>
           <!-- 列表end -->
       </div>
      
<!--内容 end-->
</div>
<!--right end-->
</div>
</div>
</body>
</html>