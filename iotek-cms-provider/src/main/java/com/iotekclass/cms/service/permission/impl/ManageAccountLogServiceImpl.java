package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageAccountLog;
import com.iotekclass.cms.persist.permission.ManageAccountLogMapper;
import com.iotekclass.cms.service.permission.ManageAccountLogService;
import com.iotekclass.common.constants.enums.ManageAccountLogTypeEnum;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: ManageAccountLogServiceImpl   
 * @Description： 后台账户日志业务逻辑实现类  
 * @Author：hujing
 * @Date：2015年4月27日 下午3:42:26
 * @version
 */
//@Service("manageAccountLogService")
@Service(version = "1.0.0")
public class ManageAccountLogServiceImpl extends BaseServiceImpl<ManageAccountLog> implements ManageAccountLogService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3858096282208523836L;
	@Autowired
	ManageAccountLogMapper manageAccountLogMapper;

	public ManageAccountLogServiceImpl() {

	}

	@Autowired
	public ManageAccountLogServiceImpl(ManageAccountLogMapper manageAccountLogMapper) {
		super(manageAccountLogMapper, ManageAccountLog.tableName);
	}

	/**
	 * 
	 * @Description: 通过账号id查找对应日志信息
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:25:50
	 * @param accountId
	 * @return
	 * @throws
	 */
	@Override
	public List<ManageAccountLog> getByAccountId(int accountId) {
		return manageAccountLogMapper.getByAccountId(ManageAccountLog.tableName,accountId);
	}

	/** 
	 * @Description: 账号数据操作日志
	 * @param accountId
	 * @param userId
	 * @param accountLogTypeEnum
	 * @throws 
	*/ 
	@Override
	public void saveInfo(int accountId, int userId, ManageAccountLogTypeEnum accountLogTypeEnum) {
		ManageAccountLog manageAccountLog = new ManageAccountLog();
		manageAccountLog.setAccountId(accountId);
		manageAccountLog.setCreateTime(new Date());
		manageAccountLog.setCreateUser(userId);
		manageAccountLog.setDescription(accountLogTypeEnum.getName());
		manageAccountLog.setUpdateType(accountLogTypeEnum.getValue());
		save(manageAccountLog);
	}


}
