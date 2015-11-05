package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName UserGoodsStatusEnum.java
 * @Description 用户与商品关系状态枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2014年12月19日 下午3:48:29
 * @Version 1.0
 */

public enum UserGoodsStatusEnum {
	ALL(0,"所有"),
	UNDERWAY(1,"进行中"), 
	LEVAING(2,"请假中"), 
	EXTENSION_DEADLINE (3,"延长期限中"), 
	FINISHED(4,"已完成"),
	EXPIRED(5,"已过期");
	
	private int value;

	private String label;

	private UserGoodsStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, UserGoodsStatusEnum> lookup = Maps.newHashMap();

	static {
		for (UserGoodsStatusEnum c : UserGoodsStatusEnum.values()) {
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
	
	public static UserGoodsStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
