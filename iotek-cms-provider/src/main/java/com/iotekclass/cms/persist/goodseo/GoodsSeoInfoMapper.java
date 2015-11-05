package com.iotekclass.cms.persist.goodseo;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.goodseo.GoodsSeoInfo;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * @ClassName GoodsSeoInfoMapper.java
 * @Description 商品SEO信息数据操作接口
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年7月9日 下午4:47:14
 * @Version 1.0
 */

@MyBatisRepository
public interface GoodsSeoInfoMapper extends BaseMapper<GoodsSeoInfo> {

	/**
	 * @Description: 根据商品ID查询SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月20日 下午3:03:46
	 * @param goodsId
	 *  @param seoStatus
	 * @return 
	 * @throws
	 */
	GoodsSeoInfo getGoodSeoInfoByGoodsId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("goodsId") int goodsId);
	
	/**
	 * 
	 * @Description: 删除商品信息 
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 下午2:24:59
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	Integer deleteGoodsSeoInfoByGoodsId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("goodsId")int goodsId);
	
	
	/**
	 * 
	 * @Description: 获取单个已发布的商品SEO信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月23日 上午10:27:31
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	GoodsSeoInfo getReleasedGoodsSeoInfoByGoodsId(@Param(MAPPER_TABLE_NAME) String tableName, @Param("goodsId") int goodsId, @Param("seoStatus") int seoStatus);
	
}
