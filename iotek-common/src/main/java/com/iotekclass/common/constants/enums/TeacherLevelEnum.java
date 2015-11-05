package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: TeacherLevelEnum   
 * Description： TODO(这里用一句话描述这个类的作用)   
 * Author：黎斌龙
 * Date：2014年10月16日 下午12:36:07
 * @version
 */
public enum TeacherLevelEnum {

	PRIMARY(1,"初级"),MIDDLE(2,"中级"),HIGH(3,"高级"),SENIOR(4,"资深");
	
	private int value;
	private String label;
	
	private TeacherLevelEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, TeacherLevelEnum> lookup = Maps.newHashMap();
	
	static {
		for(TeacherLevelEnum c : TeacherLevelEnum.values()) {
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
	
	public static TeacherLevelEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
