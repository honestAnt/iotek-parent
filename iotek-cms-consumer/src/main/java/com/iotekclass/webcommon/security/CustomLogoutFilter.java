package com.iotekclass.webcommon.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ClassName: CustomLogoutFilter
 * Description：自定义登出shrio过滤器
 * Author：王啸
 * Date：2014年9月26日 下午2:48:47
 * 
 * @version
 */
public class CustomLogoutFilter extends LogoutFilter {

	private static final Logger log = LoggerFactory.getLogger(CustomLogoutFilter.class);

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		String redirectUrl = getRedirectUrl(request, response, subject);
		try {
			subject.logout();
		} catch (SessionException ise) {
			log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
		}
		issueRedirect(request, response, redirectUrl);
		return false;
	}

}
