<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/web/js/common/tablejs.js"></script>
<script type="text/javascript">
  
</script>
<!--left-->
<div class="menu_left">
<div id="course_menu">
	<!-- 声明：以后相应菜单功能开发时，对应菜单权限做相关修改。防止权限判断异常! -->
		<!-- 只有管理员才具有权限 -->
	<shiro:hasRole name="administrator">
		  <h3 class="subNav" id="system_manage_h3"><a id="system_manage_a" href="javascript:void(0)"><i class="W_ico20 ico_syst"></i><span>系统管理</span></a></h3>
		   <ul id="system_manage_ul" class="navContent">
		    <li><a id="permission_li" href="${ctx}/systemManage/roleIndex"><i class="icon_arrow"></i>权限管理</a></li>
		    <li><a id="account_li" href="${ctx}/systemManage/accountInfo" ><i class="icon_arrow"></i>账号管理</a></li>
		    <li><a id="permission_resource_li" href="${ctx}/systemManage/permissionResource" ><i class="icon_arrow"></i>权限资源</a></li>
		    <li><a id="operation_resource_li" href="${ctx}/systemManage/operationResourceIndex" ><i class="icon_arrow"></i>操作资源</a></li>
		    <li><a id="operation_record_li" href="${ctx}/systemManage/operationRecordInfo" ><i class="icon_arrow"></i>操作日志</a></li>
		  </ul>
    </shiro:hasRole>
    <%--  <shiro:hasPermission name="/informationSort/infoSort:0x001 or /recommendPositionManage/index:0x001">--%>
     <shiro:hasPermission name="/friendlyLinkManage/index:0x001 or /recommendPositionManage/index:0x001 or /banner/bannerList:0x001">
		  <h3 id="home_manage_h3" class="subNav"><a href="javascript:void(0)"><i class="W_ico20 ico_test"></i><span>网站设置</span></a></h3>
		   <ul class="navContent" id="home_ul">
				<shiro:hasPermission name="/friendlyLinkManage/index:0x001">
		    		<li><a id="friendly_link_manage" href="${ctx}/friendlyLinkManage/index"><i class="icon_arrow"></i>友情链接</a></li>
		    	</shiro:hasPermission>
			    	<!-- <li><a id="" href="javascript:void(0);"><i class="icon_arrow"></i>网站更新</a></li> -->
		    	<shiro:hasPermission name="/recommendPositionManage/index:0x001">
			    	<li><a id="recommend_position_manage" href="${ctx}/recommendPositionManage/index"><i class="icon_arrow"></i>推荐位</a></li>
		    	</shiro:hasPermission> 
		    	<shiro:hasPermission name="/banner/bannerList:0x001">
			    	<li><a id="home_banner_li" href="${ctx}/banner/bannerList"><i class="icon_arrow"></i>首页广告列表</a></li>
		    	</shiro:hasPermission>
		  </ul>
     </shiro:hasPermission>
	 
	 <!-- 商品列表 -->
	 <shiro:hasPermission name="/goodsSeoInfo/goodsSeoListIndex:0x001 or /goodsSeoInfo/goodsSeoAuditListIndex:0x001"> 
	   <h3 id="goods_seo_h3" class="subNav"><a href="javascript:void(0)"><i class="W_ico20 ico_test"></i><span>商品管理</span></a></h3>
	   <ul class="navContent" id="goods_seo_ul">
	    	<shiro:hasPermission name="/goodsSeoInfo/goodsSeoListIndex:0x001">
	    		<li><a id="goods_seo_cms" href="${ctx}/goodsSeoInfo/goodsSeoListIndex"><i class="icon_arrow"></i>SEO信息列表</a></li>
	    	</shiro:hasPermission>
	    	<shiro:hasPermission name="/goodsSeoInfo/goodsSeoAuditListIndex:0x001">
	    		<li><a id="goods_seo_audit_cms" href="${ctx}/goodsSeoInfo/goodsSeoAuditListIndex"><i class="icon_arrow"></i>SEO信息审核列表</a></li>
	    	</shiro:hasPermission>
	  </ul> 
	</shiro:hasPermission>
	  
	   <shiro:hasPermission name="/informationSort/infoSort:0x001 or /information/informationListIn:0x001 or /information/informationListReviewIn:0x001">
	   <h3 id="information_h3" class="subNav"><a href="javascript:void(0)"><i class="W_ico20 ico_test"></i><span>资讯管理</span></a></h3>
	   <ul class="navContent" id="home_information_ul">
	   		<shiro:hasPermission name="/informationSort/infoSort:0x001">
	    	<li><a id="information_sort_li" href="${ctx}/informationSort/infoSort"><i class="icon_arrow"></i>分类列表</a></li>
	    	 </shiro:hasPermission>
	    	<shiro:hasPermission name="/information/informationListIn:0x001">
	    	<li><a id="information_li" href="${ctx}/information/informationListIn"><i class="icon_arrow"></i>资讯列表</a></li>
	    	</shiro:hasPermission>
	    	<shiro:hasPermission name="/information/informationListReviewIn:0x001">
	    	<li><a id="information_review_li" href="${ctx}/information/informationListReviewIn"><i class="icon_arrow"></i>审核列表</a></li>
	    	</shiro:hasPermission>
	  </ul> 
	  </shiro:hasPermission>
</div>
</div>
<!--left end-->