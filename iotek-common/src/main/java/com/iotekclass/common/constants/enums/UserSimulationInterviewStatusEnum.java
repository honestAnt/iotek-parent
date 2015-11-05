package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName SimulationInterviewStatusEnum.java
 * @Description 模拟面试 状态 枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年1月16日 上午11:29:38
 * @Version 1.0
 */

public enum UserSimulationInterviewStatusEnum {
	
	ALL(0,"所有"),
	UNDERWAY(1,"进行中"),
	PASS (2,"已通过"), 
	NO_PASS(3,"未通过"),
	ARRANGING(4,"安排中");
	private int value;

	private String label;

	private UserSimulationInterviewStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, UserSimulationInterviewStatusEnum> lookup = Maps.newHashMap();

	static {
		for (UserSimulationInterviewStatusEnum c : UserSimulationInterviewStatusEnum.values()) {
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
	
	public static UserSimulationInterviewStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
	
}
