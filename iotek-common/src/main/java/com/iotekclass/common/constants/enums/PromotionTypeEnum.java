package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: PromotionTypeEnum   
 * @Description： 促销类型枚举
 * @Author：hujing
 * @Date：2015年6月30日 下午2:08:21
 * @version
 */
public enum PromotionTypeEnum {
	
	ALL(0, "全部"), SINGLE(1, "打折"), MULTY(2, "立减"),CODE(3,"减至");

	private static final Map<Integer, PromotionTypeEnum> lookup = Maps.newHashMap();

	static {
		for (PromotionTypeEnum c : PromotionTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private PromotionTypeEnum(int value, String label) {
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

	public static PromotionTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
