package com.iotekclass.cms.model.goodseo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.GoodsSeoStatusEnum;

/**
 * @ClassName GoodsSeoInfo.java
 * @Description 商品SEO信息表
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年7月9日 下午3:42:56
 * @Version 1.0
 */

public class GoodsSeoInfo extends IdExtEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3628053458271613243L;
	
	public static final String tableName = "tb_goods_seo_info";
	
	
	/**
	 * 商品Id
	 */
	private int goodsId;
	
	/**
	 * SEO标题
	 */
	private String title;
	
	/**
	 * SEO关键字
	 */
	private String keywords;
	
	/**
	 * SEO关键字描述
	 */
	private String keywordsDesc;
	
	/**
	 * SEO状态
	 */
	private int status;
	
	/**
	 * 审核人
	 */
	private int auditUser;
	
	/**
	 * 审核时间
	 */
	private Date auditTime;
	
	/**
	 * 退回理由
	 */
	private String backReason;

	/**
	 * 是否删除
	 */
	private int deleteFlag;
	
	
	
	public GoodsSeoInfo() {

	}
	
	
	
	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

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

	public String getKeywordsDesc() {
		return keywordsDesc;
	}

	public void setKeywordsDesc(String keywordsDesc) {
		this.keywordsDesc = keywordsDesc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	//获取状态枚举
	public GoodsSeoStatusEnum getTypeEnum() {
		return GoodsSeoStatusEnum.fromOrdinal(this.status);
	}
	
	public String getTypeLabel() {
		return GoodsSeoStatusEnum.fromOrdinal(this.status).getLabel();
	}
	

	public int getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(int auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
