/**
 * 
 */
package com.iotekclass.model.company;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdEntity;

/**
 * @ClassName: Company   
 * @Description： 合作公司实体类  
 * @Author：GuangChen
 * @Date：2015年4月29日 上午10:19:15
 * @version
 */
public class Company extends IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8257958358001280323L;
	public static final String tableName = "tb_company";
	
	/**
	 *公司名称 
	 */
	private String name;
	/**
	 * 公司简介
	 */
	private String intro;
	
	/**
	 *排序号 
	 */
	private int sortNumber;
	
	private List<CompanyDepartment> companyDeList;

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

	/**
	 * @return the companyDeList
	 */
	public List<CompanyDepartment> getCompanyDeList() {
		return companyDeList;
	}

	/**
	 * @param companyDeList the companyDeList to set
	 */
	public void setCompanyDeList(List<CompanyDepartment> companyDeList) {
		this.companyDeList = companyDeList;
	}
}
