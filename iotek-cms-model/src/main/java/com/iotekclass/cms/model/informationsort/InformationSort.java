package com.iotekclass.cms.model.informationsort;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;


/**
 * @ClassName: InformationSort
 * @Description：资讯分类 实体类
 * @Author：张帅
 * @Date：2015年7月13日 下午4:19:57
 * @version
 */
public class InformationSort extends IdExtEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3173714054892970118L;

	/**
	 * 表名
	 */
	public static final String tableName = "tb_information_sort";

	/**
	 * 分类名词
	 */
	private String sortName;

	/**
	 * SEO标题
	 */
	private String seoTitle;

	/**
	 * SEO关键字
	 */
	private String seoKeywords;

	/**
	 * SEO描述
	 */
	private String seoDescr;

	/**
	 * 分类排序
	 */
	private int sortNumber;

	/**
	 * 引用自身父节点ID
	 */
	private int parentId;

	public InformationSort() {
	}

	public InformationSort(int id) {
		super();
		this.id = id;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescr() {
		return seoDescr;
	}

	public void setSeoDescr(String seoDescr) {
		this.seoDescr = seoDescr;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public static String getTablename() {
		return tableName;
	}

	 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
