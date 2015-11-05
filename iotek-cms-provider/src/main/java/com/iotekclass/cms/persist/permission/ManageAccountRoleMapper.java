package com.iotekclass.cms.persist.permission;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.permission.ManageAccountRole;
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
public interface ManageAccountRoleMapper extends BaseMapper<ManageAccountRole>{
	int getByRoleId(@Param(MAPPER_TABLE_NAME) String tableName,@Param("roleId") int roleId);
}
