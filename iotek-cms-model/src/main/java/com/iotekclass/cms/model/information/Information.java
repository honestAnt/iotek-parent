package com.iotekclass.cms.model.information;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;


/**
 * 
 * @ClassName: Information   
 * @Description： 资讯实体类   
 * @Author：袁亚明
 * @Date：2015年7月13日 下午5:13:56
 * @version
 */
public class Information extends IdExtEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7760480023577545416L;
	
	public static String tableName = "tb_information";
	
	/**
	 * 资讯一级分类
	 */
	private int firstLevelId;
	
	/**
	 * 资讯二级分类
	 */
	private int secondLevelId;
	
	/**
	 * 资讯标题
	 */
	private String title;

	/**
	 * 资讯关键字
	 */
	private String keywords;

	/**
	 * 资讯描述
	 */
	private String description;

	/**
	 * 资讯发布时间
	 */
	private Date deliveryTime;

	/**
	 * 资讯来源
	 */
	private String source;

	/**
	 * 资讯推荐位
	 */
	private int featured;

	/**
	 * 资讯内容
	 */
	private String content;

	/**
	 * 资讯状态（0：待审核，1：已通过，2：退回）
	 */
	private int status;
	
	/**
	 * 一级分类名称
	 */
	private String firstLevelName;
	
	/**
	 * 二级分类名称
	 */
	private String secondLevelName;
	
	/**
	 * 退回理由
	 */
	private String returnReason;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getFeatured() {
		return featured;
	}
	public void setFeatured(int featured) {
		this.featured = featured;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFirstLevelName() {
		return firstLevelName;
	}
	public void setFirstLevelName(String firstLevelName) {
		this.firstLevelName = firstLevelName;
	}
	public String getSecondLevelName() {
		return secondLevelName;
	}
	public void setSecondLevelName(String secondLevelName) {
		this.secondLevelName = secondLevelName;
	}
	public int getFirstLevelId() {
		return firstLevelId;
	}
	public void setFirstLevelId(int firstLevelId) {
		this.firstLevelId = firstLevelId;
	}
	public int getSecondLevelId() {
		return secondLevelId;
	}
	public void setSecondLevelId(int secondLevelId) {
		this.secondLevelId = secondLevelId;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
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
