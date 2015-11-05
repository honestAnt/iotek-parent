package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: IfUseEnum   
 * Description： TODO(这里用一句话描述这个类的作用)   
 * Author：黎斌龙
 * Date：2014年11月3日 上午10:52:56
 * @version
 */
public enum IfUseEnum {

	UNUSEFUL(0, "不可用"), USEFUL(1, "可用");
	
	private int value;
	private String label;
	
	private IfUseEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, IfUseEnum> lookup = Maps.newHashMap();
	
	static {
		for(IfUseEnum c : IfUseEnum.values()) {
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
	
	public static IfUseEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
