package com.iotekclass.cms.service.banner.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.banner.HomeBanner;
import com.iotekclass.cms.persist.banner.HomeBannerMapper;
import com.iotekclass.cms.service.banner.HomeBannerService;
import com.iotekclass.common.util.Collections3;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

//@Service("homeBannerService")
@Service(version = "1.0.0")
public class HomeBannerServiceImpl extends BaseServiceImpl<HomeBanner> implements HomeBannerService {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8454455749532065078L;
	@Autowired
	HomeBannerMapper homeBannerMapper;
	
	@Autowired 
	public HomeBannerServiceImpl(HomeBannerMapper mapper) {
		super(mapper, HomeBanner.tableName);
	}
	
	@Override
	public List<HomeBanner> getAllByEnable(int enabled) {
		
		return homeBannerMapper.getAllByEnable(HomeBanner.tableName, enabled);
	}

	@Override
	public int updateSortNumber(int sortNumber) {
		return homeBannerMapper.updateSortNumber(HomeBanner.tableName, sortNumber);
	}

	/** 
	 * @Description: 重写父类保存方法
	 * @param t
	 * @return
	 * @throws 
	*/ 
	@Override
	public int save(HomeBanner t) {
		int sortNumber = t.getSortNumber();
		//找出sortNumber后的数据
		List<HomeBanner> list = getBySortNumber(sortNumber);

		if (Collections3.isNotEmpty(list)) {

			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSortNumber(sortNumber + (i + 1));
			}
			// 批量更新sortNumber
			updateBatchSortNumber(sortNumber, list);

		}
		return super.save(t);
	}
	
	/**
	 * 
	 * @Description: 重写父类更新方法
	 * @param t
	 * @return
	 * @throws
	 */
	@Override
	public int update(HomeBanner t) {
		
		int sortNumber = t.getSortNumber();
		//找出sortNumber后的数据
		List<HomeBanner> list = getBySortNumber(sortNumber);
		if (Collections3.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSortNumber(sortNumber + (i + 1));
			}
			// 批量更新sortNumber
			updateBatchSortNumber(sortNumber, list);
		}
		return super.update(t);
	}

	/**
	 * 
	 * @Description: 获取符合条件的广告位分页数据
	 * @param params
	 * @return
	 * @throws
	 */
	@Override
	public Page<HomeBanner> getPageByParams(Map<String, Object> params,Page<HomeBanner> page) {
		// 1.设置总条数
		page.setDataCount(countPageByParams(params));
		
		// 2.设置rowRound参数
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数

		// 3.通过mapper查询数据
		List<HomeBanner> homeBanners = homeBannerMapper.getPageByParams(HomeBanner.tableName, params,rowBounds);
		
		// 4.在page对象中设置数据
		page.setResult(homeBanners);
		return page;
	}

	/**
	 * 
	 * @Description: 统计符合条件的广告位分页数据
	 * @param params
	 * @return
	 * @throws
	 */
	@Override
	public int countPageByParams(Map<String, Object> params) {
		return homeBannerMapper.countPageByParams(HomeBanner.tableName, params);
	}

	/** 
	 * @Description: 找出sortNumber后的数据
	 * @param sortNumber
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<HomeBanner> getBySortNumber(int sortNumber) {
		return homeBannerMapper.getBySortNumber(HomeBanner.tableName, sortNumber);
	}

	/** 
	 * @Description:  批量更新sortNumber
	 * @param sortNumber
	 * @param list
	 * @return
	 * @throws 
	*/ 
	@Override
	public int updateBatchSortNumber(int sortNumber, List<HomeBanner> list) {
		return homeBannerMapper.updateBatchSortNumber(HomeBanner.tableName, sortNumber, list);
	}
}
