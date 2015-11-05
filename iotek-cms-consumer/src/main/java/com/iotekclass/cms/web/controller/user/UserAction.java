/**
 * 
 */
package com.iotekclass.cms.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.service.permission.ManageAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iotekclass.cms.web.controller.BaseController;

/**
 * @Description 后台用户action
 * @author wangfengbao
 * @date 2015年4月24日 下午1:30:47
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserAction extends BaseController {

	@Reference(version = "1.0.0")
	private ManageAccountService manageAccountService;

	/**
	 * 
	 * @description 后台登录 
	 * @author wangfengbao
	 * @date 2015年4月24日 下午1:33:59
	 *
	 * @return
	 */
	@RequestMapping(value="/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request) {
		logger.debug("登录用户: " + this.getLoginUser(request));
		logger.debug("id: "+manageAccountService.getById(1));
		return "/index/login";
	}
	
//	@Action(value = "goLogin", results = { @Result(name = SUCCESS, location = "/web/pages/index/login.jsp") })
	@RequestMapping(value="/goLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String goLogin() {
		return "/index/login";
	}
	
	/**
	 * 
	 * @description 无权限页面 
	 * @author wangfengbao
	 * @date 2015年5月4日 下午2:01:35
	 *
	 * @return
	 */
//	@Action(value = "anon", results = { @Result(name = SUCCESS, location = "/web/pages/index/shiro_anon.jsp") })
	@RequestMapping(value="/anon", method = { RequestMethod.GET, RequestMethod.POST })
	public String anon() {
//		logger.debug("" + this.getLoginUser());
		return "/index/shiro_anon";
	}
	
	/**
	 * 具体登出操作交由shiro
	 * 
	 * @return
	 */
//	@Action(value = "logout", results = { @Result(name = SUCCESS, location = "/user/goLogin", type = "redirect") })
	@RequestMapping(value="/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		return "redirect:/user/goLogin";
	}
}
