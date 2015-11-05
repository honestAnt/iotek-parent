package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: CourseWareEnum
 * Description： 课件类型
 * Author：黎斌龙
 * Date：2014年10月14日 下午4:53:15
 * 
 * @version
 */
public enum CourseWareEnum {

	NORMAL(1, "普通"), EXTEND(2, "宣传"), SPECIAL(3, "特殊");

	private int value;

	private String label;

	private CourseWareEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, CourseWareEnum> lookup = Maps.newHashMap();

	static {
		for (CourseWareEnum c : CourseWareEnum.values()) {
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

	public static CourseWareEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
