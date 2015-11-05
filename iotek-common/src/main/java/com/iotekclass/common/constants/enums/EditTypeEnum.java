/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName: EditTypeEnum   
 * @Description： 编辑类型枚举
 * @Author：GuangChen
 * @Date：2015年1月16日 下午4:08:09
 * @version
 */
public enum EditTypeEnum {
	ALL(-1,"其他"),QUESTION(1,"问题"),ANSWER(2,"答案");
	
	private static final Map<Integer, EditTypeEnum> lookup = Maps.newHashMap();

	static {
		for (EditTypeEnum c : EditTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private EditTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

	public static EditTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
