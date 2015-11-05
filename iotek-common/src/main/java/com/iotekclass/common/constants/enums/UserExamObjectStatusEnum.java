package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName ExamPaperStatusEnum.java
 * @Description 用户与考试对象 状态 枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年1月16日 上午10:46:00
 * @Version 1.0
 */

public enum UserExamObjectStatusEnum {
	
	ALL(0,"所有"),
	READ_IN(1,"批阅中"), 
	PASS (2,"已通过"), 
	NO_PASS(3,"未通过"),
	STANDSTILL(4,"暂停中"),
	WAITING(5,"待批阅");
	
	private int value;

	private String label;

	private UserExamObjectStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, UserExamObjectStatusEnum> lookup = Maps.newHashMap();

	static {
		for (UserExamObjectStatusEnum c : UserExamObjectStatusEnum.values()) {
			lookup.put(c.getValue(), c);
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
	
	public static UserExamObjectStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
	
}
