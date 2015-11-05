package com.iotekclass.cms.service.homepage;

import java.util.Map;

import com.iotekclass.cms.model.homepage.FriendlyLink;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: FriendlyLinkService   
 * @Description： 友情链接业务逻辑接口   
 * @Author：hujing
 * @Date：2015年7月9日 下午1:33:33
 * @version
 */
public interface FriendlyLinkService extends BaseService<FriendlyLink>{

	/**
	 * 
	 * @Description:根据条件查询友情链接信息
	 * @Author：hujing
	 * @Date：2015年7月9日 下午2:28:01
	 * @param params
	 * @param page
	 * @return
	 * @throws
	 */
	Page<FriendlyLink> getByParams(Map<String, Object> params,
			Page<FriendlyLink> page);

	/**
	 * 
	 * @Description: 通过名字获取友情链接信息
	 * @Author：hujing
	 * @Date：2015年7月9日 下午5:40:52
	 * @param name
	 * @return
	 * @throws
	 */
	FriendlyLink getByName(String name);
	
	
	
}
