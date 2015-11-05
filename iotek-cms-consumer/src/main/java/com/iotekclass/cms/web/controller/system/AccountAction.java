package com.iotekclass.cms.web.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.model.permission.ManageAccountCourseRel;
import com.iotekclass.cms.model.permission.ManageAccountRole;
import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.cms.service.company.CompanyService;
import com.iotekclass.cms.service.course.CourseService;
import com.iotekclass.cms.service.permission.ManageAccountCourseRelService;
import com.iotekclass.cms.service.permission.ManageAccountRoleService;
import com.iotekclass.cms.service.permission.ManageAccountService;
import com.iotekclass.cms.service.permission.ManageRoleService;
import com.iotekclass.cms.service.school.SchoolService;
import com.iotekclass.cms.web.controller.BaseController;
import com.iotekclass.common.constants.enums.ManageRoleTypeEnum;
import com.iotekclass.common.util.MD5Util;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.model.company.Company;
import com.iotekclass.model.course.Course;
import com.iotekclass.model.school.School;
import com.iotekclass.persist.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * 
 * @ClassName: AccountAction   
 * @Description： 账号管理action   
 * @Author：hujing
 * @Date：2015年4月28日 下午2:09:35
 * @version
 */
@Controller
@RequestMapping(value="/systemManage")
public class AccountAction extends BaseController {

	@Reference(version = "1.0.0")
	ManageAccountService manageAccountService;
	@Reference(version = "1.0.0")
	ManageRoleService manageRoleService;
	@Reference(version = "1.0.0")
	ManageAccountRoleService manageAccountRoleService;
	@Reference(version = "1.0.0")
	CourseService courseService;
	@Reference(version = "1.0.0")
	ManageAccountCourseRelService manageAccountCourseRelService;
	@Reference(version = "1.0.0")
	SchoolService schoolService;
	@Reference(version = "1.0.0")
	CompanyService companyService;
	
	/**
	 * 显示条数
	 */
	private static final int PAGE_SIZE = 10;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page<ManageAccount> page = new Page(PAGE_SIZE);

	/**
	 * 账号id
	 */
	private int id;
	/**
	 * 账号类型
	 */
	private int type;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 访问标识
	 */
	private String code;
	/**
	 * 角色id
	 */
	private int roleId;

	/**
	 * 课程id字符串
	 */
	private String courseIds;
	private int unitId;
	
	private ManageAccount manageAccount;
	
	/**
	 * 
	 * @Description: 账号列表页qq
	 * @Author：hujing
	 * @Date：2015年4月28日 下午1:59:34
	 * @return
	 * @throws
	 */
//	//  @Action(value = "accountInfo", results = { @Result(name = SUCCESS, location = "/web/pages/system/account/account_info.jsp") })
	@RequestMapping(value="/accountInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountInfo(HttpServletRequest request) {
		
		dealJspParams(request);
		request.setAttribute("manageRoleTypeEnum",EnumSet.of(ManageRoleTypeEnum.IOTEK_ADMIN,ManageRoleTypeEnum.COLLEGES_ADMIN,ManageRoleTypeEnum.COMPANY_ADMIN));
		List<ManageRole> iotekRoleList =manageRoleService.getByType(ManageRoleTypeEnum.IOTEK_ADMIN.getValue());
		List<ManageRole> collegeRoleList =manageRoleService.getByType(ManageRoleTypeEnum.COLLEGES_ADMIN.getValue());
		List<ManageRole> companyRoleList =manageRoleService.getByType(ManageRoleTypeEnum.COMPANY_ADMIN.getValue());
		List<Course> courseList = courseService.getAll();
		List<School> schoolList = schoolService.getAll();
		List<Company> companyList = companyService.getAll();
		
		request.setAttribute("iotekRoleList", iotekRoleList);
		request.setAttribute("collegeRoleList", collegeRoleList);
		request.setAttribute("companyRoleList", companyRoleList);
		request.setAttribute("courseList", courseList);
		request.setAttribute("schoolList", schoolList);
		request.setAttribute("companyList", companyList);
		return "/system/account/account_info";
	}

	/**
	 * 
	 * @Description: 用户信息列表
	 * @Author：hujing
	 * @Date：2015年4月29日 下午2:32:49
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/showAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public void showAccount(HttpServletRequest request,Writer writer){
		
		dealJspParams(request);
		page=manageAccountService.getByType(page,type);
		try {
			writer.write(objToJsonData("page", page));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		request.setAttribute("page", page);
	}

	/**
	 * 
	 * @Description: 验证该用户名是否重复
	 * @Author：hujing
	 * @Date：2015年4月30日 下午4:55:49
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/findByUsername", method = { RequestMethod.GET, RequestMethod.POST })
	public void findByUsername(Writer writer,HttpServletRequest request){
		
		dealJspParams(request);
		manageAccount = manageAccountService.getByNameAndPwd(username.trim(),null);
		if(manageAccount != null){
			code="200";
		}else{
			code = "500";
		}
		try {
			writer.write(objToJsonData("code", code));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 查找该账户是否存在
	 * @Author：hujing
	 * @Date：2015年4月30日 下午4:55:08
	 * @return
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/findAccountById", method = { RequestMethod.GET, RequestMethod.POST })
	public void findAccountById(Writer writer, HttpServletRequest request,HttpServletResponse response){
		dealJspParams(request);
		manageAccount = manageAccountService.getById(id);
		if(manageAccount != null){
			response.setStatus(response.SC_OK);
			List<ManageRole> manageRoles = manageRoleService.getByAccountId(manageAccount.getId());
			if(manageRoles != null && manageRoles.size() >0){
				roleId = manageRoles.get(0).getId();
			}
			List<ManageAccountCourseRel> manageAccountCourseRels =manageAccountCourseRelService.getByAccountId(manageAccount.getId());
			courseIds="";
			for (ManageAccountCourseRel manageAccountCourseRel : manageAccountCourseRels) {
				courseIds += manageAccountCourseRel.getCourseId()+",";
			}
			this.code="200";
		}else{
			this.code = "500";
		}
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("code", code);
			dataMap.put("manageAccount", manageAccount);
			dataMap.put("roleId", roleId);
			dataMap.put("courseIds", courseIds);
			
			writer.write(binder.toJson(dataMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 * @Description: 删除账号
	 * @Author：hujing
	 * @Date：2015年4月30日 下午5:03:53
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/deleteAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public void deleteAccount(Writer out, HttpServletRequest request) throws IOException{
		dealJspParams(request);
		ManageAccount manageAccount = manageAccountService.getById(id);
		manageAccount.setId(id);
		manageAccount.setEnabled(0);
		int updateNum = manageAccountService.update(manageAccount);
		out.write(binder.toJson(updateNum));
	}

	/**
	 * 
	 * @Description: 添加账号
	 * @Author：hujing
	 * @Date：2015年5月4日 下午2:11:34
	 * @return
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/toAddAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public void toAddAccount(HttpServletRequest request,HttpServletResponse response){
		dealJspParams(request);
		int accountId;
		ManageAccount manageAccount;
		ManageAccountRole manageAccountRole;
		switch (type) {
		case 1:
			manageAccount = addAccount(request);
			manageAccountService.save(manageAccount);
			accountId = manageAccount.getId();
			manageAccountRole = addAccountRoleRel(accountId,request);
			manageAccountRoleService.save(manageAccountRole);
			break;
		case 2:
		case 3:
			manageAccount = addAccount(request);
			manageAccountService.save(manageAccount);
			accountId = manageAccount.getId();
			manageAccountRole = addAccountRoleRel(accountId,request);
			manageAccountRoleService.save(manageAccountRole);
			addAccountCourseRel(accountId);
			break;
		default:
			break;
		}
		response.setStatus(response.SC_OK);
	}

	/**
	 * 
	 * @Description: 更新网校用户信息
	 * @Author：hujing
	 * @Date：2015年5月4日 下午3:27:21
	 * @return
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/toUpdateAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public void toUpdateAccount(HttpServletRequest request, HttpServletResponse response){
		dealJspParams(request);
		ManageAccount manageAccount = manageAccountService.getById(id);
		ManageAccountRole manageAccountRole;
		switch (manageAccount.getType()) {
		//网校管理员用户修改
		case 1:
			switch (type) {
			case 1:
				manageAccount = addAccount(request);
				manageAccount.setId(id);
				manageAccountService.update(manageAccount);
				manageAccountRole = addAccountRoleRel(id,request);
				List<ManageRole> manageRoles = manageRoleService.getByAccountId(id);
				if(manageRoles == null || manageRoles.size() == 0){
					manageAccountRoleService.save(manageAccountRole);
				}else{
					manageAccountRoleService.update(manageAccountRole);
				}
				break;
			case 2:
			case 3:
				manageAccount = addAccount(request);
				manageAccount.setId(id);
				manageAccountService.update(manageAccount);
				manageAccountRole = addAccountRoleRel(id,request);
				manageAccountRoleService.update(manageAccountRole);
				addAccountCourseRel(id);
				break;
			default:
				break;
			}
			break;
		//非网校管理员用户
		case 2:
		case 3:
			switch (type) {
			case 1:
				manageAccount = addAccount(request);
				manageAccount.setId(id);
				manageAccount.setUnitId(-1);
				manageAccountService.update(manageAccount);
				manageAccountRole = addAccountRoleRel(id,request);
				manageAccountRoleService.update(manageAccountRole);
				manageAccountCourseRelService.deleteByAccountId(id);
				break;
			case 2:
			case 3:
				manageAccount = addAccount(request);
				manageAccount.setId(id);
				manageAccountService.update(manageAccount);
				manageAccountRole = addAccountRoleRel(id,request);
				manageAccountRoleService.update(manageAccountRole);
				manageAccountCourseRelService.deleteByAccountId(id);
				addAccountCourseRel(id);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		response.setStatus(response.SC_OK);
	}

	/**
	 * 
	 * @Description: 添加账号
	 * @Author：hujing
	 * @Date：2015年4月29日 下午6:38:19
	 * @return
	 * @throws
	 */
	public ManageAccount addAccount(HttpServletRequest request){
		ManageAccount manageAccount = new ManageAccount();
		manageAccount.setUnitId(unitId);
		manageAccount.setUsername(username);
		if (!"******".equals(password)) {
			manageAccount.setPassword(MD5Util.md5(password));
		} else {
			password = "";
		}
		manageAccount.setComment(comment);
		manageAccount.setRealName(realName);
		manageAccount.setCreateUser(getLoginUserId(request));
		manageAccount.setType(type);
		manageAccount.setUpdateUser(getLoginUserId(request));
		manageAccount.setEnabled(1);
		//manageAccountService.save(manageAccount);
		return manageAccount;
	}
	/**
	 * 
	 * @Description: 添加账号角色关系
	 * @Author：hujing
	 * @Date：2015年4月29日 下午6:38:31
	 * @param accountId
	 * @return
	 * @throws
	 */
	public ManageAccountRole addAccountRoleRel(int accountId,HttpServletRequest request){
		ManageAccountRole manageAccountRole = new ManageAccountRole();
		manageAccountRole.setAccountId(accountId);
		manageAccountRole.setRoleId(roleId);
		manageAccountRole.setCreateUser(getLoginUserId(request));
		manageAccountRole.setUpdateUser(getLoginUserId(request));
		//manageAccountRoleService.save(manageAccountRole);
		return manageAccountRole;
	};

	/**
	 * 
	 * @Description: 添加用户课程关系
	 * @Author：hujing
	 * @Date：2015年4月29日 下午6:47:12
	 * @param accountId
	 * @throws
	 */
	public void addAccountCourseRel(int accountId){
		List<Integer> couserIds = courseIds(courseIds);
		for (int i=0;i< couserIds.size(); i++) {
			ManageAccountCourseRel manageAccountCourseRel = new ManageAccountCourseRel();
			manageAccountCourseRel.setAccountId(accountId);
			manageAccountCourseRel.setCourseId(couserIds.get(i));
			manageAccountCourseRel.setSortNumber(i+1);
			manageAccountCourseRelService.save(manageAccountCourseRel);
		}
	}
	/**
	 * 
	 * @Description: 解析ids
	 * @param ids
	 * @return
	 * @throws
	 */
	public static List<Integer> courseIds(String ids) {
		String[] split = ids.split(",");
		List<Integer> idsParse = new ArrayList<Integer>();
		if (ids.length() > 0) {
			for (String element : split) {
				idsParse.add(Integer.parseInt(element));
			}
			return idsParse;
		}
		return null;
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
		id = convertInteger(request.getParameter("id"));
		if (!StringUtil.isEmpty(request.getParameter("type"))) {
			type = convertInteger(request.getParameter("type"));
		} else {
			type = 1;
		}
		comment = convertStr(request.getParameter("comment"));
		realName = convertStr(request.getParameter("realName"));
		code = convertStr(request.getParameter("code"));
		
		roleId = convertInteger(request.getParameter("roleId"));
		courseIds = convertStr(request.getParameter("courseIds"));
		unitId = convertInteger(request.getParameter("unitId"));
		username = convertStr(request.getParameter("username"));
		password = convertStr(request.getParameter("password"));
		String pageNo = request.getParameter("pageNo");
		if (!StringUtil.isEmpty(pageNo)) {
			setPageNo(convertStr(request.getParameter("pageNo")));
		}
	}
}
