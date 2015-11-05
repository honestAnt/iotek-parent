package com.iotekclass.model.school;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdEntity;
/**
 * 
 * ClassName: School   
 * Description：学校   
 * Author：黎斌龙
 * Date：2015年4月21日 上午10:31:55
 * @version
 */
public class School extends IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2810297404281045080L;
	
	public static final String tableName = "tb_school";
	
	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 学校简介
	 */
	private String intro;
	private int sortNumber;
	private List<Department> departmentList;
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

	/**
	 * @return the departmentList
	 */
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	/**
	 * @param departmentList the departmentList to set
	 */
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}


}
