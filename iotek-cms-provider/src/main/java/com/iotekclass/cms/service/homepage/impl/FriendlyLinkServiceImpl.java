package com.iotekclass.cms.service.homepage.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.homepage.FriendlyLink;
import com.iotekclass.cms.persist.homepage.FriendlyLinkMapper;
import com.iotekclass.cms.service.homepage.FriendlyLinkService;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: FriendlyLinkServiceImpl   
 * @Description： 友情链接业务逻辑实现类
 *改注解使用的是dubbo提供的，不是spring的。interfaceName不写会默认。
 * @Author：hujing
 * @Date：2015年7月9日 下午1:35:19
 * @version
 */
//@Service("friendlyLinkService")
@Service(version = "1.0.0")
public class FriendlyLinkServiceImpl extends BaseServiceImpl<FriendlyLink> implements FriendlyLinkService{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2173834024186001891L;
	@Autowired
	FriendlyLinkMapper friendlyLinkMapper;

	public FriendlyLinkServiceImpl() {

	}
	@Autowired
	public FriendlyLinkServiceImpl(FriendlyLinkMapper friendlyLinkMapper) {
		super(friendlyLinkMapper, FriendlyLink.tableName);
	}

	/**
	 * 
	 * @Description: 根据条件查询友情链接信息
	 * @param params
	 * @param page
	 * @return
	 * @throws
	 */
	@Override
	public Page<FriendlyLink> getByParams(Map<String, Object> params,
			Page<FriendlyLink> page) {
		String name = (String)params.get("name");
		if(!StringUtil.isEmpty(name)){
			params.put("name","%"+name+"%");
		}
		params.put("tableName",FriendlyLink.tableName);
		// 1.设置总条数
		page.setDataCount(friendlyLinkMapper.getCountByParams(params));

		// 2.设置rowRound参数(模块写法)
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数

		// 3.通过mapper查询数据
		List<FriendlyLink> friendlyLinkList = friendlyLinkMapper.getByParams(params,rowBounds);
		
		// 4.在page对象中设置数据
		page.setResult(friendlyLinkList);
		return page;
	}
	
	/**
	 * 
	 * @Description: 通过名字获取友情链接信息
	 * @param name
	 * @return
	 * @throws
	 */
	@Override
	public FriendlyLink getByName(String name) {
		return friendlyLinkMapper.getByName(FriendlyLink.tableName,name);
	}



}
