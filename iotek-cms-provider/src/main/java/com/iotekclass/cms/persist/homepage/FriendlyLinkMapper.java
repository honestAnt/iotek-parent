package com.iotekclass.cms.persist.homepage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.homepage.FriendlyLink;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;
/**
 * 
 * @ClassName: FriendlyLinkMapper   
 * @Description： 友情链接持久化接口   
 * @Author：hujing
 * @Date：2015年7月9日 上午11:00:25
 * @version
 */
@MyBatisRepository
public interface FriendlyLinkMapper extends BaseMapper<FriendlyLink> {

	/**
	 * 
	 * @Description: 获取指定条件友情链接数量
	 * @Author：hujing
	 * @Date：2015年7月9日 下午2:33:02
	 * @param params
	 * @return
	 * @throws
	 */
	int getCountByParams(@Param("params") Map<String, Object> params);

	/**
	 * 
	 * @Description: 根据条件获取友情链接信息
	 * @Author：hujing
	 * @Date：2015年7月9日 下午2:33:29
	 * @param params
	 * @param rowBounds
	 * @return
	 * @throws
	 */
	List<FriendlyLink> getByParams(@Param("params") Map<String, Object> params,
			RowBounds rowBounds);

	/**
	 * 
	 * @Description: 通过名称获取友情链接信息
	 * @Author：hujing
	 * @Date：2015年7月9日 下午5:42:49
	 * @param tableName
	 * @param name
	 * @return
	 * @throws
	 */
	FriendlyLink getByName(@Param(MAPPER_TABLE_NAME) String tableName,@Param("name") String name);
	
}