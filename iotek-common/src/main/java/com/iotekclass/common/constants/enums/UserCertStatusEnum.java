package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName UserCertStatus.java
 * @Description 用户认证状态
 * @Author hujing
 * @Date 2015年1月15日 下午1:38:04
 * @Version 1.0
 */

public enum UserCertStatusEnum {
	
	EXAM_ONGOING(0,"考试进行中"),EXAM_FINISH(1,"考试完成"),FACE_ONGOING(2,"面试进行中"),FACE_FINISH(3,"面试完成");
	
	private int value;
	private String label;
	
	private UserCertStatusEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, UserCertStatusEnum> lookup = Maps.newHashMap();
	
	static {
		for(UserCertStatusEnum c : UserCertStatusEnum.values()) {
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
	
	public static UserCertStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
