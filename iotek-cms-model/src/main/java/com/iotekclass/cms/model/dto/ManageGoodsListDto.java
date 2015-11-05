package com.iotekclass.cms.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.common.constants.enums.GoodsStatusEnum;
import com.iotekclass.model.goods.GoodsLevel;

/**
 * @ClassName ManageGoodsList.java
 * @Description 后台商品管理商品列表DTO
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年6月5日 下午5:27:59
 * @Version 1.0
 */

public class ManageGoodsListDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 409672475421251301L;

	
	/**
	 * 商品id
	 */
	private int id;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 分类1
	 */
	private String firstLevelName;
	
	/**
	 * 分类2
	 */
	private String secondLevelName;
	
	/**
	 * 分类3
	 */
	private String thirdLevelName;
	
	/**
	 * 商品市场价
	 */
	private int price;
	
	/**
	 * 优惠价
	 */
	private int salePrice;
	
	/**
	 * 提交人ID
	 */
	private String updateUser;
	
	/**
	 * 审核人ID
	 */
	private String checkUser;
	/**
	 * 提交人用户名
	 */
	private String updateUserName;
	/**
	 * 审核人用户名
	 */
	private String checkUserName;
	
	/**
	 * 发布时间
	 */
	private Date createTime;
	
	/**
	 * 商品二级分类
	 */
	private List<GoodsLevel> goodsLevelList;
	
	/**
	 * seoId
	 */
	private int seoId;
	
	/**
	 * seo标题
	 */
	private String title;
	
	/**
	 * seo关键字
	 */
	private String keywords;
	
	/**
	 * seo关键字描述
	 */
	private String keywordsDesc;  
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getThirdLevelName() {
		return thirdLevelName;
	}

	public void setThirdLevelName(String thirdLevelName) {
		this.thirdLevelName = thirdLevelName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public GoodsStatusEnum getStatusEnum() {
		return GoodsStatusEnum.fromOrdinal(this.status);
	}
	
	public String getStatusEnumLabel() {
		return GoodsStatusEnum.fromOrdinal(this.status).getLabel();
	}
	
	public List<GoodsLevel> getGoodsLevelList() {
		return goodsLevelList;
	}

	public void setGoodsLevelList(List<GoodsLevel> goodsLevelList) {
		this.goodsLevelList = goodsLevelList;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	
	public int getSeoId() {
		return seoId;
	}

	public void setSeoId(int seoId) {
		this.seoId = seoId;
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

	
	
	
	public ManageGoodsListDto() {

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
