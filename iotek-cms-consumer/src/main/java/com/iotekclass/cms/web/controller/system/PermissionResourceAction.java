package com.iotekclass.cms.web.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.permission.ManageResource;
import com.iotekclass.cms.service.permission.ManageResourceService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description 后台权限资源action 
 * @author wangfengbao
 * @date 2015年6月19日 下午5:52:28
 *
 */
@Controller
@RequestMapping(value="/systemManage/permission")
public class PermissionResourceAction extends BaseController {

	/**
	 * 序列号
	 */
	@Reference(version = "1.0.0")
	ManageResourceService manageResourceService;
	
	/**
	 * 资源
	 */
	private String url;
	
	/**
	 * 校验信息
	 */
	private boolean valid;
	
	/**
	 * 父id
	 */
	private int parentId;
	
	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源id
	 */
	private String resourceId;
	
	/**
	 * 查看权限路径
	 */
	private String url1;
	/**
	 * 添加权限路径
	 */
	private String url2;
	/**
	 * 删除权限路径
	 */
	private String url3;
	/**
	 * 修改权限路径
	 */
	private String url4;
	/**
	 * 查询权限路径
	 */
	private String url5;
	/**
	 * 上传权限路径
	 */
	private String url6;
	/**
	 * 导入权限路径
	 */
	private String url7;
	/**
	 * 导出权限路径
	 */
	private String url8;
	/**
	 * 下载权限路径
	 */
	private String url9;
	
	/**
	 *查看权限id
	 */
	private String urlId1;
	/**
	 * 添加权限id
	 */
	private String urlId2;
	/**
	 * 删除权限id
	 */
	private String urlId3;
	/**
	 * 修改权限id
	 */
	private String urlId4;
	/**
	 * 查询权限id
	 */
	private String urlId5;
	/**
	 * 上传权限id
	 */
	private String urlId6;
	/**
	 * 导入权限id
	 */
	private String urlId7;
	/**
	 * 导出权限id
	 */
	private String urlId8;
	/**
	 * 下载权限id
	 */
	private String urlId9;

	/**
	 * 菜单资源
	 */
	private List<ManageResource> firstResources;
	
	/**
	 * 
	 * @description 保存角色权限资源信息 
	 * @author wangfengbao
	 * @date 2015年5月4日 下午3:12:19
	 *
	 * @return
	 */
//	@Action(value = "savePermissionResource",results={@Result(name=SUCCESS,type="redirect",location="/systemManage/permissionResource")})
	@RequestMapping(value="/savePermissionResource",method={RequestMethod.GET,RequestMethod.POST})
	public String savePermissionResource(HttpServletRequest request){
		dealJspParams(request);
		if (StringUtil.isEmpty(request.getParameter("name"))) {
			return "redirect:/systemManage/permissionResource";
		}
		manageResourceService.saveManageResourceInfo(dealParams(request));
		return "redirect:/systemManage/permissionResource";
	}

	/**
	 * 
	 * @description 更新权限信息 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午5:55:36
	 *
	 * @return
	 */
//	@Action(value = "updatePermissionResource",results={@Result(name=SUCCESS,type="redirect",location="/systemManage/permissionResource")})
	@RequestMapping(value="/updatePermissionResource",method={RequestMethod.GET,RequestMethod.POST})
	public String updatePermissionResource(HttpServletRequest request){
		dealJspParams(request);
		if (StringUtil.isEmpty(name) && state.equals("-1")) {
			return "redirect:/systemManage/permissionResource";
		}
		manageResourceService.updateManageResourceInfo(dealParams(request));
		return "redirect:/systemManage/permissionResource";
	}

	/**
	 * 
	 * @description 更新权限信息 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午5:55:36
	 *
	 * @return
	 */
//	@Action(value = "updatePermissionState",results={@Result(name=SUCCESS,type="json")})
	@RequestMapping(value="/updatePermissionState",method={RequestMethod.GET,RequestMethod.POST})
	public void updatePermissionState(HttpServletRequest request){
		dealJspParams(request);
		manageResourceService.updateManageResourceInfo(dealParams(request));
	}
	
	/**
	 * 
	 * @description 简单封装页面参数 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午5:57:14
	 *
	 * @return
	 */
	private Map<String, String> dealParams(HttpServletRequest request) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("resourceId", resourceId+"");
		params.put("state", state+"");
		params.put("name", name);
		params.put("userId", getLoginUserId(request)+"");
		params.put("parentId", parentId+"");
		for (int i = 1; i <= 9; i++) {
			String urlStr = "";
			String urlIdStr = "";
			
			switch (i) {
			case 1:
				urlStr = url1;
				urlIdStr = urlId1;
				break;
			case 2:
				urlStr = url2;
				urlIdStr = urlId2;
				break;
			case 3:
				urlStr = url3;
				urlIdStr = urlId3;
				break;
			case 4:
				urlStr = url4;
				urlIdStr = urlId4;
				break;
			case 5:
				urlStr = url5;
				urlIdStr = urlId5;
				break;
			case 6:
				urlStr = url6;
				urlIdStr = urlId6;
				break;
			case 7:
				urlStr = url7;
				urlIdStr = urlId7;
				break;
			case 8:
				urlStr = url8;
				urlIdStr = urlId8;
				break;
			case 9:
				urlStr = url9;
				urlIdStr = urlId9;
				break;
			default:
				urlStr = url1;
				urlIdStr = urlId1;
				break;
			}
			if (urlStr == null) {
				urlStr = "";
				urlIdStr = "";
			}
			params.put("urlId"+i, urlIdStr);
			params.put("url"+i, urlStr.trim());
		}
		
		return params;
	}
	
	/**
	 * 
	 * @description 删除资源数据 
	 * @author wangfengbao
	 * @date 2015年6月23日 下午5:19:43
	 *
	 * @return
	 */
@SuppressWarnings("static-access")
	//	@Action(value = "delResource",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/delResource",method={RequestMethod.GET,RequestMethod.POST})
	public void delResource(HttpServletRequest request,HttpServletResponse response){
		dealJspParams(request);
		manageResourceService.deleteByResourceId(Integer.parseInt(resourceId), parentId);
		response.setStatus(response.SC_OK);
	}
	
	/**
	 * 
	 * @description 校验url资源是否被使用
	 * @author wangfengbao
	 * @date 2015年6月19日 下午6:00:01
	 *
	 * @return
	 */
//	@Action(value = "validResourceUse",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/validResourceUse",method={RequestMethod.GET,RequestMethod.POST})
	public void validResourceUse(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		//获取校验信息
		valid = manageResourceService.validResourceUsed(Integer.parseInt(resourceId), parentId) > 0?false:true;
		try {
			writer.write(objToJsonData("valid", valid));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 校验url资源是否存在
	 * @author wangfengbao
	 * @date 2015年6月19日 下午6:00:01
	 *
	 * @return
	 */
//	@Action(value = "validResourceUrl",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/validResourceUrl",method={RequestMethod.GET,RequestMethod.POST})
	public void validResourceUrl(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		//获取校验信息
		List<ManageResource> list = manageResourceService.getByUrl(url.trim());
		valid = (list != null && list.size() > 0) ? false:true;
		try {
			writer.write(objToJsonData("valid", valid));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 获取当前资源下子菜单资源 (默认二级菜单)
	 * @author wangfengbao
	 * @date 2015年6月23日 上午9:52:23
	 *
	 * @return
	 */
//	@Action(value = "dealResource",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/dealResource",method={RequestMethod.GET,RequestMethod.POST})
	public void dealResource(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		//默认获取所有一级资源信息
		firstResources = manageResourceService.getAllByParentId(parentId);
		try {
			writer.write(objToJsonData("firstResources", firstResources));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 校验资源名称是否存在
	 * @author wangfengbao
	 * @date 2015年6月19日 下午5:59:05
	 *
	 * @return
	 */
//	@Action(value = "validResourceName",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/validResourceName",method={RequestMethod.GET,RequestMethod.POST})
	public void validResourceName(HttpServletRequest request,Writer writer){
		dealJspParams(request);
		//获取校验信息
		valid = manageResourceService.getByName(name.trim()) != null ? false:true;
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
		url = convertStr(request.getParameter("url"));
		valid = Boolean.getBoolean(convertStr(request.getParameter("valid")));
		parentId = convertInteger(request.getParameter("parentId"));
		state = convertStr(request.getParameter("state"));
		name = convertStr(request.getParameter("name"));
		resourceId = convertStr(request.getParameter("resourceId"));
		for (int i = 1; i <= 9; i++) {
			String urlStr = convertStr(request.getParameter("url"+i));
			String urlIdStr = convertStr(request.getParameter("urlId"+i));
			switch (i) {
			case 1:
				url1 = urlStr;
				urlId1 = urlIdStr;
				break;
			case 2:
				url2 = urlStr;
				urlId2 = urlIdStr;
				break;
			case 3:
				url3 = urlStr;
				urlId3 = urlIdStr;
				break;
			case 4:
				url4 = urlStr;
				urlId4 = urlIdStr;
				break;
			case 5:
				url5 = urlStr;
				urlId5 = urlIdStr;
				break;
			case 6:
				url6 = urlStr;
				urlId6 = urlIdStr;
				break;
			case 7:
				url7 = urlStr;
				urlId7 = urlIdStr;
				break;
			case 8:
				url8 = urlStr;
				urlId8 = urlIdStr;
				break;
			case 9:
				url9 = urlStr;
				urlId9 = urlIdStr;
				break;
			default:
				url1 = urlStr;
				urlId1 = urlIdStr;
				break;
			}
		}
	}
}
