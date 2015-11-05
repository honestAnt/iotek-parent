package com.iotekclass.cms.service.banner;

import java.util.List;
import java.util.Map;

import com.iotekclass.cms.model.banner.HomeBanner;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;

public interface HomeBannerService extends BaseService<HomeBanner> {
	/**
	 * 
	 * @Description: 通过状态获取广告位数据
	 * @Author：王凤宝
	 * @Date：2015年7月29日 下午1:49:40
	 * @param enabled(0:所有，1：不可用，2：可用)
	 * @return
	 * @throws
	 */
	List<HomeBanner> getAllByEnable(int enabled);
	
	/**
	 * 
	 * @Description: 把sortNumber后的数据排位后移
	 * @Author：王凤宝
	 * @Date：2015年7月29日 下午1:54:14
	 * @param sortNumber
	 * @return
	 * @throws
	 */
	int updateSortNumber(int sortNumber);
	
	/**
	 * 
	 * @Description: 获取符合条件的广告位分页数据
	 * @Author：王凤宝
	 * @Date：2015年7月30日 下午3:18:19
	 * @param tableName
	 * @param params
	 * @return
	 * @throws
	 */
	Page<HomeBanner> getPageByParams(Map<String, Object> params,Page<HomeBanner> page);
	
	/**
	 * 
	 * @Description: 统计符合条件的广告位分页数据
	 * @Author：王凤宝
	 * @Date：2015年7月30日 下午3:18:23
	 * @param tableName
	 * @param params
	 * @return
	 * @throws
	 */
	int countPageByParams(Map<String, Object> params);
	
	/**
	 * 
	 * @Description: 找出sortNumber后的数据
	 * @Author：王凤宝
	 * @Date：2015年8月3日 下午4:12:01
	 * @param sortNumber
	 * @return
	 * @throws
	 */
	List<HomeBanner> getBySortNumber(int sortNumber);
	
	/**
	 * 
	 * @Description: 批量更新sortNumber
	 * @Author：王凤宝
	 * @Date：2015年8月3日 下午4:13:27
	 * @param sortNumber
	 * @param list
	 * @return
	 * @throws
	 */
	int updateBatchSortNumber(int sortNumber,List<HomeBanner> list);
}

