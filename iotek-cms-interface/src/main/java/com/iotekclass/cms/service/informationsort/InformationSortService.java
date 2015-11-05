package com.iotekclass.cms.service.informationsort;

import java.util.List;
import java.util.Map;

import com.iotekclass.cms.model.informationsort.InformationSort;
import com.iotekclass.service.BaseService;

/**
 * @ClassName: InformationSortService
 * @Description： 资讯分类Service
 * @Author：张帅
 * @Date：2015年7月13日 下午5:33:01
 * @version
 */
public interface InformationSortService extends BaseService<InformationSort> {
	
	/**
	 * 
	 * @Description: 获取重组后的分类信息集合
	 * @Author：张帅
	 * @Date：2015年7月14日 下午8:14:34
	 * @return
	 * @throws
	 */
	List<Map<String,Object>> getInfoSort();
	
	/**
	 * 
	 * @Description: 根据父节点id获取数据
	 * @Author：张帅
	 * @Date：2015年7月14日 下午8:15:08
	 * @param parentId
	 * @return
	 * @throws
	 */
	List<InformationSort> getByParentId(int parentId);
	
	
	/**
	 * 
	 * @Description: 验证分类名称是否存在
	 * @Author：张帅
	 * @Date：2015年7月15日 上午11:28:59
	 * @param sortName
	 * @return
	 * @throws
	 */
	InformationSort verifySortName(String sortName);
	
	/**
	 * 
	 * @Description: 查询所有引用此分类的资讯信息
	 * @Author：张帅
	 * @Date：2015年7月16日 下午2:52:17
	 * @param id
	 * @return
	 * @throws
	 */
	int countRefById(int id);
}
