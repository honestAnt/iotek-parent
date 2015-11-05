package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: diplomaEnum   
 * Description： 学历
 * Author：黎斌龙
 * Date：2015年1月18日 下午4:48:23
 * @version
 */
public enum DiplomaEnum {
	BELOW(1,"高中以下"),GENERAL(2,"高中"),SECONDARY(3,"中专"),JUNIOR(4,"大专"),
	UNDERGRADUATE(5,"本科"),MASTER(6,"硕士"),DOCTOR(7,"博士"),POSTDOCTOR(8,"博士以上");
	private int value;
	private String label;
	
	private DiplomaEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, DiplomaEnum> lookup = Maps.newHashMap();
	
	static {
		for(DiplomaEnum c : DiplomaEnum.values()) {
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
	
	public static DiplomaEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
