package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;

/**
 * 
 * @ClassName: ManageRole   
 * @Description： 角色实体类 
 * @Author：hujing
 * @Date：2015年4月27日 下午1:53:57
 * @version
 */
public class ManageRole extends IdExtEntity implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3606282075387979413L;
	
	public static final String tableName = "tb_manage_role";
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色描述
	 */
	private String description;
	/**
	 * 角色分类
	 */
	private int type;

	public ManageRole(){}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
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
