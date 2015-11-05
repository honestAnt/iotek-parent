package com.iotekclass.cms.persist.information;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.information.Information;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: InformationMapper   
 * @Description： 资讯接口   
 * @Author：袁亚明
 * @Date：2015年7月14日 下午5:14:31
 * @version
 */

@MyBatisRepository
public interface InformationMapper extends BaseMapper<Information> {
	/**
	 * 
	 * @Description: 保存资讯
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:25:45
	 * @param tableName
	 * @param information
	 * @throws
	 */
	void saveInformation(@Param(MAPPER_TABLE_NAME) String tableName,@Param("information") Information information);
	
	/**
	 * 
	 * @Description: 根据条件查询资讯列表
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:26:00
	 * @param tableName
	 * @param firstLevelId
	 * @param second_level_id
	 * @param status
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @param rowBounds
	 * @return
	 * @throws
	 */
	public List<Information> informationSearch(@Param(MAPPER_TABLE_NAME) String tableName,@Param("firstLevelId") Integer firstLevelId,
			@Param("secondLevelId") Integer second_level_id, @Param("status") Integer status, @Param("title") String title, 
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,RowBounds rowBounds);
	
	/**
	 * 
	 * @Description: 根据条件查询资讯列表数量
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:26:24
	 * @param tableName
	 * @param firstLevelId
	 * @param second_level_id
	 * @param status
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws
	 */
	public int getInformationListCount(@Param(MAPPER_TABLE_NAME) String tableName,@Param("firstLevelId") Integer firstLevelId,
			@Param("secondLevelId") Integer second_level_id, @Param("status") Integer status, @Param("title") String title, 
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime);
	
	/**
	 * 
	 * @Description: 修改资讯状态
	 * @Author：袁亚明
	 * @Date：2015年7月21日 下午4:26:51
	 * @param tableName
	 * @param id
	 * @param status
	 * @param returnReason
	 * @param userId
	 * @throws
	 */
	public  void updateByStatus(@Param(MAPPER_TABLE_NAME) String tableName,@Param("id") int id,@Param("status") int status
							,@Param("returnReason") String returnReason,@Param("userId") int userId);
	
	/**
	 * 
	 * @Description: 获取发布最新的两条资讯
	 * @Author：hujing
	 * @Date：2015年7月16日 下午1:54:39
	 * @param tableName
	 * @return
	 * @throws
	 */
	public List<Information> getFirstInfo(@Param(MAPPER_TABLE_NAME) String tableName ,@Param("status") int status
			,@Param("featured") int featured ,@Param("number") int number);
	
	
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
	List<Information> informationBySort(@Param(MAPPER_TABLE_NAME) String tableName,@Param("firstLevelId") Integer firstLevelId,
			@Param("secondLevelId") Integer second_level_id,@Param("status") int status);
}
