package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: GenderEnum   
 * Description： TODO(这里用一句话描述这个类的作用)   
 * Author：黎斌龙
 * Date：2014年11月3日 上午11:31:19
 * @version
 */
public enum GenderEnum {

	FEMALE(0, "女"), MALE(1, "男");
	
	private int value;
	private String label;
	
	private GenderEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, GenderEnum> lookup = Maps.newHashMap();
	
	static {
		for(GenderEnum c : GenderEnum.values()) {
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
	
	public static GenderEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
