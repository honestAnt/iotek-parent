package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.model.permission.ManageResource;
import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.cms.persist.permission.ManageAccountMapper;
import com.iotekclass.cms.service.permission.ManageAccountService;
import com.iotekclass.cms.service.permission.ManageResourceService;
import com.iotekclass.cms.service.permission.ManageRoleResourceRelService;
import com.iotekclass.cms.service.permission.ManageRoleService;
import com.iotekclass.common.util.MD5Util;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName: GoodsServiceImpl
 * @Description：商品业务实现类
 * @Author：gufeifei
 * @Date：2014年10月15日 下午1:41:09
 * @version
 */
//@Service("manageAccountService")
@Service(version = "1.0.0")
public class ManageAccountServiceImpl extends BaseServiceImpl<ManageAccount> implements ManageAccountService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3043624273389183576L;

	@Autowired
	ManageAccountMapper manageAccountMapper;
	
	@Autowired
	ManageResourceService manageResourceService;
	
	@Autowired
	ManageRoleService manageRoleService;
	
	@Autowired
	ManageRoleResourceRelService manageRoleResourceRelService;

	@Autowired
	public ManageAccountServiceImpl(ManageAccountMapper manageAccountMapper) {
		super(manageAccountMapper, ManageAccount.tableName);
	}

	/** 
	 * @Description: 通过用户名或者密码获取用户信息
	 * @param userName
	 * @param pwd
	 * @return
	 * @throws 
	 */ 
	@Override
	public ManageAccount getByNameAndPwd(String userName, String pwd) {
		if (StringUtil.isEmpty(pwd)) {
			return manageAccountMapper.getByNameOrPwd(ManageAccount.tableName, userName, "");
		}
		return manageAccountMapper.getByNameOrPwd(ManageAccount.tableName, userName, MD5Util.md5(pwd));
	}

	/** 
	 * @Description: 处理后台账号角色资源权限信息
	 * @param userName
	 * @return
	 * @throws 
	*/ 
	@Override
	public SimpleAuthorizationInfo dealAccountRoleAndPermissions(String userName) {
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		//获取账号信息
		ManageAccount manageAccount = getByNameAndPwd(userName, "");
		
		//获取账号的角色
		 List<ManageRole> roles = manageRoleService.getByAccountId(manageAccount.getId());
		
		//获取角色资源信息
		for (int i = 0;roles!= null && i < roles.size(); i++) {
			//把角色信息添加到认证中
			simpleAuthorizationInfo.addRole(roles.get(i).getName());
			//获取角色对应的所有的权限资源
			List<ManageResource> resources = manageResourceService.getByRoleId(roles.get(i).getId());
			
			//获取资源详细信息
			for (ManageResource manageResource: resources) {
				//把权限资源添加到认证中
				if (!StringUtil.isEmpty(manageResource.getUrl())) {
					if (manageResource.getUrl().contains(",")) {
						for (String str: Arrays.asList(manageResource.getUrl().split(","))) {
							StringBuilder sb = new StringBuilder();
							sb.append(str.trim())
							.append(":")
							.append(manageResource.getName());
							simpleAuthorizationInfo.addStringPermission(sb.toString());
						}
					}  else {
						StringBuilder sb = new StringBuilder();
						sb.append(manageResource.getUrl().trim())
						.append(":")
						.append(manageResource.getName());
						simpleAuthorizationInfo.addStringPermission(sb.toString());
					}
				}
			}
		}
		
		return simpleAuthorizationInfo;
	}

	@Override
	public Page<ManageAccount> getByType(Page<ManageAccount> page, int type) {
		// 1.设置总条数
		page.setDataCount(manageAccountMapper.getCountByType(ManageAccount.tableName, type));
		// 2.设置rowRound参数(模块写法)
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数
		// 3.通过mapper查询数据
		List<ManageAccount> manageAccounts = manageAccountMapper.getByTypePage(ManageAccount.tableName,type, rowBounds);
		for (ManageAccount manageAccount : manageAccounts) {
			List<ManageRole> manageRoles = manageRoleService.getByAccountId(manageAccount.getId());
			if(manageRoles != null && manageRoles.size() > 0){
				manageAccount.setRoleName(manageRoles.get(0).getName());
			}else{
				manageAccount.setRoleName("");
			}
		}
		// 4.在page对象中设置数据
		page.setResult(manageAccounts);
		// 5.返回Page
		return page;
	}

	/** 
	 * @Description: TODO
	 * @param courseId
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<ManageAccount> getManageAccountByCourseId(int courseId,int type) {
		// TODO Auto-generated method stub
		return manageAccountMapper.getManageAccountByCourseId(courseId,type);
	}

}
