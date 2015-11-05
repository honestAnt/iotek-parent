package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName GoodsSeoStatusEnum.java
 * @Description 商品SEO信息状态枚举-->后台管理系统-->系统管理-->SEO信息
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年07月09日 下午3:48:29
 * @Version 1.0
 */

public enum GoodsSeoStatusEnum {
	
	ALL(0,""),
	PENDING_AUDIT(1,"待审核"), 
	SEND_BACK(2,"退回"),
	RELEASED(3,"已发布");
	
	private int value;

	private String label;

	private GoodsSeoStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, GoodsSeoStatusEnum> lookup = Maps.newHashMap();

	static {
		for (GoodsSeoStatusEnum c : GoodsSeoStatusEnum.values()) {
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
	
	public static GoodsSeoStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
