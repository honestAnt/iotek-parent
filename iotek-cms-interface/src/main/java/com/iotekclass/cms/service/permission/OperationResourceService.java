/**
 * 
 */
package com.iotekclass.cms.service.permission;

import com.iotekclass.cms.model.permission.OperationResource;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;

/**
 * @ClassName: OperationResourceService   
 * @Description： 操作资源业务逻辑接口   
 * @Author：GuangChen
 * @Date：2015年5月15日 上午10:19:15
 * @version
 */
public interface OperationResourceService extends BaseService<OperationResource> {

	/** 
	 * @Description: 根据URL查询资源(带类型条件进行查询)
	 * @Author：GuangChen
	 * @Date：2015年5月15日 上午11:12:25
	 * @param url
	 * @return
	 * @throws 
	*/ 
	OperationResource getByUrl(String url);
	
	
	/**
	 * @Description: 根据URL查询资源(不带类型条件进行查询)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月23日 上午9:46:48
	 * @param url
	 * @return 
	 * @throws
	 */
	OperationResource queryResourceByUrl(String url);
	
	
	/**
	 * @Description: 查询符合条件的操作资源总数量(分页用)(后台管理-系统管理-操作资源)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午3:36:19
	 * @return 
	 * @throws
	 */
	int searchResourcesListCount();
	
	/**
	 * @Description: 分页查询操作资源列表数据 (后台管理-系统管理-操作资源)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午3:29:36
	 * @param paramsMap
	 * @param page
	 * @return 
	 * @throws
	 */
	Page<OperationResource> searchResourcesList(Page<OperationResource> page);
	
}
