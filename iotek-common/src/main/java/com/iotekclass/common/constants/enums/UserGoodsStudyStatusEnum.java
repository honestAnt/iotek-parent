package com.iotekclass.common.constants.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @ClassName UserGoodsStudyStatusEnum.java
 * @Description 用户与商品学关系 学习 状态枚举
 * @Author sq.xiao@iotek.com.cn
 * @Date 2015年10月14日 下午1:13:24
 * @Version 1.0
 */

public enum UserGoodsStudyStatusEnum {
	ALL(0,"所有"),
	STUDY_NOTSTARTED(1,"未开始"),
	STUDY_UNDERWAY(2,"进行中"),
	STUDY_FINISHED(3,"已完成");

	private int value;

	private String label;

	private UserGoodsStudyStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, UserGoodsStudyStatusEnum> lookup = Maps.newHashMap();

	static {
		for (UserGoodsStudyStatusEnum c : UserGoodsStudyStatusEnum.values()) {
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
	
	public static UserGoodsStudyStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
