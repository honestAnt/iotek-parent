package com.iotekclass.model.goods;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.GoodsLevelTypeEnum;


/**
 * 
 * @Description 商品分类表
 * @author wangfengbao
 * @date 2015年5月28日 下午5:40:19
 *
 */
public class GoodsLevel extends IdExtEntity  implements  Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8410717785085723647L;

	public static final String tableName = "tb_goods_level";
	
	/**
	 * 分类内容
	 */
	private String content;
	
	/**
	 * 商品分类(1：一二分类 2：三级分类)
	 */
	private int  type; 
	
	/**
	 * 父分类标签id
	 */
	private int parentId;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public GoodsLevelTypeEnum getTypeEnum() {
		
		return GoodsLevelTypeEnum.fromOrdinal(this.type);
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
