package com.iotekclass.cms.service.information.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iotekclass.cms.model.information.Information;
import com.iotekclass.cms.persist.information.InformationMapper;
import com.iotekclass.cms.service.information.InformationService;
import com.iotekclass.common.util.ConvertCharToLabel;
import com.iotekclass.common.util.StringUtil;
import com.iotekclass.persist.pagination.Page;
import com.iotekclass.service.BaseServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * 
 * @ClassName: InformationServiceImpl   
 * @Description： 咨询信息实现类  
 * @Author：袁亚明
 * @Date：2015年7月14日 下午5:13:14
 * @version
 */
//@Service("informationService")
@Service(version = "1.0.0")
public class InformationServiceImpl extends BaseServiceImpl<Information> implements InformationService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3011226276463783461L;
	
	@Autowired
	private InformationMapper informationMapper;
	
	public InformationServiceImpl(){}
	
	@Autowired
	public InformationServiceImpl(InformationMapper informationMapper){
		super(informationMapper,Information.tableName);
	}

	/**
	 * 
	 * @Description: 保存资讯
	 * @param information
	 * @throws
	 */
	@Override
	public void saveInformation(Information information) {
		if (!StringUtil.isEmpty(information.getContent())) {
			String content = information.getContent();
			content = ConvertCharToLabel.toLabel(content); 
		    content = content.replace("'", "&apos;");
			content = content.replace("\n\r", "");
			content = content.replace("\r\n", "");//这才是正确的！
			content = content.replace("\t", "");
		}
		informationMapper.saveInformation(Information.tableName,information);
	}

	/**
	 * 
	 * @Description: 根据搜索条件查询资讯列表
	 * @param first_level_id
	 * @param second_level_id
	 * @param status
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return
	 * @throws
	 */
	@Override
	public Page<Information> informationSearch(Integer firstLevelId,
			Integer secondLevelId, Integer status, String title, String beginTime,
			String endTime,Page<Information> page) {
		page.setDataCount(informationMapper.getInformationListCount(Information.tableName,firstLevelId,secondLevelId,status,title,beginTime,endTime));
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());// 使用RowBounds计算偏移量和偏移总数
		List<Information> list = informationMapper.informationSearch(Information.tableName,firstLevelId,secondLevelId,status,title,beginTime,endTime,rowBounds);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * @Description: 修改资讯状态
	 * @param id
	 * @param status
	 * @param returnReason
	 * @param userId
	 * @throws
	 */
	@Override
	public void updateByStatus(int id,int status, String returnReason,int userId) {
		informationMapper.updateByStatus(Information.tableName,id,status,returnReason,userId);
	}
	
	/**
	 * 
	 * @Description: 获取发布时间最新的两条数据
	 * @return
	 * @throws
	 */
	@Override
	public List<Information> getFirstInfo(@Param("status") int status
			,@Param("featured") int featured ,@Param("number") int number) {
		return informationMapper.getFirstInfo(Information.tableName,status,featured,number);
	}

	/** 
	 * @Description: 根据分类查询咨询列表
	 * @param firstLevelId
	 * @param second_level_id
	 * @return
	 * @throws 
	*/ 
	@Override
	public List<Information> informationBySort(Integer firstLevelId,
			Integer second_level_id,int status) {
		 
		return informationMapper.informationBySort(Information.tableName, firstLevelId, second_level_id,status);
	}

}
