package com.iotekclass.cms.web.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.service.filter.FilterService;
import com.iotekclass.cms.service.permission.ManageRoleResourceRelService;
import com.iotekclass.cms.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 
 * @Description 后台权限分配action
 * @author wangfengbao
 * @date 2015年5月4日 下午3:05:15
 *
 */
@Controller
@RequestMapping(value="/systemManage")
public class PermissionDetailAction extends BaseController {

	@Reference(version = "1.0.0")
	ManageRoleResourceRelService manageRoleResourceRelService;

	@Reference(version = "1.0.0")
	FilterService filterService;
	
	/**
	 * 角色id
	 */
	private int roleId;

	/**
	 * 权限资源id字符串
	 */
	private String permissionStr;

	/**
	 * 资源
	 */
	private String url;
	
	/**
	 * 校验信息
	 */
	private boolean valid;
	
	/**
	 * 
	 * @description 保存角色权限资源信息 
	 * @author wangfengbao
	 * @date 2015年5月4日 下午3:12:19
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	//	@Action(value = "saveRoleResource",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/saveRoleResource",method={RequestMethod.GET,RequestMethod.POST})
	public void saveRoleResource(HttpServletRequest request,HttpServletResponse response){
		dealJspParams(request);
		manageRoleResourceRelService.saveRoleResource(roleId, permissionStr, getLoginUserId(request));
		response.setStatus(response.SC_OK);
	}

	/**
	 * 
	 * @description 校验用户权限资源 
	 * @author wangfengbao
	 * @date 2015年5月6日 下午2:18:57
	 *
	 * @return
	 */
//	@Action(value = "validAccountPermission",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/validAccountPermission",method={RequestMethod.GET,RequestMethod.POST})
	public void validAccountPermission(HttpServletRequest request, Writer writer){
		dealJspParams(request);
		//获取校验信息
		valid = filterService.validUrl(getLoginUserId(request), url);
		try {
			writer.write(objToJsonData("valid", valid));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 简单处理页面传递过来的参数
	 * @param request
	 */
	private void dealJspParams(HttpServletRequest request) {
		roleId = convertInteger(request.getParameter("roleId"));
		permissionStr = convertStr(request.getParameter("permissionStr"));
		url = convertStr(request.getParameter("url"));
		valid = Boolean.getBoolean(convertStr(request.getParameter("valid")));
	}
}
