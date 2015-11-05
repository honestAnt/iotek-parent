/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * @ClassName: BooleanEnum   
 * @Description： 这只是一个普通的枚举，不要用布尔类型来分析它。 注意的是ALL只用来进行查询，不要保存到数据库
 * @Author：GuangChen
 * @Date：2015年1月16日 下午3:09:52
 * @version
 */
public enum BooleanEnum {

	ALL(-1, "全部"), FALSE(0, "否"), TRUE(1, "是");

	private static final Map<Integer, BooleanEnum> lookup = Maps.newHashMap();

	static {
		for (BooleanEnum c : BooleanEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private BooleanEnum(int value, String label) {
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

	public static BooleanEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
