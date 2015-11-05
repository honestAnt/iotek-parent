package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

public enum ObjectTypeEnum {

	GOODS(1,"商品"),TEACHER(2,"教师"),COURSEWARE(3,"视频"),OTHER(4,"其他"),LIVE(5,"直播课");
	
	private int value;
	private String label;
	
	private ObjectTypeEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, ObjectTypeEnum> lookup = Maps.newHashMap();
	
	static {
		for(ObjectTypeEnum c : ObjectTypeEnum.values()) {
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
	
	public int getId() {
		return this.ordinal();
	}
	
	public String getName() {
		return this.name();
	}
	
	public static ObjectTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
