/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;
/**
 * @ClassName: TopTypeEnum   
 * @Description： 问题置顶类型枚举
 * @Author：GuangChen
 * @Date：2015年1月16日 下午4:23:03
 * @version
 */
public enum TopTypeEnum {
	ALL(0,"all"),HOMEPAGE(1,"首页"),TOP(2,"置顶"),ESSENCE(3,"精华");
	
	private static final Map<Integer, TopTypeEnum> lookup = Maps.newHashMap();

	static {
		for (TopTypeEnum c : TopTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private TopTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

	public static TopTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
