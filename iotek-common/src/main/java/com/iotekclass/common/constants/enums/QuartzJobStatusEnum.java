package com.iotekclass.common.constants.enums;


import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 
 * @ClassName: QuartzJobTypeEnum
 * @Description：定时任务类型
 * @Author：Joshua
 * @Date：2015年9月25日10:23:14
 * @version
 */

public enum QuartzJobStatusEnum {

  	STOP(0,"未启动"),
	RUN(2,"运行");

	private static final Map<Integer, QuartzJobStatusEnum> lookup = Maps.newHashMap();

	static {
		for (QuartzJobStatusEnum c : QuartzJobStatusEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	QuartzJobStatusEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getId() {
		return this.ordinal();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return this.name();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static QuartzJobStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
