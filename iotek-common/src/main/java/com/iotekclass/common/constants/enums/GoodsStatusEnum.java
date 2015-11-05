package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName GoodsStatusEnum.java
 * @Description 商品状态枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2014年12月19日 下午3:48:29
 * @Version 1.0
 */

public enum GoodsStatusEnum {
	OFF_THE_SHELF(0,"已下架"),
	ON_THE_SHELF(1,"已上架"), 
	NOT_THE_SHELF(2,"未上架"), 
	NOT_PASS (3,"未通过"), 
	PENDING_AUDIT(4,"待审核"),
	IS_DELETE(5,"已删除");
	
	private int value;

	private String label;

	private GoodsStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, GoodsStatusEnum> lookup = Maps.newHashMap();

	static {
		for (GoodsStatusEnum c : GoodsStatusEnum.values()) {
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
	
	public static GoodsStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
