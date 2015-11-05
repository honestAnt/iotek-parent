package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.permission.ManageAccountCourseRel;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: ManageAccountCourseRelMapper   
 * @Description： 后台渠道教师课程关系Mapper  
 * @Author：hujing
 * @Date：2015年4月27日 下午1:42:30
 * @version
 */

@MyBatisRepository
public interface ManageAccountCourseRelMapper extends BaseMapper<ManageAccountCourseRel>{
	List<ManageAccountCourseRel> getByAccountId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("accountId") int accountId);
	//通过用户id删除相关联系
	void deleteByAccountId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("accountId") int accountId);
}
