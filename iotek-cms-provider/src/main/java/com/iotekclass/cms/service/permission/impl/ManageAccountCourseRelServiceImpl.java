package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageAccountCourseRel;
import com.iotekclass.cms.persist.permission.ManageAccountCourseRelMapper;
import com.iotekclass.cms.service.permission.ManageAccountCourseRelService;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 * @ClassName: GoodsServiceImpl
 * @Description：商品业务实现类
 * @Author：gufeifei
 * @Date：2014年10月15日 下午1:41:09
 * @version
 */
//@Service("manageAccountCourseRelService")
@Service(version = "1.0.0")
public class ManageAccountCourseRelServiceImpl extends BaseServiceImpl<ManageAccountCourseRel> implements ManageAccountCourseRelService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999298173800774492L;
	@Autowired
	ManageAccountCourseRelMapper manageAccountCourseRelMapper;

	public ManageAccountCourseRelServiceImpl() {

	}

	@Autowired
	public ManageAccountCourseRelServiceImpl(ManageAccountCourseRelMapper manageAccountCourseRelMapper) {
		super(manageAccountCourseRelMapper, ManageAccountCourseRel.tableName);
	}

	@Override
	public List<ManageAccountCourseRel> getByAccountId(int accountId) {
		return manageAccountCourseRelMapper.getByAccountId(ManageAccountCourseRel.tableName, accountId);
	}

	@Override
	public void deleteByAccountId(int accountId) {
		manageAccountCourseRelMapper.deleteByAccountId(ManageAccountCourseRel.tableName, accountId);
	}


}
