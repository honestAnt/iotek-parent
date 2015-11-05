package com.iotekclass.cms.service.goodseo.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.goodseo.GoodsSeoInfo;
import com.iotekclass.cms.persist.goodseo.GoodsSeoInfoMapper;
import com.iotekclass.cms.service.goods.GoodsLevelService;
import com.iotekclass.cms.service.goods.GoodsService;
import com.iotekclass.cms.service.goodseo.GoodsSeoInfoService;
import com.iotekclass.common.constants.enums.GoodsSeoStatusEnum;
import com.iotekclass.model.goods.Goods;
import com.iotekclass.model.goods.GoodsLevel;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName GoodsSeoInfoServiceImpl.java
 * @Description 商品SEO信息逻辑接口实现类
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年7月9日 下午4:37:51
 * @Version 1.0
 */

//@Service("goodsSeoInfoService")
	@Service(version = "1.0.0")
public class GoodsSeoInfoServiceImpl extends BaseServiceImpl<GoodsSeoInfo> implements
		GoodsSeoInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8971902650220619201L;

	@Autowired
	GoodsSeoInfoMapper goodsSeoInfoMapper;

//	@Reference(version = "1.0.0")
//	GoodsService goodsService;
	
//	@Reference(version = "1.0.0")
//	GoodsLevelService goodsLevelService;
	
	
	public GoodsSeoInfoServiceImpl() {

	}
	
	@Autowired
	public GoodsSeoInfoServiceImpl(GoodsSeoInfoMapper goodsSeoInfoMapper){
		super(goodsSeoInfoMapper, GoodsSeoInfo.tableName);
	}

	
	/**
	 * @Description: 根据商品ID查询SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月20日 下午3:03:46
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	@Override
	public Map<String, Object> getGoodSeoInfoByGoodsId(int goodsId) {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		//商品信息
		/*Goods good = goodsService.getById(goodsId);
		
		//商品分类一
		GoodsLevel firstLevel= goodsLevelService.getFirstLevelByGoodsId(goodsId);

		//商品分类二
		List<GoodsLevel> secondLevelList = goodsLevelService.getsecondLevelListByGoodsId(goodsId);
		
		//商品SEO信息
		GoodsSeoInfo seoInfo = goodsSeoInfoMapper.getGoodSeoInfoByGoodsId(GoodsSeoInfo.tableName, goodsId);
		boolean updateFlag = false;
		if(seoInfo != null){
			updateFlag = true;	
		}
		
		dataMap.put("good",good);
		dataMap.put("firstLevel",firstLevel);
		dataMap.put("secondLevelList",secondLevelList);
		dataMap.put("seoInfo",seoInfo);
		dataMap.put("updateFlag",updateFlag);*/
		
		return dataMap;
	}

	
	/**
	 * 
	 * @Description:商品SEO信息操作(添加或删除) 
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午1:51:32
	 * @param paramsMap
	 * @return 
	 * @throws
	 */
	@Override
	public Integer operateGoodsSeoInfo(Map<String, Object> paramsMap) {
		
		GoodsSeoInfo seoInfo = new GoodsSeoInfo();
		
		seoInfo.setGoodsId(Integer.parseInt(paramsMap.get("goodsId").toString()));
		seoInfo.setTitle(paramsMap.get("title").toString());
		seoInfo.setKeywords(paramsMap.get("keywords").toString());
		seoInfo.setKeywordsDesc(paramsMap.get("keywordsDesc").toString());
		seoInfo.setStatus(GoodsSeoStatusEnum.PENDING_AUDIT.getValue());
		
		int operationNum = 0;
		if(Boolean.parseBoolean(paramsMap.get("updateFlag").toString())){
			seoInfo.setId(Integer.parseInt(paramsMap.get("seoId").toString()));
			seoInfo.setUpdateUser(Integer.parseInt(paramsMap.get("loginUserId").toString()));
			operationNum = goodsSeoInfoMapper.update(seoInfo);
		}else{
			seoInfo.setDeleteFlag(0);
			seoInfo.setCreateUser(Integer.parseInt(paramsMap.get("loginUserId").toString()));
			operationNum = goodsSeoInfoMapper.save(seoInfo);
		}
		
		return operationNum;
	}

	/**
	 * 
	 * @Description: 删除商品信息 
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午2:24:59
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	@Override
	public Integer deleteGoodsSeoInfoByGoodsId(int goodsId) {
		return goodsSeoInfoMapper.deleteGoodsSeoInfoByGoodsId(GoodsSeoInfo.tableName, goodsId);
	}

	
	
	/**
	 * 
	 * @Description: 获取单个商品的SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月23日 上午10:27:31
	 * @param goodsId
	 * @param seoStatus
	 * @return 
	 * @throws
	 */
	@Override
	public GoodsSeoInfo getReleasedGoodsSeoInfoByGoodsId(int goodsId, int seoStatus) {
		return goodsSeoInfoMapper.getReleasedGoodsSeoInfoByGoodsId(GoodsSeoInfo.tableName, goodsId, seoStatus);
	}
	
	
}
