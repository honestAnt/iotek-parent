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

public enum QuartzJobTypeEnum {

	ALL(-1,"全部"),
  	FIXEDTIME(1, "固定时间(每天)"),
    INTERVALTIME(2, "间隔时间");

	private static final Map<Integer, QuartzJobTypeEnum> lookup = Maps.newHashMap();

	static {
		for (QuartzJobTypeEnum c : QuartzJobTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	QuartzJobTypeEnum(int value, String label) {
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

	public static QuartzJobTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
