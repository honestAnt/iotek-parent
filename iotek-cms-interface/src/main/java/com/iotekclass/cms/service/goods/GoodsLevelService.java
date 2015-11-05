package com.iotekclass.cms.service.goods;

import java.util.List;
import java.util.Map;

import com.iotekclass.model.goods.GoodsLevel;
import com.iotekclass.service.BaseService;

/**
 * 
 * @Description 商品分类业务接口 
 * @author wangfengbao
 * @date 2015年5月29日 上午10:59:01
 *
 */
public interface GoodsLevelService extends BaseService<GoodsLevel> {
	
	/**
	 * 
	 * @description 获取商品所有分类
	 * @author wangfengbao
	 * @date 2015年5月29日 上午10:59:38
	 *
	 * @param goodsId
	 * @return
	 */
	List<GoodsLevel> getLevelByGoodsId(int goodsId);
	
	/**
	 * 
	 * @description 获取所有符合条件的标签(无参数，则返回所有一级分类)
	 * @author wangfengbao
	 * @date 2015年5月29日 上午11:07:14
	 *
	 * @param type
	 * @param parentId
	 * @return
	 */
	List<GoodsLevel> getAllForHession(int type, int parentId);
	
	/**
	 * 
	 * @description 获取所有分类信息
	 * @author wangfengbao
	 * @date 2015年5月29日 上午11:46:50
	 *
	 * @return
	 */
	Map<String, Object> getAllLevels();
	
	/**
	 * 
	 * @description 校验一三级分类内容是否存在 
	 * @author wangfengbao
	 * @date 2015年6月2日 下午12:34:09
	 *
	 * @param content
	 * @return
	 */
	int countByContent(String content,int type);
	
	/**
	 * 
	 * @description 获取和商品有关系的分类
	 * @author wangfengbao
	 * @date 2015年6月3日 上午9:55:26
	 *
	 * @param type
	 * @param parentId
	 * @return
	 */
	List<GoodsLevel> getAllGoodsLevel(int type, int parentId);
	
	/**
	 * 
	 * @description 获取所有商品分类数据 
	 * @author wangfengbao
	 * @date 2015年6月3日 上午9:56:12
	 *
	 * @return
	 */
	Map<String, Object> getAllGoodsLevels();
	
	/**
	 * @Description: 根据商品ID查询该商品所有的二级分类(后台管理-->商品列表)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月11日 下午2:57:54
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	List<GoodsLevel> getsecondLevelListByGoodsId(int goodsId);
	
	/**
	 * @Description: 根据商品ID查询该商品的一级分类(后台管理-->商品列表)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年7月21日 上午10:09:20
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	GoodsLevel getFirstLevelByGoodsId(int goodsId);
	
}
