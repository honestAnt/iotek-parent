package com.iotekclass.cms.service.homepage.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.homepage.RecommendPosition;
import com.iotekclass.cms.persist.homepage.RecommendPositionMapper;
import com.iotekclass.cms.service.homepage.RecommendPositionService;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 * @ClassName: RecommendPositionServiceImpl   
 * @Description： 推荐位业务逻辑实现类
 * @Author：hujing
 * @Date：2015年7月15日 下午1:51:05
 * @version
 */
//@Service("recommendPositionService")
@Service(version = "1.0.0")
public class RecommendPositionServiceImpl extends BaseServiceImpl<RecommendPosition> implements RecommendPositionService{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3775169925660904224L;
	
	@Autowired
	RecommendPositionMapper recommendPositionMapper;

	public RecommendPositionServiceImpl() {

	}
	@Autowired
	public RecommendPositionServiceImpl(RecommendPositionMapper recommendPositionMapper) {
		super(recommendPositionMapper, RecommendPosition.tableName);
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
	public Page<RecommendPosition> getAllPage(Page<RecommendPosition> page) {
		
		// 1.设置总条数
		page.setDataCount(recommendPositionMapper.getAllCount(RecommendPosition.tableName));

		// 2.设置rowRound参数(模块写法)
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数

		// 3.通过mapper查询数据
		List<RecommendPosition> recommendPositionList = recommendPositionMapper.getAllPage(RecommendPosition.tableName,rowBounds);
		
		// 4.在page对象中设置数据
		page.setResult(recommendPositionList);
		return page;
	}
	
	/**
	 * 
	 * @Description: 通过名字获取推荐位信息
	 * @param name
	 * @return
	 * @throws
	 */
	@Override
	public RecommendPosition getByName(String name) {
		return recommendPositionMapper.getByName(RecommendPosition.tableName,name);
	}



}
