package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageRoleResourceRel;
import com.iotekclass.cms.persist.permission.ManageRoleResourceRelMapper;
import com.iotekclass.cms.service.permission.ManageAccountLogService;
import com.iotekclass.cms.service.permission.ManageAccountRoleService;
import com.iotekclass.cms.service.permission.ManageRoleResourceRelService;
import com.iotekclass.common.constants.enums.ManageAccountLogTypeEnum;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: ManageRoleServiceImpl   
 * @Description： 后台角色业务逻辑实现  
 * @Author：hujing
 * @Date：2015年4月27日 下午3:01:41
 * @version
 */
//@Service("manageRoleResourceRelService")
@Service(version = "1.0.0")
public class ManageRoleResourceRelServiceImpl extends BaseServiceImpl<ManageRoleResourceRel> implements ManageRoleResourceRelService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296647654678035231L;
	@Autowired
	ManageRoleResourceRelMapper manageRoleResourceRelMapper;

	@Autowired
	ManageAccountRoleService manageAccountRoleService;
	
	@Autowired
	ManageAccountLogService manageAccountLogService;
	
	public ManageRoleResourceRelServiceImpl() {

	}

	@Autowired
	public ManageRoleResourceRelServiceImpl(ManageRoleResourceRelMapper manageRoleResourceRelMapper) {
		super(manageRoleResourceRelMapper, ManageRoleResourceRel.tableName);
	}

	/**
	 * 
	 * @Description: 根据角色或资源id获得权限
	 * @param roleId
	 * @param resourceId
	 * @return
	 * @throws
	 */
	@Override
	public List<ManageRoleResourceRel> getByRoleIdOrResourceId(int roleId,
			int resourceId) {
		return manageRoleResourceRelMapper.getByRoleIdOrResourceId(ManageRoleResourceRel.tableName, roleId, resourceId);
	}

	/** 
	 * @Description: 取角色对应的相同资源(URL)中第一个权限信息
	 * @param role
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<ManageRoleResourceRel> getFirstByRole(int role) {
		
		return manageRoleResourceRelMapper.getFirstByRole(ManageRoleResourceRel.tableName, role);
	}

	/** 
	 * @Description: 保存角色权限资源数据
	 * @param roleId
	 * @param resourceIds
	 * @param createUser
	 * @return
	 * @throws 
	*/ 
	@Override
	public int saveRoleResource(int roleId, String resourceIds, int createUser) {
		
		//删除权限资源数据
		deleteRoleResourceByRole(roleId);
		
		//构建新的权限资源数据
		List<String> ids = Arrays.asList(resourceIds.split(","));

		//权限资源实体
		List<ManageRoleResourceRel> roleResourceRels = new ArrayList<ManageRoleResourceRel>();
		for (String str : ids) {
			ManageRoleResourceRel manageRoleResourceRel = new ManageRoleResourceRel();
			manageRoleResourceRel.setCreateTime(new Date());
			manageRoleResourceRel.setCreateUser(createUser);
			manageRoleResourceRel.setPermission("");
			manageRoleResourceRel.setResourceId(Integer.parseInt(str));
			manageRoleResourceRel.setRoleId(roleId);
			
			roleResourceRels.add(manageRoleResourceRel);
		}
		
		//批量添加角色权限资源
		manageRoleResourceRelMapper.saveBatch(ManageRoleResourceRel.tableName, roleResourceRels);
		
		//维护日志信息
		manageAccountLogService.saveInfo(roleId, createUser, ManageAccountLogTypeEnum.UPDATE_PERMISSION);
		return 0; 
	}

	/** 
	 * @Description: 删除角色权限资源数据
	 * @param roleId
	 * @return
	 * @throws 
	*/ 
	@Override
	public int deleteRoleResourceByRole(int roleId) {
		
		return manageRoleResourceRelMapper.deleteRoleResourceByRole(ManageRoleResourceRel.tableName, roleId);
	}

	/** 
	 * @Description: 删除角色某一权限资源数据
	 * @param roleId
	 * @param resourceId
	 * @return
	 * @throws 
	*/ 
	@Override
	public int deleteRoleResourceByRoleAndResource(int roleId, int resourceId) {
		return manageRoleResourceRelMapper.deleteRoleResourceByRoleAndResource(ManageRoleResourceRel.tableName, roleId, resourceId);
	}




}
