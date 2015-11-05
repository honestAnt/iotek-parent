package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageResource;
import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.cms.model.permission.ManageRoleResourceRel;
import com.iotekclass.cms.persist.permission.ManageResourceMapper;
import com.iotekclass.cms.service.permission.ManageResourceService;
import com.iotekclass.cms.service.permission.ManageRoleResourceRelService;
import com.iotekclass.cms.service.permission.ManageRoleService;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 
 * @ClassName: ManageResourceServiceImpl   
 * @Description： 后台资源业务逻辑实现类 
 * @Author：hujing
 * @Date：2015年4月27日 下午3:17:46
 * @version
 */
//@Service("manageResourceService")
@Service(version = "1.0.0")
public class ManageResourceServiceImpl extends BaseServiceImpl<ManageResource> implements ManageResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5305127043047633220L;
	
	/**
	 * 管理员角色
	 */
	private static final int ADMIN_ROLE = 1;
	
	@Autowired
	ManageResourceMapper manageResourceMapper;

	@Autowired
	ManageRoleResourceRelService manageRoleResourceRelService; 
	
	@Autowired
	ManageRoleService manageRoleService;
	
	public ManageResourceServiceImpl() {

	}

	@Autowired
	public ManageResourceServiceImpl(ManageResourceMapper manageResourceMapper) {
		super(manageResourceMapper, ManageResource.tableName);
	}

	/**
	 * 
	 * @Description: 获取对应角色资源信息
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:18:58
	 * @param roleId
	 * @return
	 * @throws
	 */
	@Override
	public List<ManageResource> getByRoleId(int roleId) {
		return manageResourceMapper.getByRoleId(ManageResource.tableName, roleId);
	}

	/** 
	 * @Description: 通过父资源查找所有可用子资源 
	 * @param parentId
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<ManageResource> getByParentId(int parentId) {
		
		return manageResourceMapper.getByParentId(ManageResource.tableName, parentId);
	}

	/** 
	 * @Description: 获取所有的用户权限资源信息(返回树形结构)
	 * @param accountId
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<Map<String, Object>> getAllResourceByAccountId(int accountId) {
		
		return getResource(accountId, 0);
	}

	/** 
	 * @Description: 获取某个角色的权限资源信息(返回树形结构)
	 * @param roleId
	 * @return
	 * @throws 
	*/ 
	@Override
	public Map<String, Object> getAllResourceByRoleId(int roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getResource(0, roleId));
		ManageRole manageRole = manageRoleService.getById(roleId);
		map.put("role", manageRole);
		return map;
	}

	/**
	 * 
	 * @description 获取资源信息 
	 * @author wangfengbao
	 * @date 2015年5月4日 上午10:15:28
	 *
	 * @param accountId
	 * @param roleId
	 * @return
	 */
	private List<Map<String, Object>> getResource(int accountId, int roleId) {
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//获取第一层目录
		List<ManageResource> firstResources = getByParentId(0);
		
		//获取角色对应资源
		List<Integer> userResources = new ArrayList<Integer>();

		if (accountId > 0) {
			//获取用户角色
			List<ManageRole> manageRoles = manageRoleService.getByAccountId(accountId);
			
			for (ManageRole manageRole: manageRoles) {
				List<ManageRoleResourceRel> resources = manageRoleResourceRelService.getByRoleIdOrResourceId(manageRole.getId(), 0);
				for (ManageRoleResourceRel manageRoleResourceRel : resources) {
					userResources.add(manageRoleResourceRel.getResourceId());
				}
			}
		} else if (roleId > 0) {
			List<ManageRoleResourceRel> resources = manageRoleResourceRelService.getByRoleIdOrResourceId(roleId, 0);
			for (ManageRoleResourceRel manageRoleResourceRel : resources) {
				userResources.add(manageRoleResourceRel.getResourceId());
			}
		}
		//不显示系统管理权限列表
		
		/**
		 * 获取第二层结构
		 */
		for (ManageResource manageResource: firstResources) {
			Map<String, Object> firstMap = new HashMap<String, Object>();
			firstMap.put("root", manageResource);
			
			List<Map<String, Object>> mapList2 = new ArrayList<Map<String,Object>>();
			//获取第二层目录
			List<ManageResource> secondResources = getByParentId(manageResource.getId());
			if (secondResources != null) {
				for (ManageResource manageResource2: secondResources) {
					Map<String, Object> secondMap = new HashMap<String, Object>();
					
					secondMap.put("root", manageResource2);
					//判读一级菜单是否直接跟权限
					if (manageResource2.getName().contains("0x00")) {
						//添加是否有二级菜单的标识
						firstMap.put("isSecond", "true");
					} else {
						//存放权限资源信息
						List<Map<String, Object>> mapliList3 = new ArrayList<Map<String,Object>>();
						List<ManageResource> thirdResources = getByParentId(manageResource2.getId());
						// 获取叶子节点的权限信息
						for (ManageResource manageResource3 : thirdResources) {
							Map<String,  Object> thirdMap = new HashMap<String, Object>();
							thirdMap.put("permission", userResources.contains(manageResource3.getId()));
							thirdMap.put("root", manageResource3);
							mapliList3.add(thirdMap);
						}
						secondMap.put("leaf", mapliList3);
					}
					mapList2.add(secondMap);
				}
			}
			
			firstMap.put("leaf", mapList2);
			mapList.add(firstMap);
		}
		return mapList;
	}

	/** 
	 * @Description: 通过名称获取资源信息
	 * @param name
	 * @return
	 * @throws 
	*/ 
	@Override
	public ManageResource getByName(String name) {
		return manageResourceMapper.getByName(ManageResource.tableName, name.trim());
	}

	/** 
	 * @Description: 通过路径获取资源信息
	 * @param url
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<ManageResource> getByUrl(String url) {
		List<ManageResource> list = new ArrayList<ManageResource>();
			getResourceByUrl(url, list);
		return list;
	}

	/**
	 * 
	 * @description 计算url是否被使用过 
	 * @author wangfengbao
	 * @date 2015年6月24日 上午10:20:28
	 *
	 * @param url
	 * @param list
	 */
	private void getResourceByUrl(String url,List<ManageResource> list) { 
		
		if (list.size() == 0) {
			if (url.contains(",")) {
				for (int i = 0; i < url.split(",").length;i++) {
					if (!StringUtil.isEmpty(url.split(",")[i])) {
						getResourceByUrl(url.split(",")[i],list);
					}
				}
			} else {
				list.addAll(manageResourceMapper.getByUrl(ManageResource.tableName, url.trim(), "%"+url.trim()+",%"));
			}
		}
	}
	
	/** 
	 * @Description: 添加权限资源
	 * @param params
	 * @return
	 * @throws 
	*/ 
	@SuppressWarnings("static-access")
	@Override
	public int saveManageResourceInfo(Map<String, String> params) {
		
		ManageResource manageResource = new ManageResource();
		
		if (!StringUtil.isEmpty(params.get("parentId")) && !params.get("parentId").equals("0")) {
			//添加子级菜单
			manageResource.setCreateTime(new Date());
			manageResource.setCreateUser(Integer.parseInt(params.get("userId")));
			manageResource.setDescription(params.get("name"));
			manageResource.setName(params.get("name"));
			manageResource.setParentId(Integer.parseInt(params.get("parentId")));
			manageResource.setSortNumber(manageResourceMapper.getAllByParentId(manageResource.tableName, Integer.parseInt(params.get("parentId"))).size()+1);
			manageResource.setState(1);
			manageResource.setUrl("");
			save(manageResource);
			
			//添加权限资源
			for (int i = 1; i <= 9; i++) {
				String url = params.get("url"+i).trim();
				//封装权限数据
				ManageResource manageResourcePermission = new ManageResource();
				
				manageResourcePermission.setCreateTime(new Date());
				manageResourcePermission.setCreateUser(Integer.parseInt(params.get("userId")));
				manageResourcePermission.setDescription(params.get("name"));
				manageResourcePermission.setName("0x00"+i);
				manageResourcePermission.setParentId(manageResource.getId());
				manageResourcePermission.setSortNumber(manageResourceMapper.getAllByParentId(manageResource.tableName, Integer.parseInt(params.get("parentId"))).size()+1);
				manageResourcePermission.setState(1);
				manageResourcePermission.setUrl(url);
				save(manageResourcePermission);
				//给管理员角色赋予权限
				ManageRoleResourceRel roleResourceRel = new ManageRoleResourceRel();
				roleResourceRel.setCreateTime(new Date());
				roleResourceRel.setCreateUser(Integer.parseInt(params.get("userId")));
				roleResourceRel.setPermission("");
				roleResourceRel.setResourceId(manageResourcePermission.getId());
				roleResourceRel.setRoleId(1);//默认系统管理员是1
				manageRoleResourceRelService.save(roleResourceRel);
			}
			
		} else {
			//添加父级菜单
			manageResource.setCreateTime(new Date());
			manageResource.setCreateUser(Integer.parseInt(params.get("userId")));
			manageResource.setDescription(params.get("name"));
			manageResource.setName(params.get("name"));
			manageResource.setParentId(0);
			manageResource.setSortNumber(manageResourceMapper.getAllByParentId(manageResource.tableName, 0).size()+1);
			manageResource.setState(1);
			manageResource.setUrl("");
			save(manageResource);
		}
		return 0;
	}

	/** 
	 * @Description: 修改权限资源信息
	 * @param params
	 * @return
	 * @throws 
	*/ 
	@Override
	public int updateManageResourceInfo(Map<String, String> params) {
		
		ManageResource manageResource = new ManageResource();
		//编辑资源id
		int resourceId = Integer.parseInt(params.get("resourceId"));
		manageResource.setId(resourceId);
		if (!StringUtil.isEmpty(params.get("parentId")) && !params.get("parentId").equals("0")) {
			//修改子级菜单信息
			manageResource.setUpdateUser(Integer.parseInt(params.get("userId")));
			manageResource.setDescription(params.get("name"));
			manageResource.setName(params.get("name"));
			manageResource.setParentId(Integer.parseInt(params.get("parentId")));
			manageResource.setState(Integer.parseInt(params.get("state")));
			update(manageResource);
			if (params.get("state").equals("-1")) {
				//修改权限资源
				for (int i = 1; i <= 9; i++) {
					String url = params.get("url"+i).trim();
					int id = Integer.parseInt(params.get("urlId"+i));
					ManageResource manageResourcePermission = new ManageResource();
					//封装权限数据
					manageResourcePermission.setId(id);
					manageResourcePermission.setUpdateUser(Integer.parseInt(params.get("userId")));
					manageResourcePermission.setDescription(params.get("name"));
					manageResourcePermission.setName("0x00"+i);
					manageResourcePermission.setUrl(url);
					manageResourcePermission.setState(Integer.parseInt(params.get("state")));
					update(manageResourcePermission);
				}
			} else {
				//修改权限资源状态
				for (ManageResource permissionResource: getAllByParentId(manageResource.getId())) {
					permissionResource.setUpdateUser(Integer.parseInt(params.get("userId")));
					permissionResource.setState(Integer.parseInt(params.get("state")));
					update(permissionResource);
				}
			}
			
		} else {
			//修改父级菜单信息
			manageResource.setUpdateUser(Integer.parseInt(params.get("userId")));
			manageResource.setDescription(params.get("name"));
			manageResource.setName(params.get("name"));
			manageResource.setParentId(0);
			manageResource.setUrl("");
			
			//更新状态时
			if (Integer.parseInt(params.get("state")) != -1) {
				manageResource.setState(Integer.parseInt(params.get("state")));
				//更新二级菜单状态
				for(ManageResource secondResource: getAllByParentId(manageResource.getId())) {
					secondResource.setUpdateUser(Integer.parseInt(params.get("userId")));
					secondResource.setState(Integer.parseInt(params.get("state")));
					update(secondResource);
					//更新权限资源状态
					for (ManageResource permissionResource: getAllByParentId(secondResource.getId())) {
						permissionResource.setUpdateUser(Integer.parseInt(params.get("userId")));
						permissionResource.setState(Integer.parseInt(params.get("state")));
						update(permissionResource);
					}
				}
			}
			update(manageResource);
		}
		return 0;
	}

	/** 
	 * @Description: 获取所有的权限资源信息(包括不可用的)
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<Map<String, Object>> getAllPermissionResource() {
		
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		
		//获取第一层菜单
		List<ManageResource> firstList = getAllByParentId(0);
		for (ManageResource manageResource : firstList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("root", manageResource);
			//获取第二层菜单信息
			map.put("leaf", getAllByParentId(manageResource.getId()));
			listMaps.add(map);
		}
		
		return listMaps;
	}

	/** 
	 * @Description:获取当前父类所有子类信息（包括不可用） 
	 * @param parentId
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<ManageResource> getAllByParentId(int parentId) {
		return manageResourceMapper.getAllByParentId(ManageResource.tableName, parentId);
	}

	/** 
	 * @Description: 删除权限资源数据
	 * @param resourceId
	 * @param parent
	 * @return
	 * @throws 
	*/ 
	@Override
	public int deleteByResourceId(int resourceId,int parent) {
		//从底层权限资源删起
		if (parent > 0) {
			//找到所有二级菜单的权限资源
			for (ManageResource manageResource: getAllByParentId(resourceId)) {
				//删除管理员的权限
				manageRoleResourceRelService.deleteRoleResourceByRoleAndResource(ADMIN_ROLE, manageResource.getId());
				
				//删除权限资源
				delete(manageResource.getId());
			}
		} else {
			//找到所有二级菜单
			for (ManageResource manageResource: getAllByParentId(resourceId)) {
				deleteByResourceId(manageResource.getId(), resourceId);
			}
		}
		//删除菜单资源
		delete(resourceId);
		return 0;
	}

	/** 
	 * @Description: 校验权限资源数据是否被使用
	 * @param resourceId
	 * @return
	 * @throws 
	*/ 
	@Override
	public int validResourceUsed(int resourceId,int parent) {
		List<Integer> list = new ArrayList<Integer>();
		return validResourceUser(resourceId, parent, list);
	}
	
	/**
	 * 
	 * @description 计算权限资源是否被使用过
	 * @author wangfengbao
	 * @date 2015年6月24日 下午4:23:35
	 *
	 * @param resourceId
	 * @param parent
	 * @param count
	 * @return
	 */
	
	private int validResourceUser(int resourceId,int parent,List<Integer> integers) {
		if (integers.size() == 0) {
			if (parent > 0) {
				//找到所有二级菜单的权限资源
				for (ManageResource manageResource: getAllByParentId(resourceId)) {
					//找到被使用的权限资源数据
					List<ManageRoleResourceRel> list = manageRoleResourceRelService.getByRoleIdOrResourceId(0, manageResource.getId());
					//去除默认绑定的管理员的权限
					for (ManageRoleResourceRel manageRoleResourceRel: list) {
						if (ADMIN_ROLE != manageRoleResourceRel.getRoleId()) {
							integers.add(0);
							return 1;
						}
					}
				}
			} else {
				//找到所有二级菜单
				for (ManageResource manageResource: getAllByParentId(resourceId)) {
					validResourceUser(manageResource.getId(), resourceId,integers);
				}
			}
		}
		return integers.size();
	}
}
