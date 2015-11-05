package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName UserHomeworkTopicStatusEnum.java
 * @Description 用户做作业题的状态枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年2月5日 上午10:35:36
 * @Version 1.0
 */

public enum UserHomeworkTopicStatusEnum {

	//1: 未上传    2: 待批阅    3: 批阅中    4: 已批阅
	ALL(0,"所有"), 
	NO_UPLOAD(1,"未上传"), 
	TO_READ(2,"待批阅"), 
	READ_IN(3,"批阅中"), 
	HAD_READ(4,"已批阅");

	private int value;
	private String label;

	private UserHomeworkTopicStatusEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, UserHomeworkTopicStatusEnum> lookup = Maps.newHashMap();

	static {
		for(UserHomeworkTopicStatusEnum c : UserHomeworkTopicStatusEnum.values()) {
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

	public static UserHomeworkTopicStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}	
}
