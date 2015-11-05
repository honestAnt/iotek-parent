package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @Description 试题类型枚举 
 * @author wangfengbao
 * @date 2015年1月21日 上午10:23:12
 *
 */
public enum TopicTypeEnum {

	ALL(0, "全部"), SINGLE(1, "单选题"), MULTY(2, "多选题"),CODE(3,"编程题");

	private static final Map<Integer, TopicTypeEnum> lookup = Maps.newHashMap();

	static {
		for (TopicTypeEnum c : TopicTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private TopicTypeEnum(int value, String label) {
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

	public static TopicTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
