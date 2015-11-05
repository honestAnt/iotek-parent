/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 
 * @Description 后台账号类型枚举 
 * @author wangfengbao
 * @date 2015年4月27日 上午10:29:27
 *
 */
public enum ManageAccountTypeEnum {

	ALL(0, "全部"), IOTEK_ADMIN(1, "网校管理员"), COLLEGE_ADMIN(2, "院校管理员"), COMPANY_ADMIN(3, "企业管理员");

	private static final Map<Integer, ManageAccountTypeEnum> lookup = Maps.newHashMap();

	static {
		for (ManageAccountTypeEnum c : ManageAccountTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private ManageAccountTypeEnum(int value, String label) {
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

	public static ManageAccountTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
