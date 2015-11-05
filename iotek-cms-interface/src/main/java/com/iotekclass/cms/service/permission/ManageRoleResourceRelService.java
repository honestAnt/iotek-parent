package com.iotekclass.cms.service.permission;

import java.util.List;

import com.iotekclass.cms.model.permission.ManageRoleResourceRel;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: ManageRoleResourceRelService   
 * @Description： 后台角色资源关系业务逻辑接口
 * @Author：hujing
 * @Date：2015年4月27日 下午3:20:20
 * @version
 */
public interface ManageRoleResourceRelService extends BaseService<ManageRoleResourceRel>{
	/**
	 * 
	 * @Description: 根据角色或资源id获得权限
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:40:43
	 * @param roleId
	 * @param resourceId
	 * @return
	 * @throws
	 */
	List<ManageRoleResourceRel> getByRoleIdOrResourceId(int roleId,int resourceId);
	
	/**
	 * 
	 * @description 取角色对应的相同资源(URL)中第一个权限信息
	 * @author wangfengbao
	 * @date 2015年4月29日 下午1:40:36
	 *
	 * @param role
	 * @return
	 */
	List<ManageRoleResourceRel> getFirstByRole(int role);
	
	/**
	 * 
	 * @description 保存角色权限资源数据
	 * @author wangfengbao
	 * @date 2015年5月4日 下午3:18:39
	 *
	 * @param roleId
	 * @param resourceId
	 * @param createUser
	 * @return
	 */
	int saveRoleResource(int roleId,String resourceIds,int createUser);
	
	/**
	 * 
	 * @description 删除角色权限资源数据
	 * @author wangfengbao
	 * @date 2015年5月4日 下午3:24:17
	 *
	 * @param roleId
	 * @return
	 */
	int deleteRoleResourceByRole(int roleId);
	
	/**
	 * 
	 * @description  删除角色某一权限资源数据
	 * @author wangfengbao
	 * @date 2015年6月24日 上午8:59:49
	 *
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	int deleteRoleResourceByRoleAndResource(int roleId,int resourceId);
}
