package com.iotekclass.cms.service.permission;

import java.util.List;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: ManageAccountService   
 * @Description： 后台账户业务逻辑接口 
 * @Author：hujing
 * @Date：2015年4月27日 下午3:30:26
 * @version
 */
public interface ManageAccountService extends BaseService<ManageAccount>{
	
	/**
	 * 
	 * @description 通过用户名或者密码获取用户信息 
	 * @author wangfengbao
	 * @date 2015年4月28日 下午1:37:20
	 *
	 * @param userName
	 * @param pwd
	 * @return
	 */
	ManageAccount getByNameAndPwd(String userName, String pwd);
	
	/**
	 * 
	 * @description 处理后台账号角色权限信息 
	 * @author wangfengbao
	 * @date 2015年4月28日 下午4:20:13
	 *
	 * @param userName
	 * @return
	 */
	SimpleAuthorizationInfo dealAccountRoleAndPermissions(String userName);
	
	/**
	 * 
	 * @Description: 通过类型查找账户信息
	 * @Author：hujing
	 * @Date：2015年4月29日 下午2:28:28
	 * @param page
	 * @param type
	 * @return
	 * @throws
	 */
	Page<ManageAccount> getByType(Page<ManageAccount> page,int type);

	/**
	 * @param type  
	 * @Description: 通过课程Id查询对应教师账户信息
	 * @Author：GuangChen
	 * @Date：2015年5月5日 下午2:31:48
	 * @param courseId
	 * @return
	 * @throws 
	*/ 
	List<ManageAccount> getManageAccountByCourseId(int courseId, int type);
	
}
