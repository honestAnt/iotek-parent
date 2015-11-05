package com.iotekclass.cms.web.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.permission.OperationResource;
import com.iotekclass.cms.service.permission.OperationResourceService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.OperationResourceTypeEnum;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;

/**
 * @ClassName OperationResourceAjaxAction.java
 * @Description 
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年6月19日 下午3:26:40
 * @Version 1.0
 */
@Controller
@RequestMapping(value="/systemManage")
public class OperationResourceAjaxAction extends BaseController {

	@Reference(version = "1.0.0")
	private OperationResourceService operationResourceService;
	
	/**
	 *分页组件 
	 */
	private Page<OperationResource> page = new Page<OperationResource>(PAGE_SIZE);

	/**
	 * 每页多少条数据
	 */
	private static final int PAGE_SIZE = 10;
	
	/**
	 * 资源id
	 */
	private int resourceId;
	
	/**
	 *  url
	 */
	private String url;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 描述
	 */
	private String module;
	
	/**
	 * 详情
	 */
	private String detail;
	
	/**
	 * 判断
	 */
	private boolean flag;
	
	/**
	 * 数量
	 */
	private int amount;

	/**
	 * 操作资源VO
	 */
	private OperationResource oResource;
	
	
	/**
	 * @Description: 操作资源录入
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午4:00:31
	 * @return 
	 * @throws
	 */
//	@Action(value = "operationResourceIndex", results = { @Result(name = SUCCESS, location = "/web/pages/system/permission/operation_resource.jsp") })
	@RequestMapping(value="/operationResourceIndex",method={RequestMethod.GET,RequestMethod.POST})
	public String operationResourceIndex(HttpServletRequest request) {
	
		request.setAttribute("operationResourceTypeEnum", EnumSet.of(OperationResourceTypeEnum.ADD,
				OperationResourceTypeEnum.REMOVE, OperationResourceTypeEnum.MODIFY,
				OperationResourceTypeEnum.QUERY,OperationResourceTypeEnum.AUTHORIZATION,
				OperationResourceTypeEnum.IMPORT_UPLOAD));
		return "/system/permission/operation_resource";
	}
	
	
	/**
	 * @Description: 校验输入的ＵＲＬ的唯一性
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月23日 上午9:50:58
	 * @return 
	 * @throws
	 */
//	@Action(value = "validUniquenessUrl", results = { @Result(name = SUCCESS,type = "json") })
	@RequestMapping(value="/validUniquenessUrl",method={RequestMethod.GET,RequestMethod.POST})
	public void validUniquenessUrl(HttpServletRequest request,Writer writer) {
		dealJspParams(request);
		flag=false;
		OperationResource oResource = operationResourceService.queryResourceByUrl(url);
		if(oResource == null){
			flag=true;
		}
		try {
			writer.write(objToJsonData("flag", flag));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Description: 分页查询操作资源-->操作资源
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月4日 下午2:39:12
	 * @return 
	 * @throws
	 */
//	@Action(value = "searchResourcesList",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/searchResourcesList",method={RequestMethod.GET,RequestMethod.POST})
	public void searchResourcesList(HttpServletRequest request,Writer writer) {
		dealJspParams(request);
		//分页
		page = operationResourceService.searchResourcesList(page);
		try {
			writer.write(objToJsonData("page", page));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 根据资源ID查询操作资源信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月24日 上午11:47:01
	 * @return 
	 * @throws
	 */
//	@Action(value = "searchResourceCondition",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/searchResourceCondition",method={RequestMethod.GET,RequestMethod.POST})
	public void searchResourceCondition(HttpServletRequest request,Writer writer) {
		dealJspParams(request);
		oResource =  operationResourceService.getById(resourceId);
		try {
			writer.write(objToJsonData("oResource", oResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Description: 新增操作资源-->操作资源
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月4日 下午2:39:12
	 * @return 
	 * @throws
	 */
	
//	@Action(value = "operationResourceOperate",results={@Result(name=SUCCESS,type = "json")})
	@RequestMapping(value="/operationResourceOperate",method={RequestMethod.GET,RequestMethod.POST})
	public void addOperationResource(HttpServletRequest request,Writer writer) {
		dealJspParams(request);
		OperationResource oResource = new OperationResource();
		oResource.setUrl(url);
		oResource.setType(type);
		oResource.setModule(module);
		oResource.setDetail(detail);
		if(flag){
			oResource.setId(resourceId);
			amount = operationResourceService.update(oResource);
		}else{
			amount = operationResourceService.save(oResource);
		}
		try {
			writer.write(objToJsonData("amount", amount));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		resourceId = convertInteger(request.getParameter("resourceId"));
		url = convertStr(request.getParameter("url"));
		type = convertInteger(request.getParameter("type"));
		module = convertStr(request.getParameter("module"));
		detail = convertStr(request.getParameter("detail"));
		flag = Boolean.parseBoolean(request.getParameter("flag"));
		amount = convertInteger(request.getParameter("amount"));
		
		String pageNo = request.getParameter("pageNo");
		if (!StringUtil.isEmpty(pageNo)) {
			setPageNo(convertStr(request.getParameter("pageNo")));
		}
	}
	
}
