package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.permission.ManageRoleResourceRel;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: ManageRoleResourceRelMapper   
 * @Description：角色资源关系Mapper
 * @Author：hujing
 * @Date：2015年4月27日 下午2:29:46
 * @version
 */

@MyBatisRepository
public interface ManageRoleResourceRelMapper extends BaseMapper<ManageRoleResourceRel>{
	//通过角色id或资源id查找角色资源关系信息
	List<ManageRoleResourceRel> getByRoleIdOrResourceId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("roleId") int roleId,@Param("resourceId") int resourceId);
	
	/**
	 * 
	 * @description 取角色对应的相同资源(URL)中第一个权限信息 
	 * @author wangfengbao
	 * @date 2015年4月29日 下午1:42:50
	 *
	 * @param tableName
	 * @param roleId
	 * @return
	 */
	List<ManageRoleResourceRel> getFirstByRole(@Param(MAPPER_TABLE_NAME) String tableName, @Param("roleId") int roleId);
	
	/**
	 * 
	 * @description 删除角色权限数据
	 * @author wangfengbao
	 * @date 2015年5月4日 下午3:26:55
	 *
	 * @param tableName
	 * @param roleId
	 * @return
	 */
	int deleteRoleResourceByRole(@Param(MAPPER_TABLE_NAME) String tableName, @Param("roleId") int roleId);
	
	/**
	 * 
	 * @description 删除角色某一权限数据
	 * @author wangfengbao
	 * @date 2015年6月24日 上午8:58:57
	 *
	 * @param tableName
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	int deleteRoleResourceByRoleAndResource(@Param(MAPPER_TABLE_NAME) String tableName, @Param("roleId") int roleId,
			@Param("resourceId") int resourceId);
}
