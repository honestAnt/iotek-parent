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

public enum CompetitionCoursewareTypeEnum {
	
	ALL(0,""),
	BEIDOU_COURSE(1,"北斗课程"), 
	IOS_COURSE(2,"iOS课程"),
	ANDROID_COURSE(3,"Android课程"),
	OPEN_PLATFORM(4,"开放平台"),
	CONTEST_PREACH(5,"大赛宣讲");
	
	private int value;

	private String label;

	private CompetitionCoursewareTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, CompetitionCoursewareTypeEnum> lookup = Maps.newHashMap();

	static {
		for (CompetitionCoursewareTypeEnum c : CompetitionCoursewareTypeEnum.values()) {
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
	
	public static CompetitionCoursewareTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
