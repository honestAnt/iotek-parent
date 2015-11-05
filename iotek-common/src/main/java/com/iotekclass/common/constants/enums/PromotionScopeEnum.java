package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: PromotionScopeEnum   
 * @Description： 促销范围枚举
 * @Author：hujing
 * @Date：2015年6月30日 下午2:08:21
 * @version
 */
public enum PromotionScopeEnum {
	
	ALL(0, "全部"), SINGLE(1, "全部商品"), MULTY(2, "部分商品"),CODE(3,"价格段");

	private static final Map<Integer, PromotionScopeEnum> lookup = Maps.newHashMap();

	static {
		for (PromotionScopeEnum c : PromotionScopeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private PromotionScopeEnum(int value, String label) {
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

	public static PromotionScopeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
