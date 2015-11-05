package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;

/**
 * 
 * @ClassName: ManageAccountCourseRel   
 * @Description： 后台渠道教师课程关系实体  
 * @Author：hujing
 * @Date：2015年4月27日 下午1:43:33
 * @version
 */

public class ManageAccountCourseRel extends IdExtEntity implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6481879817533194266L;

	public static final String tableName = "tb_manage_account_course_rel";
	/**
	 * 教师账号id
	 */
	private int accountId;
	/**
	 * 课程id
	 */
	private int courseId;
	/**
	 * 排序
	 */
	private int sortNumber;
	
	public ManageAccountCourseRel(){}
	

	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
