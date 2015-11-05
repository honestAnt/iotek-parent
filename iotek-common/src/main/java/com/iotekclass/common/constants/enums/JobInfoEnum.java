package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName JobInfoEnum.java
 * @Description 就业资讯种类枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年2月6日 下午2:21:48
 * @Version 1.0
 */

public enum JobInfoEnum {
	
	ALL(0,"全部"), 
	POLICY(1,"政策"), 
	NEWS(2,"新闻"),
	NOTIFICATION(5,"通知");
	
	private int value;
	private String label;

	private JobInfoEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, JobInfoEnum> lookup = Maps.newHashMap();

	static {
		for(JobInfoEnum c : JobInfoEnum.values()) {
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

	public static JobInfoEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}	
	
}
