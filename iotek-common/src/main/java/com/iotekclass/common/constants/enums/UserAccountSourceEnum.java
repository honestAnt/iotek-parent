package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

public enum UserAccountSourceEnum {

	ALL(0,"全部"),
	WEB_REGISTER(1,"网站注册"),
	WEB_IMPORT(2,"网站导入"),
	BACKGROUND_IMPORT(3,"后台上传"),
	MARKET_PROMOTE(4,"市场推广"),
	SEM_PACKAGE_EMPLOYMENT(5,"SEM包就业"),
	SEM_ZERO_BASIC(6,"SEM零基础"),
	SEM_ZERO_BASIC_EX(7,"SEM零基础推广"),
	WEB_REGISTER_SCHOOL(8,"校园用户"),
	WEB_REGISTER_COMMUNICATION(9,"渠道用户"),
	WEB_REGISTER_COMPANY(10,"企业用户"),
	WEB_REGISTER_UNIVERSITY(11,"高校联盟"),
	EXCELLENT_TALENTS_PLAN(12,"优才计划");

	

	private int value;
	private String label;
	
	private UserAccountSourceEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, UserAccountSourceEnum> lookup = Maps.newHashMap();
	
	static {
		for(UserAccountSourceEnum c : UserAccountSourceEnum.values()) {
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
	
	
	
	public static UserAccountSourceEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
