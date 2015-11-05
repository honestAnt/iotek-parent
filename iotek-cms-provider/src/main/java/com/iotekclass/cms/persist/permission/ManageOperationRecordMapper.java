/**
 * 
 */
package com.iotekclass.cms.persist.permission;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.permission.ManageOperationRecord;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * @ClassName: ManageOperationRecordMapper   
 * @Description： TODO(这里用一句话描述这个类的作用)   
 * @Author：GuangChen
 * @Date：2015年5月15日 上午10:05:46
 * @version
 */
@MyBatisRepository
public interface ManageOperationRecordMapper extends BaseMapper<ManageOperationRecord> {
	/**
	 * 
	 * @Description: 分页查询所有后台操作记录
	 * @Author：hujing
	 * @Date：2015年6月19日 下午1:45:37
	 * @param params
	 * @param rowBounds
	 * @return
	 * @throws
	 */
	List<ManageOperationRecord> getAllByParamsPage(@Param(MAPPER_PARAMS) Map<String, Object> params,RowBounds rowBounds);
	/**
	 * 
	 * @Description:获取符合条件的总数
	 * @Author：hujing
	 * @Date：2015年6月19日 下午2:30:05
	 * @param params
	 * @return
	 * @throws
	 */
	int getCountByParams(@Param(MAPPER_PARAMS) Map<String, Object> params);
}
