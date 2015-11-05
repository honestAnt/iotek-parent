package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: ManageRoleTypeEnum   
 * @Description： 后台角色枚举类
 * @Author：hujing
 * @Date：2015年4月29日 上午10:19:00
 * @version
 */
public enum ManageRoleTypeEnum {
	ALL(0, "全部"), IOTEK_ADMIN(1, "网校管理员"),COLLEGES_ADMIN(2, "院校管理员"),COMPANY_ADMIN(3,"企业管理员");

	private static final Map<Integer, ManageRoleTypeEnum> lookup = Maps.newHashMap();

	static {
		for (ManageRoleTypeEnum c : ManageRoleTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private ManageRoleTypeEnum(int value, String label) {
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

	public static ManageRoleTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
