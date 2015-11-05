package com.iotekclass.cms.model.homepage;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.FriendlyLinkOpenWayEnum;
import com.iotekclass.common.constants.enums.FriendlyLinkTypeEnum;
/**
 * 
 * @ClassName: FriendlyLink   
 * @Description： 友情链接实体类   
 * @Author：hujing
 * @Date：2015年7月9日 上午10:50:34
 * @version
 */
public class FriendlyLink extends IdExtEntity implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8987391395749023773L;

	public static final String tableName = "tb_friendly_link";
	/**
	 * 友情链接名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 链接地址
	 */
	private String url;
	/**
	 * 打开方式
	 */
	private int openWay;
	/**
	 * 鼠标移动显示
	 */
	private String title;
	/**
	 * 链接图片文件路径
	 */
	private String filePath;
	
	public FriendlyLink() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}
	public FriendlyLinkTypeEnum getFriendlyLinkType() {
		return type > 0 ? FriendlyLinkTypeEnum.fromOrdinal(this.type):null;
	}
	public String getFriendlyLinkTypeEnumLabel(){
		FriendlyLinkTypeEnum friendlyLinkTypeEnum = type > 0 ? FriendlyLinkTypeEnum.fromOrdinal(this.type):null;
		if(friendlyLinkTypeEnum != null){
			return friendlyLinkTypeEnum.getLabel();
		}
		return "";
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOpenWay() {
		return openWay;
	}
	public FriendlyLinkOpenWayEnum getFriendlyLinkOpenWayEnum() {
		return openWay > 0 ? FriendlyLinkOpenWayEnum.fromOrdinal(this.openWay):null;
	}
	public String getFriendlyLinkOpenWayEnumLabel() {
		FriendlyLinkOpenWayEnum friendlyLinkOpenWayEnum = openWay > 0 ? FriendlyLinkOpenWayEnum.fromOrdinal(this.openWay):null;
		if(friendlyLinkOpenWayEnum != null ){
			return friendlyLinkOpenWayEnum.getLabel();
		}
		return "";
	}
	public void setOpenWay(int openWay) {
		this.openWay = openWay;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
