package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: FriendlyLinkTypeEnum   
 * @Description： 友情链接类型枚举   
 * @Author：hujing
 * @Date：2015年7月9日 下午2:53:26
 * @version
 */
public enum FriendlyLinkTypeEnum {
	
	ALL(0, "全部"), WORD_URL(1, "文字链接"), PICTURE_URL(2, "图片链接");

	private static final Map<Integer, FriendlyLinkTypeEnum> lookup = Maps.newHashMap();

	static {
		for (FriendlyLinkTypeEnum c : FriendlyLinkTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private FriendlyLinkTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getId() {
		return this.ordinal();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return this.name();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static FriendlyLinkTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
