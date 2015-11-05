<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<title>系统后台-资讯分类列表</title>
<link href="${ctx}/web/css/seo/seo.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/web/js/cms/informationsort/information_sort.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#home_information_ul").show();
		$("#information_h3").addClass("subNav currentDd");
		$("#information_sort_li").addClass("li_current");
	});
</script>
<body>
<%@ include file="/WEB-INF/views/jsp/common/header.jsp"%>
<!--头部 ends-->
<div class="container">
<!--left-->
<%@ include file="/WEB-INF/views/jsp/common/menu.jsp"%>
<!--left end-->
<!--right-->
<div class="main_content">
 <!--面包屑-->
  <div class="crumbs"><p><a href="#">首页</a><span>&gt;</span><a href="#">资讯管理 </a><span>&gt;</span><a href="#">分类列表</a></p></div>
 <!--面包屑 ends-->
<!--内容-->
 <div class="dash_wrapper">
   <div class="class_tab">
        <table width="60%" class="c_tab">
          <tr>
            <td colspan="2" height="60">
            <shiro:hasPermission name="/informationSort/addInfoSort:0x002"> 
            <input type="button" value="添加分类" onclick='addSort(0,"parent")' class="btn_1" id="add_sort"/>
            </shiro:hasPermission>
            </td>
          </tr>
          <tr>
            <th height="40">分类名</th>
            <th width="40%">操作</th>
          </tr>
          <shiro:hasPermission name="/informationSort/infoSortJosn:0x005 or /informationSort/infoSortList:0x005">
          <tbody id="info_sort_content">
          </tbody>
          </shiro:hasPermission>
        </table>
    </div>
 </div>
<!--内容 end-->
</div>
<!--right end-->
</div>

<!-- 弹出层start -->

<!-- 添加修改子类 -->
<div class="popup" style="display:none;" id="upd_info_sort">
  <div class="popup_box_add">
    <div class="pop_title">
      <span class="fl"></span>
      <b class="fr" onclick='closeBox("updInfoSort")' id="close1">×</b>
    </div>
    <div class="pop_con">
    <form action="" method="post" id="infor_sort_form">
       <table width="100%">
         <tr><td colspan="2" height="10"></td></tr> 
         <tr id="_parent">
           <td width="28%" height="45" align="right"><span class="red">*</span> 上级分类：</td>
           <td><label><select  name="parentId" id="parent_id" class="asktion_05" disabled="disabled"></select></label></td> 
         </tr>
         <tr>
           <td height="45" align="right"><span class="red">*</span> 名　　称：</td>
           <td><label>
           	<input type="text" name="sortName" id="sort_name" class="pop_txt">
           	<input type="hidden"  id="sort_name_hidden">
           </label></td> 
         </tr>
         <tr>
           <td height="45" align="right">SEO标题：</td>
           <td><label><input type="text" name="seoTitle" id="seo_title" class="pop_txt"></label></td> 
         </tr> 
         <tr>
           <td height="45" align="right">SEO关键字：</td>
           <td><label><input type="text" name="seoKeywords" id="seo_keywords" class="pop_txt"></label></td> 
         </tr> 
         <tr><td colspan="2" height="5"></td></tr>
         <tr>
           <td height="45" align="right">SEO描述：</td>
           <td><label><textarea class="text_add_des" id="seo_descr" name="seoDescr"></textarea></label></td> 
         </tr>
         <tr><td colspan="2" height="5"></td></tr> 
         <tr>
           <td height="45" ></td>
           <td align="left">
           	<shiro:hasPermission name="/informationSort/addInfoSort:0x002 or /informationSort/updInfoSort:0x004">
              <input type="button" class="btn_1" value="确定" id="sub_btn"/>
             </shiro:hasPermission>
              <input type="button" class="btn_1" value="取消" onclick='closeBox("updInfoSort")'/>
           </td>
         </tr>
       </table>
       </form>
    </div>
  </div>
</div>
</body>
</html>
