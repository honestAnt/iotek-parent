package com.iotekclass.cms.persist.homepage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.homepage.RecommendPosition;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;
/**
 * 
 * @ClassName: RecommendPositionMapper   
 * @Description： 推荐位持久化操作   
 * @Author：hujing
 * @Date：2015年7月15日 下午1:42:06
 * @version
 */
@MyBatisRepository
public interface RecommendPositionMapper extends BaseMapper<RecommendPosition> {

	/**
	 * 
	 * @Description:获取所有推荐位数量
	 * @Author：hujing
	 * @Date：2015年7月15日 下午1:43:47
	 * @param tableName
	 * @return
	 * @throws
	 */
	int getAllCount(@Param(MAPPER_TABLE_NAME) String tableName);

	/**
	 * 
	 * @Description: 分页获取所有推荐位信息
	 * @Author：hujing
	 * @Date：2015年7月15日 下午1:44:08
	 * @param tableName
	 * @param rowBounds
	 * @return
	 * @throws
	 */
	List<RecommendPosition> getAllPage(@Param(MAPPER_TABLE_NAME) String tableName,RowBounds rowBounds);

	/**
	 * 
	 * @Description:通过名称获取推荐位信息
	 * @Author：hujing
	 * @Date：2015年7月15日 下午1:44:26
	 * @param tableName
	 * @param name
	 * @return
	 * @throws
	 */
	RecommendPosition getByName(@Param(MAPPER_TABLE_NAME) String tableName,@Param("name") String name);
	
}