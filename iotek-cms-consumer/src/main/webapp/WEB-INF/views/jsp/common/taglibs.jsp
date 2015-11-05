<%@page import="com.iotekclass.common.constants.UserConstants"%>
<%@page import="com.iotekclass.common.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}"/>
<c:set var="v" value="20131213"/>
<c:set var="img_suffix" value="<%=Config.getImgSuffix()%>"/>
<c:set var="devMode" value="<%=Config.getDevMode()%>"/>
<%--查看是否是本地环境，防止测试和线上环境出问题--%>
<c:if test="${fn:contains(baseUrl, 'localhost')}">
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
</c:if>
<c:if test="!${fn:contains(baseUrl, 'localhost')}">
    <c:set var="ctx" value=""/>
</c:if>
<c:set var="file_suffix" value="<%=Config.getFileSuffix()%>"/>
<c:set var="video_suffix" value="<%=Config.getVideoSuffix()%>"/>
<c:set var="video_html_suffix" value="<%=Config.getVideoHtmlSuffix()%>"/>
<c:set var="qq_url" value="<%=Config.getQQSuffix()%>"/>
<c:set var="jsonMap" value="<%=new JsonMapper()%>"></c:set>

<c:set var="ctx_user_id" value="${sessionScope.login_user_id }"/>
<c:set var="ctx_user" value="${ sessionScope.json_login_user}"/> 

<c:set var="portalUrl" value="<%=Config.getPortalUrl()%>"/>
