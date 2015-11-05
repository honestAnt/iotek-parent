package com.iotekclass.cms.service.permission;

import java.util.List;

import com.iotekclass.cms.model.permission.ManageAccountLog;
import com.iotekclass.common.constants.enums.ManageAccountLogTypeEnum;
import com.iotekclass.service.BaseService;

/**
 * 
 * @ClassName: ManageAccountLogService   
 * @Description：   后台账号操作日志业务逻辑接口   
 * @Author：hujing
 * @Date：2015年4月27日 下午3:24:02
 * @version
 */
public interface ManageAccountLogService extends BaseService<ManageAccountLog>{
	/**
	 * 
	 * @Description: 通过账号id查找对应日志信息
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:25:50
	 * @param accountId
	 * @return
	 * @throws
	 */
	List<ManageAccountLog> getByAccountId(int accountId);
	
	/**
	 * 
	 * @description 账号数据操作日志
	 * @author wangfengbao
	 * @date 2015年5月4日 下午3:59:50
	 *
	 * @param userId
	 * @param accountLogTypeEnum
	 * @param accountId
	 */
	void saveInfo(int accountId,int userId,ManageAccountLogTypeEnum accountLogTypeEnum);
}
