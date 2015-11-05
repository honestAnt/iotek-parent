/**
 * 
 */
package com.iotekclass.webcommon.security;

import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.service.permission.ManageAccountService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author ZengAihui
 * 
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private ManageAccountService manageAccountService;

	@Autowired
	private CustomManageShiroSession customManageShiroSession;

	/**
	 * 自定义登录成功处理
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {

		ManageAccount manageAccount = manageAccountService.getByNameAndPwd(subject.getPrincipal().toString(), "");
		if (manageAccount != null) {
			customManageShiroSession.generateUserSessions(manageAccount);
		}
		issueSuccessRedirect(request, response);
		return false;
	}
}
