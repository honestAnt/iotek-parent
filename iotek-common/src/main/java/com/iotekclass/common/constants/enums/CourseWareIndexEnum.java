/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 
 * @Description 视频位置索引 
 * @author wangfengbao
 * @date 2015年2月5日 下午5:23:41
 *
 */
public enum CourseWareIndexEnum {

	ZONE(0, "中间位置"), BEGIN(1, "首位"), END(2, "末尾");

	private static final Map<Integer, CourseWareIndexEnum> lookup = Maps.newHashMap();

	static {
		for (CourseWareIndexEnum c : CourseWareIndexEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private CourseWareIndexEnum(int value, String label) {
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

	public static CourseWareIndexEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
