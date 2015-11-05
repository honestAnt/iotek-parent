package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.permission.ManageRole;
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
public interface ManageRoleMapper extends BaseMapper<ManageRole>{
	// 通过账号id查找所属角色
	List<ManageRole> getByAccountId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("accountId") int accountId);
	//分类查找角色信息
	List<ManageRole> getByTypePage(@Param(MAPPER_TABLE_NAME) String tableName,@Param("type") int type,RowBounds rowBounds);
	//获得各类别下的角色数
	int getCountByType(@Param(MAPPER_TABLE_NAME) String tableName,@Param("type") int type);
	//获得各分类下所有的角色名称
	List<ManageRole> getByType(@Param(MAPPER_TABLE_NAME) String tableName,@Param("type") int type);
	//通过角色名与类型确认角色是否已经存在
	ManageRole findByNameOrType(@Param(MAPPER_TABLE_NAME) String tableName,@Param("name") String name,@Param("type") int type);
}
