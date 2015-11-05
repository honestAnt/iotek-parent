package com.iotekclass.cms.service.informationsort.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.informationsort.InformationSort;
import com.iotekclass.cms.persist.informationsort.InformationSortMapper;
import com.iotekclass.cms.service.informationsort.InformationSortService;
import com.iotekclass.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: InformationSortServiceImpl
 * @Description： 资讯分类Service实现类
 * @Author：张帅
 * @Date：2015年7月13日 下午5:32:15
 * @version
 */
//@Service("informationSortService")
@Service(version = "1.0.0")
public class InformationSortServiceImpl extends
		BaseServiceImpl<InformationSort> implements InformationSortService {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3516262749847067057L;

	@Autowired
	InformationSortMapper informationSortMapper;

	public InformationSortServiceImpl() {
	}

	@Autowired
	public InformationSortServiceImpl(
			InformationSortMapper informationSortMapper) {
		super(informationSortMapper, InformationSort.tableName);
	}

	/**
	 * @Description: 分类数据重组
	 * @return
	 * @throws
	 */
	public List<Map<String,Object>> getInfoSort() {
		
		//parentId为0 表示父节点
		int parentId=0;
		//重组后的数据集合
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		// 获取所有的分类信息
		List<InformationSort> informationSortList = informationSortMapper.getAll(InformationSort.tableName);
		// 数据重组
		for (InformationSort informationSort1:informationSortList) {
			//父子节点map
			Map<String, Object> parentMap = new HashMap<String,Object>();
			//子节点的list
			List<InformationSort> tempList = new ArrayList<InformationSort>();
			//判断为父节点
			if (informationSort1.getParentId() == parentId) {
				//记录父节点信息
				parentMap.put("parent", informationSort1);
				//获取其所有子节点
				for (InformationSort informationSort2 : informationSortList) {
					if (informationSort1.getId() == informationSort2.getParentId()) {
						tempList.add(informationSort2);
					}
				}
				//记录子节点
				parentMap.put("children", tempList);
				//填充到list
				listMap.add(parentMap);
			}
		}
		return listMap;
	}
	
	/**
	 * 
	 * @Description: 根据父ID获取数据
	 * @param parentId
	 * @return
	 * @throws
	 */
	public List<InformationSort> getByParentId(int parentId){
		return informationSortMapper.getByParentId(parentId); 
	}
	
	/**
	 * 
	 * @Description:验证分类名称是否存在
	 * @param sortName
	 * @return
	 * @throws
	 */
	public InformationSort verifySortName(String sortName){
		
		return informationSortMapper.verifySortName(sortName);
	}
	
	/**
	 * 
	 * @Description: 查询所有引用此分类的资讯信息
	 * @param id
	 * @return
	 * @throws
	 */
	public int countRefById(int id){
		return informationSortMapper.countRefById(id);
	}

}
