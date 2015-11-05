package com.iotekclass.cms.web.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.cms.service.permission.ManageAccountRoleService;
import com.iotekclass.cms.service.permission.ManageResourceService;
import com.iotekclass.cms.service.permission.ManageRoleService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.ManageRoleTypeEnum;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: PermissionAction   
 * @Description： 系统管理
 * @Author：hujing
 * @Date：2015年4月28日 下午1:59:39
 * @version
 */
@Controller
@RequestMapping(value="/systemManage")
public class PermissionAction extends BaseController {

	/**
	 * 显示条数
	 */
	private static final int PAGE_SIZE = 10;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page<ManageRole> page = new Page(PAGE_SIZE);

	@Reference(version = "1.0.0")
	ManageRoleService manageRoleService;
	@Reference(version = "1.0.0")
	ManageAccountRoleService manageAccountRoleService;

	@Reference(version = "1.0.0")
	ManageResourceService manageResourceService;
	
	/**
	 * 角色类别
	 */
	private int type;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 角色id
	 */
	private int id;
	/**
	 * 角色对象
	 */
	private ManageRole manageRole;
	/**
	 * 是否请求成功标识符
	 */
	private String code;
	/**
	 * 
	 * @Description: 权限分配首页
	 * @Author：hujing
	 * @Date：2015年4月28日 下午1:59:34
	 * @return
	 * @throws
	 */
//	@Action(value = "roleIndex", results = { @Result(name = SUCCESS, location = "/web/pages/system/permission/permission_index.jsp") })
	@RequestMapping(value="/roleIndex",method={RequestMethod.GET,RequestMethod.POST})
	public String roleIndex(HttpServletRequest request) {
		dealJspParams(request);
		request.setAttribute("manageRoleTypeEnum",EnumSet.of(ManageRoleTypeEnum.IOTEK_ADMIN,ManageRoleTypeEnum.COLLEGES_ADMIN,ManageRoleTypeEnum.COMPANY_ADMIN));
		return "/system/permission/permission_index";
	}
	/**
	 * 
	 * @Description: 分页列表
	 * @Author：hujing
	 * @Date：2015年4月29日 下午1:38:49
	 * @return
	 * @throws
	 */
//	@Action(value = "showRole",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/showRole",method={RequestMethod.GET,RequestMethod.POST})
	public void showRole(HttpServletRequest request,Writer writer,HttpServletResponse response){
		dealJspParams(request);
		page=manageRoleService.getByType(page,type);
		try {
			writer.write(objToJsonData("page", page));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description: 添加角色
	 * @Author：hujing
	 * @Date：2015年4月29日 下午1:39:07
	 * @return
	 * @throws
	 */
@SuppressWarnings("static-access")
	//	@Action(value = "addRole",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/addRole",method={RequestMethod.GET,RequestMethod.POST})
	public void addRole(HttpServletRequest request, HttpServletResponse response){
		dealJspParams(request);
		ManageRole manageRole = new ManageRole();
		manageRole.setType(type);
		manageRole.setName(name);
		manageRole.setCreateUser(getLoginUserId(request));
		manageRole.setUpdateUser(getLoginUserId(request));
		manageRoleService.save(manageRole);
		
		response.setStatus(response.SC_OK);
	}
	
	/**
	 * 
	 * @Description: 删除角色
	 * @Author：hujing
	 * @Date：2015年4月30日 下午2:40:45
	 * @return
	 * @throws
	 */
//	@Action(value = "deleteRole",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/deleteRole",method={RequestMethod.GET,RequestMethod.POST})
	public void deleteRole(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		int count = manageAccountRoleService.getByRoleId(id);
		if(count == 0){
			manageAccountRoleService.delete(id);
			manageRoleService.delete(id);
			this.code="200";
		}else{
			this.code="500";
		}
		try {
			writer.write(objToJsonData("code", code));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description: 跟新角色
	 * @Author：hujing
	 * @Date：2015年4月30日 下午2:40:45
	 * @return
	 * @throws
	 */
//	@Action(value = "updateRole",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/updateRole",method={RequestMethod.GET,RequestMethod.POST})
	public void updateRole(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		ManageRole manageRole = new ManageRole();
		manageRole.setId(id);
		manageRole.setType(type);
		manageRole.setName(name);
		manageRole.setUpdateUser(getLoginUserId(request));
		manageRoleService.update(manageRole);
	}
	

	/**
	 * 
	 * @Description: 验证名字
	 * @Author：hujing
	 * @Date：2015年4月30日 下午2:40:45
	 * @return
	 * @throws
	 */
//	@Action(value = "verifyName",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/verifyName",method={RequestMethod.GET,RequestMethod.POST})
	public void verifyName(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		ManageRole manageRole = manageRoleService.findByNameOrType(name.trim(), type);
		if(manageRole != null) {
			this.code = "200";
		}else{
			this.code = "500";
		}
		try {
			writer.write(objToJsonData("code", code));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description:通过id获取信息
	 * @Author：hujing
	 * @Date：2015年4月30日 下午2:40:45
	 * @return
	 * @throws
	 */
//	@Action(value = "findById",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/findById",method={RequestMethod.GET,RequestMethod.POST})
	public void findById(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		manageRole = manageRoleService.getById(id);
		if(manageRole != null){
			this.code = "200";
		}else{
			this.code = "500";
		}
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("code", code);
			dataMap.put("manageRole", manageRole);
			
			writer.write(binder.toJson(dataMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description: 权限分配
	 * @Author：hujing
	 * @Date：2015年4月28日 下午1:59:34
	 * @return
	 * @throws
	 */
//	@Action(value = "distribute", results = { @Result(name = SUCCESS, location = "/web/pages/system/permission/permission_distr.jsp") })
	@RequestMapping(value="/distribute",method={RequestMethod.GET,RequestMethod.POST})
	public String distribute(HttpServletRequest request) {
		dealJspParams(request);
		//单角色时使用
		request.setAttribute("resourceData", manageResourceService.getAllResourceByRoleId(id));
		return "/system/permission/permission_distr";
	}

	/**
	 * 
	 * @Description: 进入权限资源页面
	 * @Author：hujing
	 * @Date：2015年4月28日 下午1:59:34
	 * @return
	 * @throws
	 */
//	@Action(value = "permissionResource", results = { @Result(name = SUCCESS, location = "/web/pages/system/permission_resource/permission_resource_index.jsp") })
	@RequestMapping(value="/permissionResource",method={RequestMethod.GET,RequestMethod.POST})
	public String permissionResource(HttpServletRequest request) {
		//获取所有权限资源
		request.setAttribute("data", manageResourceService.getAllPermissionResource());
		return "/system/permission_resource/permission_resource_index";
	}
	
	/**
	 * 
	 * @Description: 设置页面页数
	 * @param pageNo
	 * @throws
	 */
	public void setPageNo(String pageNo) {
		// 获取分页参数
		int currentPage;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNumeric(pageNo)) {
			currentPage = Integer.valueOf(pageNo).intValue();
		} else {
			currentPage = 1;
		}
		page.setCurrentPage(currentPage);
	}

	/**
	 * 简单处理页面传递过来的参数
	 * @param request
	 */
	private void dealJspParams(HttpServletRequest request) {
		
		type = convertInteger(request.getParameter("type"));
		name = convertStr(request.getParameter("name"));
		id = convertInteger(request.getParameter("id"));
		code = convertStr(request.getParameter("code"));
		
		String pageNo = request.getParameter("pageNo");
		if (!StringUtil.isEmpty(pageNo)) {
			setPageNo(convertStr(request.getParameter("pageNo")));
		}
	}
}
