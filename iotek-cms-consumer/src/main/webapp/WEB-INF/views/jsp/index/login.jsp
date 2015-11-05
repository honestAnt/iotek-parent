<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jsp/common/common.jsp"%>
<%@ include file="/WEB-INF/views/jsp/common/common_script.jsp"%>
<title>系统后台-登录</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/web/js/common/html5.js"></script>
<![endif]-->
<link href="${ctx}/web/css/common/jNotify.jquery.css" rel="stylesheet" type="text/css"> 
<link href="${ctx}/web/css/index/login.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="${ctx }/web/js/common/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx }/web/js/index/index.js"></script>
</head>
	<body>
		<shiro:guest>
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
			<form action="${ctx }/user/login" method="post" onsubmit="return validSubmit();">
				<div id="login">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="4" align="center"><a href="javascript:void(0);"><img src="${ctx }/web/images/index/logo.jpg"/></a></td>
						</tr>
						<tr><td colspan="4" height="42"></td></tr>
						<tr>
							<td width="58">用户名：</td>
							<td width="160"><input type="text" id="username" name="username" class="txt" tabindex="1" /></td>
							<td width="12"></td>
							<td rowspan="3"><input type="submit" class="btn" /></td>
						</tr>
						<tr><td colspan="4" height="12"></td></tr>
						<tr>
							<td>密　码：</td>
							<td><input type="password" name="password" id="password" class="txt"  tabindex="2"/></td> 
							<td></td>
						</tr>
					</table>
					<c:choose>
						<c:when
							test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
							<span id="errorLogin" class="red01">该用户不存在.</span>
						</c:when>
						<c:when
							test="${shiroLoginFailure eq 'org.apache.shiro.authc.AccountException '}">
							<span id="errorLogin" class="red01">您输入的帐号或密码有误,请重新输入.</span>
						</c:when>
						<c:when test="${shiroLoginFailure ne null}">
							<span id="errorLogin" class="red01">您输入的帐号或密码有误,请重新输入.</span>
						</c:when>
					</c:choose>
				</div>
			</form>
	</shiro:guest>
	<!-- 登录后 -->
	<shiro:user>
		登录用户账号: <shiro:principal/> 
		<a href="${ctx }/user/logout">登出</a>
	</shiro:user>
</body>
</html>