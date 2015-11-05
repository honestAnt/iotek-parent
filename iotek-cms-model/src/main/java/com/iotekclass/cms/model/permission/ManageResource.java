package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;

/**
 * 
 * @ClassName: ManageResource   
 * @Description： 资源实体类   
 * @Author：hujing
 * @Date：2015年4月27日 下午2:10:31
 * @version
 */
public class ManageResource extends IdExtEntity implements Serializable{

	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5185290171515917743L;
	
	public static final String tableName = "tb_manage_resource";
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 资源路径
	 */
	private String url;
	/**
	 * 父资源id
	 */
	private int parentId;
	/**
	 * 资源排序
	 */
	private int sortNumber;
	/**
	 * 状态
	 */
	private int state;


	public ManageResource(){}
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
