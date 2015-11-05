/**
 * 
 */
package com.iotekclass.cms.persist.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.iotekclass.cms.model.permission.OperationResource;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * @ClassName: OperationResourceMapper   
 * @Description： TODO(这里用一句话描述这个类的作用)   
 * @Author：GuangChen
 * @Date：2015年5月15日 上午10:27:49
 * @version
 */
@MyBatisRepository
public interface OperationResourceMapper extends BaseMapper<OperationResource> {

	/** 
	 * @Description: 根据URL查询操作资源(带类型条件进行查询)
	 * @Author：GuangChen
	 * @Date：2015年5月15日 上午11:14:54
	 * @param url
	 * @return
	 * @throws 
	*/ 
	OperationResource getByUrl(@Param(MAPPER_TABLE_NAME)String tableName,@Param("url")String url);
	
	
	/**
	 * @Description: 根据URL查询操作资源(不带类型条件进行查询)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月23日 上午9:46:48
	 * @param url
	 * @return 
	 * @throws
	 */
	OperationResource queryResourceByUrl(@Param(MAPPER_TABLE_NAME)String tableName, @Param("url") String url);
	
	/**
	 * @Description: 查询符合条件的操作资源总数量(分页用)(后台管理-系统管理-操作资源)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午3:36:19
	 * @return 
	 * @throws
	 */
	int searchResourcesListCount(@Param(MAPPER_TABLE_NAME) String tableName);
	
	/**
	 * @Description: 分页查询操作资源列表数据 (后台管理-系统管理-操作资源)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午3:29:36
	 * @param paramsMap
	 * @param page
	 * @return 
	 * @throws
	 */
	List<OperationResource> searchResourcesList(@Param(MAPPER_TABLE_NAME) String tableName, RowBounds rowBounds);

}
