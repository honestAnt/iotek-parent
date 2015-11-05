package com.iotekclass.cms.model.banner;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.HomeBannerEnableEnum;

/**
 * 
 * @ClassName: HomeBanner   
 * @Description： 首页广告实体   
 * @Author：王凤宝
 * @Date：2015年7月29日 上午11:43:09
 * @version
 */
public class HomeBanner extends IdExtEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4484502243435912329L;

	public static final String tableName = "tb_home_banner";
	
	/**
	 * 广告名
	 */
	private String name;
	
	/**
	 * 广告图片悬浮字体
	 */
	private String imgAlt;
	/**
	 * 广告图片链接
	 */
	private String imgUrl;
	/**
	 * 广告图片路径
	 */
	private String imgPath;
	
	/**
	 * 广告位置排序
	 */
	private int sortNumber;
	/**
	 * 是否可用(1:不可用,2:可用)
	 */
	private int enabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgAlt() {
		return imgAlt;
	}

	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * 
	 * @Description: 获取状态枚举
	 * @Author：王凤宝
	 * @Date：2015年7月29日 下午1:47:24
	 * @return
	 * @throws
	 */
	public HomeBannerEnableEnum getEnabledEnum() {
		return HomeBannerEnableEnum.fromOrdinal(this.enabled);
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
