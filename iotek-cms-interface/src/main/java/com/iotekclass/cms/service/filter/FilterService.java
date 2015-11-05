/**
 * 
 */
package com.iotekclass.cms.service.filter;

import java.util.List;

import com.iotekclass.cms.model.filter.UrlFilter;

/**
 * @Description 过滤器服务接口
 * @author wangfengbao
 * @date 2015年4月29日 上午11:21:17
 *
 */
public interface FilterService {
	
	/**
	 * 
	 * @description 查询用户所具有的角色和权限 
	 * @author wangfengbao
	 * @date 2015年4月29日 上午11:24:39
	 *
	 * @param account
	 * @return
	 */
	public List<UrlFilter> getAllFilters(int account);
	
	/**
	 * 
	 * @description 更新过滤器中的角色及权限 
	 * @author wangfengbao
	 * @date 2015年4月29日 上午11:24:17
	 *
	 * @param filters
	 */
	public void updateFilters(List<UrlFilter> filters);
	
	/**
	 * 
	 * @description 更新用户过滤器中的角色及权限 
	 * @author wangfengbao
	 * @date 2015年4月29日 上午11:24:17
	 *
	 * @param filters
	 */
	public void updateUserFilters(int accountId);
	
	/**
	 * 
	 * @description 校验url是否合法 
	 * @author wangfengbao
	 * @date 2015年5月6日 下午3:24:55
	 *
	 * @param AccountId
	 * @param url
	 * @return
	 */
	public boolean validUrl(int AccountId, String url);
	
	/**
	 * 
	 * @description 获取用户所有资源 
	 * @author wangfengbao
	 * @date 2015年5月8日 下午4:30:01
	 *
	 * @param accountId
	 * @return
	 */
	public List<String> getAllList(int accountId);
	
	/**
	 * 
	 * @description 获取角色所有资源 
	 * @author wangfengbao
	 * @date 2015年5月11日 上午9:41:49
	 *
	 * @param role
	 * @return
	 */
	public List<String> getAllListByRole(int role);
}
