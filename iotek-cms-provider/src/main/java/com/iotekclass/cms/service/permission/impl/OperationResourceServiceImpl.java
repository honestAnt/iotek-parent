/**
 * 
 */
package com.iotekclass.cms.service.permission.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.OperationResource;
import com.iotekclass.cms.persist.permission.OperationResourceMapper;
import com.iotekclass.cms.service.permission.OperationResourceService;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: OperationResourceServiceImpl   
 * @Description： TODO(这里用一句话描述这个类的作用)   
 * @Author：GuangChen
 * @Date：2015年5月15日 上午10:21:46
 * @version
 */
//@Service("operationResourceService")
@Service(version = "1.0.0")
public class OperationResourceServiceImpl extends BaseServiceImpl<OperationResource> implements
OperationResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8978228469642453110L;
	
	@Autowired
	OperationResourceMapper operationResourceMapper;
	
	public OperationResourceServiceImpl(){
		
	}
	
	@Autowired
	public OperationResourceServiceImpl(OperationResourceMapper operationResourceMapper){
		super(operationResourceMapper,OperationResource.tableName);
	}

	/** 
	 * @Description: TODO
	 * @param url
	 * @return
	 * @throws 
	*/ 
	@Override
	public OperationResource getByUrl(String url) {
		
		return operationResourceMapper.getByUrl(OperationResource.tableName,url);
	}
	
	/**
	 * @Description: 根据URL查询资源(不带类型条件进行查询)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月23日 上午9:46:48
	 * @param url
	 * @return 
	 * @throws
	 */
	@Override
	public OperationResource queryResourceByUrl(String url) {
		return operationResourceMapper.queryResourceByUrl(OperationResource.tableName, url);
	}
	
	
	/**
	 * @Description: 查询符合条件的操作资源总数量(分页用)(后台管理-系统管理-操作资源)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午3:36:19
	 * @return 
	 * @throws
	 */
	@Override
	public int searchResourcesListCount() {
		return operationResourceMapper.searchResourcesListCount(OperationResource.tableName);
	}

	/**
	 * @Description: 分页查询操作资源列表数据 (后台管理-系统管理-操作资源)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月19日 下午3:29:36
	 * @param paramsMap
	 * @param page
	 * @return 
	 * @throws
	 */
	@Override
	public Page<OperationResource> searchResourcesList(
			Page<OperationResource> page) {
		// 1.设置总条数
		page.setDataCount(searchResourcesListCount());
		// 2.设置rowRound参数
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数
		// 3.通过mapper查询数据
		List<OperationResource> resourceList = operationResourceMapper.searchResourcesList(OperationResource.tableName,rowBounds);
		// 4.在page对象中设置数据
		page.setResult(resourceList);
		
		return page;
	}

}
