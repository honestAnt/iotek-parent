package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: CheckStatusEnum   
 * Description： 审核状态
 * Author：黎斌龙
 * Date：2015年1月23日 上午11:44:56
 * @version
 */
public enum CheckStatusEnum {

	CHECK(0, "审核中"), PASS(1, "已通过"), NOPASS(2, "未通过");
	
	private int value;
	private String label;
	
	private CheckStatusEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, CheckStatusEnum> lookup = Maps.newHashMap();
	
	static {
		for(CheckStatusEnum c : CheckStatusEnum.values()) {
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
	
	public static CheckStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
