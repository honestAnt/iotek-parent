<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!-------头部 start-------------->
<header class="hearder">
   <div id="logo" class="fl"><img src="${ctx}/web/images/index/logo.jpg"></div>
   <shiro:user>
	   <div id="nav"><span>欢迎您：
		<c:choose>
			<c:when test="${not empty sessionScope['login_user_info'].realName}">
				${sessionScope['login_user_info'].realName}
			</c:when>
			<c:otherwise>
				${sessionScope['login_user'].username}
			</c:otherwise>
		</c:choose>
	   </span><a href="${ctx}/user/logout">退出</a></div>
   </shiro:user>
</header>

<!--公共弹出框-->
<div id="public_bg" class="public_bg"></div>
<div id="public_pmp" class="public_pmp">
<div class="w_330">
  <h2 class="pmp_title"><span>温馨提示</span><span class="pmp_close"  id="pmp_close">关闭</span></h2>
  <!--main-->
  <div class="prompt_main">
    <ul>
      <li class="rel_problem" id="rel_problem_li">
	      <span class="W_icon1 rig_icon"></span>
	      <span id="rel_problem_span">确认信息</span>
      </li>
      <!-- <li class="rel_problem">
      	<span class="W_icon1 sigh_icon">
      	</span>是否举报该问题？
      </li>
      <li class="rel_problem">
       		<span class="W_icon1 error_icon"></span>激活码出现错误！
      </li>
      <li class="rel_problem">上传图片不可大于5M！</li> -->
      <li class="li_btn">
      	<input id="p_cancel_btn" type="button" name="" value="取消" class="cancel_btn">
      	<input id="p_confirm_btn" type="button" name="" value="确认" class="confirm_btn">
      </li>
    </ul>
  </div>
  <!--main end-->
 </div>
</div>
<!--公共弹出框-->