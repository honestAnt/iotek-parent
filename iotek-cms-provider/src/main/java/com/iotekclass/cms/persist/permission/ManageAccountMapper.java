package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.permission.ManageAccount;
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
public interface ManageAccountMapper extends BaseMapper<ManageAccount>{
	
	/**
	 * 
	 * @description 通过用户名或者密码获取后台用户信息 
	 * @author wangfengbao
	 * @date 2015年4月28日 下午1:34:16
	 *
	 * @param tableName
	 * @param userName
	 * @param pwd
	 * @return
	 */
	ManageAccount getByNameOrPwd(@Param(MAPPER_TABLE_NAME) String tableName, @Param("username") String userName,
			 @Param("pwd") String pwd);
	/**
	 * 
	 * @Description: 按账号不同类型查询
	 * @Author：hujing
	 * @Date：2015年4月29日 下午2:24:03
	 * @param tableName
	 * @param type
	 * @return
	 * @throws
	 */
	List<ManageAccount> getByTypePage(@Param(MAPPER_TABLE_NAME) String tableName, @Param("type") int type,RowBounds rowBounds);
	
	/**
	 * 
	 * @Description: 查找对应类型的数量
	 * @Author：hujing
	 * @Date：2015年4月29日 下午2:30:06
	 * @param tableName
	 * @param type
	 * @return
	 * @throws
	 */
	int getCountByType(@Param(MAPPER_TABLE_NAME) String tableName, @Param("type") int type);
	/**
	 * @param type  
	 * @Description: 通过课程Id查找对应教师账户信息
	 * @Author：GuangChen
	 * @Date：2015年5月5日 下午2:35:45
	 * @param courseId
	 * @return
	 * @throws 
	*/ 
	List<ManageAccount> getManageAccountByCourseId(@Param("courseId") int courseId, @Param("type")int type);
}
