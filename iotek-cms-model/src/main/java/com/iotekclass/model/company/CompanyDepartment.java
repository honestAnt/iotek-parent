/**
 * 
 */
package com.iotekclass.model.company;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdEntity;

/**
 * @ClassName: CompanyDepartment   
 * @Description： 公司部门实体类  
 * @Author：GuangChen
 * @Date：2015年4月29日 上午10:27:32
 * @version
 */
public class CompanyDepartment extends IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2610826155757845541L;
	public static final String tableName = "tb_company_department";
	
	
	private int companyId;
	
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门简介
	 */
	private String intro;
	/**
	 * 排序号
	 */
	private int sortNumber;
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}
	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	/**
	 * @return the sortNumber
	 */
	public int getSortNumber() {
		return sortNumber;
	}
	/**
	 * @param sortNumber the sortNumber to set
	 */
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
