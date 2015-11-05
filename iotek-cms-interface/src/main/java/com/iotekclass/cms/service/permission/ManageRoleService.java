package com.iotekclass.cms.service.permission;

import java.util.List;

import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: ManageRoleService   
 * @Description： 后台角色业务逻辑类   
 * @Author：hujing
 * @Date：2015年4月27日 下午3:00:18
 * @version
 */
public interface ManageRoleService extends BaseService<ManageRole>{
	/**
	 * 
	 * @Description: 通过账号id获取对应角色信息
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:04:20
	 * @param accountId
	 * @return
	 * @throws
	 */
	List<ManageRole> getByAccountId(int accountId);
	/**
	 * 
	 * @Description: 分类查找
	 * @Author：hujing
	 * @Date：2015年4月28日 下午2:47:39
	 * @param type
	 * @return
	 * @throws
	 */
	Page<ManageRole> getByType(Page<ManageRole> page,int type);
	
	List<ManageRole> getByType(int type);
	
	ManageRole findByNameOrType(String name,int type);
}
