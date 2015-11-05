<%@ page language="java" pageEncoding="UTF-8" %>
<!-- 公用js -->
<script src="${ctx}/web/js/common/jquery-1.8.2.min.js" type="text/javascript" ></script>
<script src="${ctx}/web/js/common/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript">  
	var baseUrl = '${baseUrl}';
	var ctx = '${ctx}';
	var img_suffix = '${img_suffix}';
	var video_suffix = '${video_suffix}';
	var video_html_suffix = '${video_html_suffix}';
	var file_suffix = '${file_suffix}';
	var qq_url = '${qq_url}';
	var cas_login = '${cas_login}';
	var cas_register = '${cas_register}';
	var cas_to_register = '${cas_to_register}';
	var community_url = '${community_url}';
	var cas_server = '${cas_server}';
	var portal_url = '${portal_url}';
	var comm_url = '${comm_url}';
	var manage_url = '${manage_url}';
	//用户信息
	var ctx_user_id = '${ctx_user_id}';
	if (ctx_user_id != 'undefined' && ctx_user_id != 0 && ctx_user_id != '') {
		var ctx_user = eval('('+'${ctx_user}'+')');
	}
	//未登录用户的头像
	var noLoginIcon = '/iotek2/person/sys/visitor.jpg';
	
	var ipAddress = '';
	
	var wechat_appid = '${wechat_appid }';
	var wechat_access_token = '${wechat_access_token}';
	var wechat_max_token_pass_time = '${wechat_max_token_pass_time}';	//微信接口签名有效期(毫秒)
	var wechat_access_token_expires = '${wechat_access_token_expires}';
	
	var wechat_signature_time = '${wechat_signature_time }';
	var wechat_noncestr = '${wechat_noncestr }';
	var wechat_signature = '${wechat_signature }';
	
</script>
<!-- 公用css -->
<link href="${ctx}/web/css/common/common.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
<script src="${ctx}/web/js/common/html5.js"></script>
<![endif]-->
<script src="${ctx}/web/js/common/common.js" type="text/javascript"></script>
<script type="text/javascript">
	getAddressByIp();
</script>
<!-- 覆盖placeholder,以适应所有浏览器 -->
<script src="${ctx}/web/js/common/jquery.enplaceholder.js" type="text/javascript"></script>