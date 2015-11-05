/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 
 * @Description 后台账号修改日志类型枚举 
 * @author wangfengbao
 * @date 2015年4月27日 上午10:29:27
 *
 */
public enum ManageAccountLogTypeEnum {

	ALL(0, "全部"), INSERT(1, "新增"), UPDATE(2, "修改"), DELETE(3, "删除"),
	 ASSIGN_PERMISSION(4, "分配权限"), UPDATE_PERMISSION(5, "修改权限"), LOGIN(6, "登录");

	private static final Map<Integer, ManageAccountLogTypeEnum> lookup = Maps.newHashMap();

	static {
		for (ManageAccountLogTypeEnum c : ManageAccountLogTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private ManageAccountLogTypeEnum(int value, String label) {
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

	public static ManageAccountLogTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
