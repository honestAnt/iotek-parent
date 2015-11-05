package com.iotekclass.cms.service.permission.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.cms.persist.permission.ManageRoleMapper;
import com.iotekclass.cms.service.permission.ManageRoleService;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 * @ClassName: ManageRoleServiceImpl   
 * @Description： 后台角色业务逻辑实现  
 * @Author：hujing
 * @Date：2015年4月27日 下午3:01:41
 * @version
 */
//@Service("manageRoleService")
@Service(version = "1.0.0")
public class ManageRoleServiceImpl extends BaseServiceImpl<ManageRole> implements ManageRoleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111859574080383660L;
	@Autowired
	ManageRoleMapper manageRoleMapper;

	public ManageRoleServiceImpl() {

	}

	@Autowired
	public ManageRoleServiceImpl(ManageRoleMapper manageRoleMapper) {
		super(manageRoleMapper, ManageRole.tableName);
	}

	/**
	 * 
	 * @Description: 通过账号id获取对应角色信息
	 * @Author：hujing
	 * @Date：2015年4月27日 下午3:04:20
	 * @param accountId
	 * @return
	 * @throws
	 */
	@Override
	public List<ManageRole> getByAccountId(int accountId) {
		return manageRoleMapper.getByAccountId(ManageRole.tableName, accountId);
	}

	@Override
	public Page<ManageRole> getByType(Page<ManageRole> page,int type) {
		// 1.设置总条数
		page.setDataCount(manageRoleMapper.getCountByType(ManageRole.tableName,type));
		// 2.设置rowRound参数(模块写法)
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数
		// 3.通过mapper查询数据
		List<ManageRole> managRoleList = manageRoleMapper.getByTypePage(ManageRole.tableName,type, rowBounds);
		// 4.在page对象中设置数据
		page.setResult(managRoleList);
		// 5.返回Page
		return page;
	}

	@Override
	public List<ManageRole> getByType(int type) {
		return manageRoleMapper.getByType(ManageRole.tableName, type);
	}

	@Override
	public ManageRole findByNameOrType(String name, int type) {
		return manageRoleMapper.findByNameOrType(ManageRole.tableName, name, type);
	}


}
