package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @Description 认证考试类型枚举 
 * @author wangfengbao
 * @date 2015年1月23日 下午4:08:34
 *
 */

public enum CertExamTypeEnum {

	ALL(0,"所有"),CERT(1,"认证考试"),SECTION(2,"章检测");

	private int value;
	private String label;

	private CertExamTypeEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, CertExamTypeEnum> lookup = Maps.newHashMap();

	static {
		for(CertExamTypeEnum c : CertExamTypeEnum.values()) {
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

	public static CertExamTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}	
}
