package com.iotekclass.model.school;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdEntity;
/**
 * 
 * ClassName: Department   
 * Description：院系 
 * Author：黎斌龙
 * Date：2015年4月21日 上午10:36:45
 * @version
 */
public class Department extends IdEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -685333363482598431L;

	public static final String tableName = "tb_department";
	
	private int schoolId;
	
	/**
	 * 院系名称
	 */
	private String name;
	/**
	 * 院系简介
	 */
	private String intro;
	private int sortNumber;
	private List<Mclass> classList;
	private String schoolName;
	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
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

	/**
	 * @return the classList
	 */
	public List<Mclass> getClassList() {
		return classList;
	}

	/**
	 * @param classList the classList to set
	 */
	public void setClassList(List<Mclass> classList) {
		this.classList = classList;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
