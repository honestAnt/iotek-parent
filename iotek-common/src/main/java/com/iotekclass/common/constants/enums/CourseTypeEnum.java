package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: CourseTypeEnum   
 * Description： TODO(这里用一句话描述这个类的作用)   
 * Author：黎斌龙
 * Date：2014年10月16日 下午12:35:38
 * @version
 */
public enum CourseTypeEnum {

	JAVA(1, "Java"), ANDROID(2, "Android"), CLANGUAGE(3, "C语言"), CPLUSPLUS(4, "C++");
	
	private int value;
	private String label;
	
	private CourseTypeEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, CourseTypeEnum> lookup = Maps.newHashMap();
	
	static {
		for(CourseTypeEnum c : CourseTypeEnum.values()) {
			lookup.put(c.getValue(),c);
		}
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
	
	public int getId() {
		return this.ordinal();
	}
	
	public String getName() {
		return this.name();
	}
	
	public static CourseTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
