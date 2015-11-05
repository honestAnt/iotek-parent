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
public enum TopicDifficultEnum {

	ALL(0, "全部"), SIMPLE(1, "低"), MIDDLE(2, "中"),DIFFICULT(3,"高");

	private static final Map<Integer, TopicDifficultEnum> lookup = Maps.newHashMap();

	static {
		for (TopicDifficultEnum c : TopicDifficultEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private TopicDifficultEnum(int value, String label) {
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

	public static TopicDifficultEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
