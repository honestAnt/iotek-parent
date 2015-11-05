package com.iotekclass.webcommon.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iotekclass.cms.utils.ParseShiroDefaultMap;
import com.iotekclass.common.cache.redis.RedisClientTemplate;
import com.iotekclass.common.constants.UserConstants;
import com.iotekclass.common.util.Collections3;
import com.iotekclass.common.util.JsonMapper;

/**
 * 
 * @Description 用户权限拦截器 
 * @author wangfengbao
 * @date 2015年5月8日 下午4:02:58
 *
 */
public class UserPermissionInterceptor implements HandlerInterceptor {

	private static final String ADMIN_ROLE = "1";

	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	/**
	 * 防止redis中键值重复,保证本系统唯一
	 */
	private static final String system_label = "_cms";
	
	@Autowired
	ParseShiroDefaultMap parseShiroDefaultMap;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Subject subject = SecurityUtils.getSubject();
		String targetUrl = request.getRequestURI().trim().replace("/cms/", "/");

		//判断缓存中是否具有shiro权限数据,如果没有，解析shiro配置文件获取相应权限配置信息
		if (!redisClientTemplate.exists(ParseShiroDefaultMap.SHIRO_DEFAULT_STR+system_label)) {
			redisClientTemplate.set(ParseShiroDefaultMap.SHIRO_DEFAULT_STR+system_label, parseShiroDefaultMap.parseXml());
		}
		
		String urlsList = redisClientTemplate.get(ParseShiroDefaultMap.SHIRO_DEFAULT_STR+system_label);

		if (!urlsList.contains(targetUrl)
				&& (!targetUrl.contains("/web/js") || !targetUrl.contains("/web/css")
						|| !targetUrl.contains("/web/images") || !targetUrl.contains("/web/pages"))) {
			//判断用户是否具有权限
			if (subject.isAuthenticated() && (subject.getPrincipal() != null)) {
				int userId = Integer.parseInt(request.getSession().getAttribute(UserConstants.LOGIN_USER_ID_KEY)
						.toString());
				String role = request.getSession().getAttribute(UserConstants.ACCOUNT_ROLE_KEY).toString();
				//获取登录用户的权限信息
				//判断用户是否有访问权限
				if (!ADMIN_ROLE.equals(role)) {
					if (redisClientTemplate.exists(userId + UserConstants.ACCOUNT_PERMISSION_KEY+system_label)) {
						
						String urlStr = redisClientTemplate.get(userId + UserConstants.ACCOUNT_PERMISSION_KEY+system_label);
						JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
						List<String> urls = jsonMapper.fromJson(urlStr, jsonMapper.contructCollectionType(List.class, String.class));
						
						if (Collections3.isNotEmpty(urls)) {
							if (!urls.contains(targetUrl)) {
								String contextPath=request.getContextPath();
								response.sendRedirect(contextPath+"/user/anon");
//								request.getRequestDispatcher("/user/anon").forward(request, response);
								return false;
							}
						}
					}
				}

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
