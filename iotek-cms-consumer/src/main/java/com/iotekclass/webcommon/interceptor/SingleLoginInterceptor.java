package com.iotekclass.webcommon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.service.permission.ManageOperationRecordService;
import com.iotekclass.cms.service.permission.OperationResourceService;
import com.iotekclass.common.cache.redis.RedisClientTemplate;
import com.iotekclass.common.constants.UserConstants;
import com.iotekclass.common.util.CookieUtils;

/**
 * @author ZengAihui
 *         单一登录拦截器
 */
public class SingleLoginInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(SingleLoginInterceptor.class);

	private static final String ALERT_COOKIE = "alert_info";
	
	/**
	 * 防止redis中键值重复,保证本系统唯一
	 */
	private static final String system_label = "_cms";

	@Autowired
	private RedisClientTemplate redisClientTemplate;
	

	@Reference(version = "1.0.0")
	OperationResourceService operationResourceService;
	
	@Reference(version = "1.0.0")
	ManageOperationRecordService manageOperationRecordService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("url:::        -----"+request.getRequestURL());
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() && (subject.getPrincipal() != null)) {
			String loginName = String.valueOf(subject.getPrincipal());
			String sessionId = request.getRequestedSessionId();
			HttpSession session = request.getSession();
			if (session != null) {
				ManageAccount manageAccount = (ManageAccount) session.getAttribute(UserConstants.LOGIN_USER_KEY);
				loginName = manageAccount == null ? "" : String.valueOf(manageAccount.getId());
			}
			
			if (redisClientTemplate.exists(loginName + system_label)
					&& !redisClientTemplate.get(loginName + system_label).toString().equals(sessionId)) {
				// 判断是否已经保存该登录用户的信息 并且 如果是同一个用户进行重复登录那么允许登录
				logger.info("用户已在其他地方登录。。。");
				CookieUtils.setCookie(response, ALERT_COOKIE, 60, "您已在其他地方登录!");
				String contextPath=request.getContextPath();
				response.sendRedirect(contextPath+"/user/logout");
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
