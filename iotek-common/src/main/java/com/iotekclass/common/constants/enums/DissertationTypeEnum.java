package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName GoodsStatusEnum.java
 * @Description 操作资源枚举-->后台管理系统-->系统管理-->操作资源
 * @Author sq.xiao@iotek.com.cn
 * @Date 2014年12月19日 下午3:48:29
 * @Version 1.0
 */

public enum DissertationTypeEnum {
	
	ALL(0,""),
	EXTERNAL_DISSERTATION(1,"外部专题"), 
	INTERNAL_DISSERTATION(2,"内部专题");
	
	
	private int value;

	private String label;

	private DissertationTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, DissertationTypeEnum> lookup = Maps.newHashMap();

	static {
		for (DissertationTypeEnum c : DissertationTypeEnum.values()) {
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
	
	public static DissertationTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
