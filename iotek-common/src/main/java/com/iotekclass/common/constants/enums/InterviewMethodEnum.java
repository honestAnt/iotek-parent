package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName InterviewMethodEnum.java
 * @Description 
 * @Author hujing
 * @Date 2015年1月19日 下午1:33:26
 * @Version 1.0
 */

public enum InterviewMethodEnum {
	QQ(0, "QQ视频"),YY(1, "YY视频"),UNDERLINE(2,"线下面试");
	
	private int value;
	private String label;
	
	private InterviewMethodEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, InterviewMethodEnum> lookup = Maps.newHashMap();
	
	static {
		for(InterviewMethodEnum c : InterviewMethodEnum.values()) {
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
	
	public static InterviewMethodEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
