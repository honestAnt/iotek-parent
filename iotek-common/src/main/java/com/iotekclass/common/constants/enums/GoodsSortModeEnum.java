package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: GoodsSortModeEnum
 * Description：商品展示方式
 * Author：王啸
 * Date：2014年10月20日 下午2:22:44
 * 
 * @version
 */
public enum GoodsSortModeEnum {

	ALL(0, "全部"), COMPREHENSIVE(1, "综合"), POPULARITY(2, "人气");

	private static final Map<Integer, GoodsSortModeEnum> lookup = Maps.newHashMap();

	static {
		for (GoodsSortModeEnum c : GoodsSortModeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private GoodsSortModeEnum(int value, String label) {
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

	public static GoodsSortModeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
