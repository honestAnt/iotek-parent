package com.iotekclass.cms.service.permission;

import com.iotekclass.cms.model.permission.ManageAccountRole;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: ManageAccountRoleService   
 * @Description：  账号角色关系业务逻辑接口
 * @Author：hujing
 * @Date：2015年4月27日 下午3:11:27
 * @version
 */
public interface ManageAccountRoleService extends BaseService<ManageAccountRole>{
	int getByRoleId(int roleId);
}
