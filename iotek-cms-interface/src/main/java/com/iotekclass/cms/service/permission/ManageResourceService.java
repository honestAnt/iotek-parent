package com.iotekclass.cms.service.permission;

import java.util.List;
import java.util.Map;

import com.iotekclass.cms.model.permission.ManageResource;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: ManageResourceService   
 * @Description：后台资源业务逻辑接口
 * @Author：hujing
 * @Date：2015年4月27日 下午3:16:00
 * @version
 */
public interface ManageResourceService extends BaseService<ManageResource>{
	/**
	 * 
	 * @Description: 获取对应角色资源信息
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:18:58
	 * @param roleId
	 * @return
	 * @throws
	 */
	List<ManageResource> getByRoleId(int roleId);
	
	/**
	 * 
	 * @description 通过父资源查找所有可用子资源 
	 * @author wangfengbao
	 * @date 2015年4月30日 下午5:48:54
	 *
	 * @param parentId
	 * @return
	 */
	List<ManageResource> getByParentId(int parentId);
	
	/**
	 * 
	 * @description 获取所有的用户权限资源信息(返回树形结构)
	 * @author wangfengbao
	 * @date 2015年4月30日 下午5:55:44
	 *
	 * @param accountId
	 * @return
	 */
	List<Map<String, Object>> getAllResourceByAccountId(int accountId);
	
	/**
	 * 
	 * @description 获取某个角色的权限资源信息(返回树形结构)
	 * @author wangfengbao
	 * @date 2015年5月4日 上午10:11:56
	 *
	 * @param roleId
	 * @return
	 */
	Map<String, Object> getAllResourceByRoleId(int roleId);
	
	/**
	 * 
	 * @description 通过资源名称获取对应资源信息
	 * @author wangfengbao
	 * @date 2015年6月19日 下午2:02:10
	 *
	 * @param name
	 * @return
	 */
	ManageResource getByName(String name);
	
	/**
	 * 
	 * @description 通过url获取对应资源信息 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午2:03:29
	 *
	 * @param url
	 * @return
	 */
	List<ManageResource> getByUrl(String url);
	
	/**
	 * 
	 * @description 添加权限资源
	 * @author wangfengbao
	 * @date 2015年6月19日 下午2:11:07
	 *
	 * @param params
	 * @return
	 */
	int saveManageResourceInfo(Map<String, String> params);
	
	/**
	 * 
	 * @description 修改权限资源 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午3:16:24
	 *
	 * @param params
	 * @return
	 */
	int updateManageResourceInfo(Map<String, String> params);
	
	/**
	 * 
	 * @description 获取所有权限资源(包括不可用的)信息
	 * @author wangfengbao
	 * @date 2015年6月19日 下午4:23:43
	 *
	 * @return
	 */
	List<Map<String, Object>> getAllPermissionResource();
	
	/**
	 * 
	 * @description 获取当前父类所有子类信息（包括不可用） 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午4:29:25
	 *
	 * @param parentId
	 * @return
	 */
	List<ManageResource> getAllByParentId(int parentId);
	
	/**
	 * 
	 * @description 删除权限资源数据
	 * @author wangfengbao
	 * @date 2015年6月23日 下午5:36:14
	 *
	 * @param resourceId
	 * @param parent
	 * @return
	 */
	int deleteByResourceId(int resourceId,int parent);
	
	/**
	 * 
	 * @description 校验权限资源数据是否被使用
	 * @author wangfengbao
	 * @date 2015年6月23日 下午5:36:14
	 *
	 * @param resourceId
	 * @param parent
	 * @return
	 */
	int validResourceUsed(int resourceId,int parent);
}
