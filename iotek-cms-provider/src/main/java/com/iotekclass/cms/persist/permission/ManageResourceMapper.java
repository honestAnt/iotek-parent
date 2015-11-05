package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.permission.ManageResource;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: ManageAccountMapper   
 * @Description： 后台账号Mapper
 * @Author：hujing
 * @Date：2015年4月27日 下午1:22:44
 * @version
 */

@MyBatisRepository
public interface ManageResourceMapper extends BaseMapper<ManageResource>{
	//通过角色id查找对应资源
	List<ManageResource> getByRoleId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("roleId") int roleId);
	
	/**
	 * 
	 * @description 通过父资源查找所有可用的子资源 
	 * @author wangfengbao
	 * @date 2015年4月30日 下午5:21:10
	 *
	 * @param tableName
	 * @param parentId
	 * @return
	 */
	List<ManageResource> getByParentId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("parentId") int parentId);
	
	/**
	 * 
	 * @description 通过资源名称获取对应资源信息
	 * @author wangfengbao
	 * @date 2015年6月19日 下午2:02:10
	 *
	 * @param tableName
	 * @param name
	 * @return
	 */
	ManageResource getByName(@Param(MAPPER_TABLE_NAME) String tableName, @Param("name") String name);
	
	/**
	 * 
	 * @description 通过url获取对应资源信息 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午2:03:29
	 *
	 * @param tableName
	 * @param url
	 * @param urlStr
	 * @return
	 */
	List<ManageResource> getByUrl(@Param(MAPPER_TABLE_NAME) String tableName, @Param("url") String url, @Param("urlStr") String urlStr);
	
	/**
	 * 
	 * @description 获取当前父类的所有子类资源(包括不可用的) 
	 * @author wangfengbao
	 * @date 2015年6月19日 下午4:26:22
	 *
	 * @param tableName
	 * @param parentId
	 * @return
	 */
	List<ManageResource> getAllByParentId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("parentId") int parentId);
}
