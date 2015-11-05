package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: TopicCategoryEnum   
 * @Description： 试题用途枚举 
 * @Author：hujing
 * @Date：2015年4月16日 下午1:34:51
 * @version
 */
public enum TopicCategoryEnum {

	ALL(0,"全部"),COMMON(1,"通用"), ABILITY(2,"能力测评"), EXERCISE(3,"练习"), TASK(4, "作业"), EXAM(5,"考试");
	private static final Map<Integer, TopicCategoryEnum> lookup = Maps.newHashMap();

	static {
		for (TopicCategoryEnum c : TopicCategoryEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private TopicCategoryEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getId() {
		return this.ordinal();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

	public static TopicCategoryEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
