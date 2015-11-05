/**
 * 
 */
package com.iotekclass.cms.service.permission.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageOperationRecord;
import com.iotekclass.cms.persist.permission.ManageOperationRecordMapper;
import com.iotekclass.cms.service.permission.ManageOperationRecordService;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ManageOperationRecordServiceImpl   
 * @Description： TODO(这里用一句话描述这个类的作用)   
 * @Author：GuangChen
 * @Date：2015年5月15日 上午9:58:45
 * @version
 */
//@Service("manageOperationRecordService")
@Service(version = "1.0.0")
public class ManageOperationRecordServiceImpl extends BaseServiceImpl<ManageOperationRecord>
implements ManageOperationRecordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6724087948030681973L;

	@Autowired
	ManageOperationRecordMapper manageOperationRecordMapper;
	public ManageOperationRecordServiceImpl(){

	}

	@Autowired
	public ManageOperationRecordServiceImpl(ManageOperationRecordMapper manageOperationRecordMapper){
		super(manageOperationRecordMapper,ManageOperationRecord.tableName);
	}

	/**
	 * 
	 * @Description: 后台操作日志分页列表查询
	 * @param params
	 * @param page
	 * @return
	 * @throws
	 */
	@Override
	public Page<ManageOperationRecord> getAllByParamsPage(
			Map<String, Object> params, Page<ManageOperationRecord> page) {
		String username = (String) params.get("username");
		String endTime = (String) params.get("endTime");
		if(!StringUtil.isEmpty(username)){
			params.put("username", "%"+username+"%");
		}
		if(!StringUtil.isEmpty(endTime)){
			params.put("endTime",endTime+" 23:59:59");
		}
		// 1.设置总条数
		page.setDataCount(manageOperationRecordMapper.getCountByParams(params));

		// 2.设置rowRound参数(模块写法)
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数

		// 3.通过mapper查询数据
		List<ManageOperationRecord> manageOperationRecordList = manageOperationRecordMapper.getAllByParamsPage(params, rowBounds);

		// 4.在page对象中设置数据
		page.setResult(manageOperationRecordList);

		// 5.返回Page
		return page;
	}


}
