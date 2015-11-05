package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.permission.ManageAccountLog;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: ManageAccountLog   
 * @Description： 后台账号操作日志Mapper
 * @Author：hujing
 * @Date：2015年4月27日 下午1:22:44
 * @version
 */

@MyBatisRepository
public interface ManageAccountLogMapper extends BaseMapper<ManageAccountLog>{
	//根据账户id获取对应账户日志信息
	List<ManageAccountLog> getByAccountId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("accountId") int accountId);
}
