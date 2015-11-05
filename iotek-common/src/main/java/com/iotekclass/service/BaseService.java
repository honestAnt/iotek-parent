package com.iotekclass.service;

import java.util.List;
import java.util.Map;

/**
 * ClassName: BaseService
 * Description：业务操作基础接口，封装常用操作数据的函数
 * Author：唐超
 * Date：2013-8-25 下午12:56:08
 * 
 * @version
 */
public interface BaseService<T> {

	/**
	 * @Description: 根据主键获取实体对象
	 * @param id
	 * @return T
	 * @throws
	 */
	T getById(int id);

	/**
	 * @Description: 查询所有数据
	 * @return List<T>
	 * @throws
	 */
	List<T> getAll();

	/**
	 * @return
	 * @Description: 保存试题对象
	 * @param t
	 * @return
	 * @throws
	 */
	int save(T t);

	/**
	 * @Description: 根据主键删除数据
	 * @param id
	 * @return
	 * @throws
	 */
	int delete(int id);

	/**
	 * @Description: 修改数据对象
	 * @param t
	 * @return
	 * @throws
	 */
	int update(T t);
	
	/**
	 * 
	 * @description 同步用户信息到CRM (新增或修改)
	 * @author wangfengbao
	 * @date 2014年12月15日 上午10:07:13
	 *
	 * @param mapData(用户数据)
	 * @param url (访问资源路径)
	 * @param hostAdd (访问主机地址)
	 * @param port (主机端口)
	 * @param visitType (访问方式http/https)
	 */
	void sendUserDataToCrm(Map<String, String> mapData, String url,
			String hostAdd, int port, String visitType);
	
	T getByParams(Map<String, Object> map);

}
