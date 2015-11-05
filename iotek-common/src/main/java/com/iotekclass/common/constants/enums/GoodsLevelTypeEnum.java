package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @Description 商品分类类型枚举
 * @author wangfengbao
 * @date 2015年5月28日 下午5:51:57
 *
 */

public enum GoodsLevelTypeEnum {

	FIRST_SECOND(1, "一二级分类"), THIRD(2, "三级分类");
	private int value;

	private String label;

	private GoodsLevelTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, GoodsLevelTypeEnum> lookup = Maps.newHashMap();

	static {
		for (GoodsLevelTypeEnum c : GoodsLevelTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	public String getName() {
		return this.name();
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static GoodsLevelTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
