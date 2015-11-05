package com.iotekclass.cms.service.permission;

import java.util.List;

import com.iotekclass.cms.model.permission.ManageAccountCourseRel;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: ManageAccountCourseRelService   
 * @Description： 后台渠道教师课程关系业务逻辑接口  
 * @Author：hujing
 * @Date：2015年4月27日 下午3:31:30
 * @version
 */
public interface ManageAccountCourseRelService extends BaseService<ManageAccountCourseRel>{
	//通过账号id查找对应的课程
	List<ManageAccountCourseRel> getByAccountId(int accountId);
	//通过用户id删除相关关系
	void deleteByAccountId(int accountId);
}
