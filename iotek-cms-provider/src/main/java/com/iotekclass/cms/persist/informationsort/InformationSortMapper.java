package com.iotekclass.cms.persist.informationsort;

import java.util.List;

import com.iotekclass.cms.model.informationsort.InformationSort;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

@MyBatisRepository
public interface InformationSortMapper extends BaseMapper<InformationSort> {
	/**
	 * @Description:  根据父节点Id获取数据集合
	 * @Author：张帅
	 * @Date：2015年7月14日 下午8:14:09
	 * @param parentId
	 * @return
	 * @throws
	 */
	List<InformationSort> getByParentId(int parentId);

	/**
	 * @Description: 验证分类名称
	 * @Author：张帅
	 * @Date：2015年7月15日 上午11:32:16
	 * @param sortName
	 * @return
	 * @throws
	 */
	InformationSort verifySortName(String sortName);
	
	/**
	 * 
	 * @Description: 查询所有引用此分类的资讯信息
	 * @Author：张帅
	 * @Date：2015年7月16日 下午2:47:28
	 * @param id
	 * @return
	 * @throws
	 */
	int countRefById(int id);

}
