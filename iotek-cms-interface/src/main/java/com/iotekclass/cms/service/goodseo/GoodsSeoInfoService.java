package com.iotekclass.cms.service.goodseo;

import java.util.Map;

import com.iotekclass.cms.model.goodseo.GoodsSeoInfo;
import com.iotekclass.service.BaseService;

/**
 * @ClassName GoodsSeoInfoService.java
 * @Description 商品SEO信息接口
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年7月9日 下午4:35:28
 * @Version 1.0
 */

public interface GoodsSeoInfoService extends BaseService<GoodsSeoInfo> {
	
	/**
	 * @Description: 根据商品ID查询商品信息和SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月20日 下午3:03:46
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	Map<String, Object> getGoodSeoInfoByGoodsId(int goodsId);
	
	/**
	 * 
	 * @Description:商品SEO信息操作(添加或删除) 
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午1:51:32
	 * @param paramsMap
	 * @return 
	 * @throws
	 */
	Integer operateGoodsSeoInfo(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @Description: 删除商品信息 
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午2:24:59
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	Integer deleteGoodsSeoInfoByGoodsId(int goodsId);
	
	/**
	 * 
	 * @Description: 获取单个已发布的商品SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月23日 上午10:27:31
	 * @param goodsId
	 * @param seoStatus 
	 * @return 
	 * @throws
	 */
	GoodsSeoInfo getReleasedGoodsSeoInfoByGoodsId(int goodsId, int seoStatus);
	
}
