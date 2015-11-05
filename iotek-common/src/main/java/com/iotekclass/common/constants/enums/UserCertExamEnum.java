package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName UserCertExamEnum.java
 * @Description 
 * @Author hujing
 * @Date 2015年1月15日 下午3:35:34
 * @Version 1.0
 */

public enum UserCertExamEnum {
	//0：未开启 1:进行中,2.批阅中3:未通过,4:已通过 5:待批阅
	NOT_OPEN(0,"未开启"),ONGOING(1,"进行中"),CORRENT(2,"批阅中"),NOT_PASS(3,"未通过"),PASS(4,"已通过"),WAITING(5,"待批阅");

	private int value;
	private String label;

	private UserCertExamEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, UserCertExamEnum> lookup = Maps.newHashMap();

	static {
		for(UserCertExamEnum c : UserCertExamEnum.values()) {
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

	public static UserCertExamEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}	
}
