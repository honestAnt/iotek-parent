package com.iotekclass.cms.service.goods;

import java.util.Map;

import com.iotekclass.model.dto.ManageGoodsListDto;
import com.iotekclass.model.goods.Goods;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseService;
/**
 * 
 * @ClassName: GoodsService   
 * @Description： 商品业务接口
 * @Author：gufeifei
 * @Date：2014年10月15日 下午1:40:43
 * @version
 */
public interface GoodsService extends BaseService<Goods>{
	
	/**
	 * @Description: 后台管理首页查询已上架的所有商品信息
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月3日 下午2:14:57
	 * @return 
	 * @throws
	 */
	Map<String,Object> getGoodsListAboutGoodsManageIndex();
	
	/**
	 * @Description: 分页查询商品列表数据 (后台管理-商品管理-商品列表)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月5日 下午5:36:39
	 * @param paramsMap
	 * @param page
	 * @return 
	 * @throws
	 */
	Page<ManageGoodsListDto> searchGoodsList(Map<String, Object> paramsMap,Page<ManageGoodsListDto> page);
	
	/**
	 * @Description: 查询符合条件的商品总数量(分页用)(后台管理-商品管理-商品列表)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年6月5日 下午5:36:39
	 * @param paramsMap
	 * @return 
	 * @throws
	 */
	int searchGoodsListCount(Map<String, Object> paramsMap);
	
}
