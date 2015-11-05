package com.iotekclass.model.goods;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.util.DateUtil;
/**
 * 
 * @ClassName: Goods   
 * @Description： 商品基本表
 * @Author：gufeifei
 * @Date：2014年10月15日 下午1:14:52
 * @version
 */
public class Goods extends IdExtEntity implements Serializable{

	private static final long serialVersionUID = -7423095278795461736L;

	public static final String tableName = "tb_goods";
	
	/**
	 * 商品编号
	 */
	private String goodsCode;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品简称
	 */
	private String goodsLabel;
	
	/**
	 * 图片地址
	 */
	private String imageUrl;
	
	/**
	 * 商品状态
	 */
	private int status;
	
	/**
	 * 商品排序
	 */
	private int sortNumber;
	
	/**
	 * 学习指导
	 */
	private String guidance;
	/**
	 * 教学辅导
	 */
	private String coaching;
	
	/**
	 * 类型
	 */
	private int typeId;
	
	/**
	 * 分类
	 */
	private int categoryId;
	
	/**
	 * 浏览量(自定义视图字段)
	 */
	private int pageViews;
	
	/**
	 * 商品描述(自定义视图字段)
	 */
	private String intro;

	/**
	 * 是否包就业商品（含认证数）
	 */
	private int isCert;

	
	/**
	 * 商品市场价(自定义视图字段)
	 */
	private int price;
	
	/**
	 * 商品优惠价(自定义视图字段)
	 */
	private int salePrice;
	
	private int goodsLevel;
	
	private int checkUser;
	/**
	 * 促销价格
	 */
	private double promotionPrice;
	/**
	 * 促销开始时间
	 */
	private Date promotionExpire;
	/**
	 * 促销结束时间
	 */
	private Date promotionEffect;
	/**
	 * 是否展示促销价（视图字段）
	 */
	private boolean isShowPromotion;
	
	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsLabel() {
		return goodsLabel;
	}

	public void setGoodsLabel(String goodsLabel) {
		this.goodsLabel = goodsLabel;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getGuidance() {
		return guidance;
	}

	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}

	public String getCoaching() {
		return coaching;
	}

	public void setCoaching(String coaching) {
		this.coaching = coaching;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}


	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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


	public int getIsCert() {
		return isCert;
	}

	public void setIsCert(int isCert) {
		this.isCert = isCert;
	}

	public int getGoodsLevel() {
		return goodsLevel;
	}

	public void setGoodsLevel(int goodsLevel) {
		this.goodsLevel = goodsLevel;
	}

	public int getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(int checkUser) {
		this.checkUser = checkUser;
	}

	public Date getPromotionExpire() {
		return promotionExpire;
	}

	public void setPromotionExpire(Date promotionExpire) {
		this.promotionExpire = promotionExpire;
	}

	public Date getPromotionEffect() {
		return promotionEffect;
	}

	public void setPromotionEffect(Date promotionEffect) {
		this.promotionEffect = promotionEffect;
	}

	public boolean isShowPromotion() {
		if (DateUtil.isInterjacent(this.promotionEffect, this.promotionExpire)) { 
			isShowPromotion =true;
		}
		return isShowPromotion;
	}

	public void setShowPromotion(boolean isShowPromotion) {
		this.isShowPromotion = isShowPromotion;
	}

	public double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	
}
