package com.iotekclass.cms.service.information;

import java.util.List;

import com.iotekclass.cms.model.information.Information;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;

/**
 * 
 * @ClassName: InformationService   
 * @Description： 资讯service接口 
 * @Author：袁亚明
 * @Date：2015年7月21日 下午4:20:50
 * @version
 */
public interface InformationService extends BaseService<Information> {
	
	/**
	 * 
	 * @Description: 保存信息
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:21:23
	 * @param information
	 * @throws
	 */
	void saveInformation(Information information);
	
	/**
	 * 
	 * @Description: 根据条件查询资讯列表
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:28:49
	 * @param firstLevelId
	 * @param secondLevelId
	 * @param status
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return
	 * @throws
	 */
	Page<Information> informationSearch(Integer firstLevelId,Integer secondLevelId,Integer status,String title,String beginTime,String endTime,Page<Information> page);
	
	/**
	 * 
	 * @Description: 修改资讯状态
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:29:07
	 * @param id
	 * @param status
	 * @param returnReason
	 * @param userId
	 * @throws
	 */
	void updateByStatus(int id,int status,String returnReason,int userId);
	
	/**
	 * 
	 * @Description: 获取前两条资讯
	 * @Author：hujing
	 * @Date：2015年7月16日 下午2:01:26
	 * @return
	 * @throws
	 */
	List<Information> getFirstInfo(int status
			, int featured , int number);
	
	/**
	 * @Description: 根据分类查询咨询列表
	 * @Author：Joshua
	 * @Date：2015年7月21日 下午5:48:15
	 * @param tableName
	 * @param firstLevelId
	 * @param second_level_id
	 * @return
	 * @throws
	 */
	List<Information> informationBySort(Integer firstLevelId,
			Integer second_level_id, int status);

}

