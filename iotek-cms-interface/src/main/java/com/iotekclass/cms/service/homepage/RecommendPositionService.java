package com.iotekclass.cms.service.homepage;

import com.iotekclass.cms.model.homepage.RecommendPosition;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: RecommendPositionService   
 * @Description： 推荐位业务逻辑接口   
 * @Author：hujing
 * @Date：2015年7月15日 下午1:48:55
 * @version
 */
public interface RecommendPositionService extends BaseService<RecommendPosition>{

	/**
	 * 
	 * @Description: 分页获取所有推荐位信息
	 * @Author：hujing
	 * @Date：2015年7月15日 下午1:49:48
	 * @return
	 * @throws
	 */
	Page<RecommendPosition> getAllPage(Page<RecommendPosition> page);

	/**
	 * 
	 * @Description: 根据名字获取推荐位信息
	 * @Author：hujing
	 * @Date：2015年7月15日 下午1:50:04
	 * @param name
	 * @return
	 * @throws
	 */
	RecommendPosition getByName(String name);
	
	
	
}
