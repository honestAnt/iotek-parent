package com.iotekclass.cms.persist.banner;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.banner.HomeBanner;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: HomeBannerMapper   
 * @Description： 首页广告mapper
 * @Author：王凤宝
 * @Date：2015年7月29日 下午1:27:18
 * @version
 */

@MyBatisRepository
public interface HomeBannerMapper extends BaseMapper<HomeBanner> {
	
	/**
	 * 
	 * @Description: 通过状态获取广告位数据
	 * @Author：王凤宝
	 * @Date：2015年7月29日 下午1:49:40
	 * @param enabled(0:所有，1：不可用，2：可用)
	 * @return
	 * @throws
	 */
	List<HomeBanner> getAllByEnable(@Param(MAPPER_TABLE_NAME) String tableName,@Param("enabled") int enabled);
	
	/**
	 * 
	 * @Description: 把sortNumber后的数据排位后移
	 * @Author：王凤宝
	 * @Date：2015年7月29日 下午1:54:14
	 * @param sortNumber
	 * @return
	 * @throws
	 */
	int updateSortNumber(@Param(MAPPER_TABLE_NAME) String tableName,@Param("sortNumber") int sortNumber);
	
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
	List<HomeBanner> getPageByParams(@Param(MAPPER_TABLE_NAME) String tableName,@Param("params") Map<String, Object> params,RowBounds rowBounds);
	
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
	int countPageByParams(@Param(MAPPER_TABLE_NAME) String tableName,@Param("params") Map<String, Object> params);
	
	/**
	 * 
	 * @Description: 找出sortNumber后的数据
	 * @Author：王凤宝
	 * @Date：2015年8月3日 下午4:12:01
	 * @param tableName
	 * @param sortNumber
	 * @return
	 * @throws
	 */
	List<HomeBanner> getBySortNumber(@Param(MAPPER_TABLE_NAME) String tableName,@Param("sortNumber") int sortNumber);
	
	/**
	 * 
	 * @Description: 批量更新sortNumber
	 * @Author：王凤宝
	 * @Date：2015年8月3日 下午4:13:27
	 * @param tableName
	 * @param sortNumber
	 * @param list
	 * @return
	 * @throws
	 */
	int updateBatchSortNumber(@Param(MAPPER_TABLE_NAME) String tableName,@Param("sortNumber") int sortNumber,@Param("list") List<HomeBanner> list);
}
