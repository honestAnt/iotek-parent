package com.iotekclass.model.school;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdEntity;

/**
 * 
 * ClassName: Mclass   
 * Description：班级（后台导入）     
 * Author：黎斌龙
 * Date：2015年4月21日 上午10:40:59
 * @version
 */
public class Mclass extends IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 842059620813162417L;

	public static final String tableName = "tb_class";
	
	/**
	 * 所属院系ID
	 */
	private int departmentId;

	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 学校简介
	 */
	private String intro;
	
	private int sortNumber;
	
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
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
