package com.iotekclass.cms.model.homepage;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
/**
 * 
 * @ClassName: RecommendPosition   
 * @Description： 推荐位实体类
 * @Author：hujing
 * @Date：2015年7月15日 下午1:40:00
 * @version
 */
public class RecommendPosition extends IdExtEntity implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 776039860973912280L;
	public static final String tableName = "tb_recommend_position";
	/**
	 * 推荐位名称
	 */
	private String name;
	
	
	public RecommendPosition() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
