package com.iotekclass.cms.persist.information;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotekclass.cms.model.informationsort.InformationSort;
import com.iotekclass.persist.BaseMapper;
import com.iotekclass.persist.MyBatisRepository;

/**
 * 
 * @ClassName: LevelMapper   
 * @Description： 一级分类接口
 * @Author：袁亚明
 * @Date：2015年7月14日 上午10:04:53
 * @version
 */
@MyBatisRepository
public interface LevelMapper extends BaseMapper<InformationSort>{
	List<InformationSort> getLevelAll(@Param(MAPPER_TABLE_NAME) String tableName,@Param("levelId") int levelId);
	InformationSort getLevelSeo(@Param(MAPPER_TABLE_NAME) String tableName,@Param("levelId") int levelId);
}
