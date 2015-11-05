package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageAccountRole;
import com.iotekclass.cms.persist.permission.ManageAccountRoleMapper;
import com.iotekclass.cms.service.permission.ManageAccountRoleService;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @ClassName: ManageAccountRoleServiceImpl   
 * @Description： 账号角色业务逻辑实现类 
 * @Author：hujing
 * @Date：2015年4月27日 下午3:14:47
 * @version
 */
//@Service("manageAccountRoleService")
@Service(version = "1.0.0")
public class ManageAccountRoleServiceImpl extends BaseServiceImpl<ManageAccountRole> implements ManageAccountRoleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3977676148409443972L;
	@Autowired
	ManageAccountRoleMapper manageAccountRoleMapper;

	public ManageAccountRoleServiceImpl() {

	}

	@Autowired
	public ManageAccountRoleServiceImpl(ManageAccountRoleMapper manageAccountRoleMapper) {
		super(manageAccountRoleMapper, ManageAccountRole.tableName);
	}

	@Override
	public int getByRoleId(int roleId) {
		return manageAccountRoleMapper.getByRoleId(ManageAccountRole.tableName, roleId);
	}


}
