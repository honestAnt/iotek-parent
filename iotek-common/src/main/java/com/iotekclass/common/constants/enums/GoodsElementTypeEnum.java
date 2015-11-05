package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: GoodsLabelEnum
 * @Description： 商品元素类型
 * @Author：zhangwenlei
 * @Date：2014年10月17日 上午10:03:50
 * @version
 */

public enum GoodsElementTypeEnum {

	COURSE(1, "课程"), CERTIFICATION(2, "认证"),SERVICE(3, "服务");
	private int value;

	private String label;

	private GoodsElementTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, GoodsLabelCategoryEnum> lookup = Maps.newHashMap();

	static {
		for (GoodsLabelCategoryEnum c : GoodsLabelCategoryEnum.values()) {
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

	public static GoodsLabelCategoryEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
