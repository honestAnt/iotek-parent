/**
 * 
 */
package com.iotekclass.cms.service.permission;

import java.util.Map;

import com.iotekclass.cms.model.permission.ManageOperationRecord;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;

/**
 * @ClassName: ManageOperationRecordService   
 * @Description： 后台操作记录业务逻辑  
 * @Author：GuangChen
 * @Date：2015年5月15日 上午9:53:56
 * @version
 */
public interface ManageOperationRecordService extends BaseService<ManageOperationRecord> {
	/**
	 * 
	 * @Description: 后台操作记录分页业务逻辑处理接口
	 * @Author：hujing
	 * @Date：2015年6月19日 下午2:27:46
	 * @param params
	 * @param page
	 * @return
	 * @throws
	 */
	Page<ManageOperationRecord> getAllByParamsPage(Map<String,Object> params,Page<ManageOperationRecord> page);
}
