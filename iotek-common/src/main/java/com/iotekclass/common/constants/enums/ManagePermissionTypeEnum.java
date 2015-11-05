package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @Description 后台权限类型枚举 
 * @author wangfengbao
 * @date 2015年6月16日 上午10:56:23
 *
 */
public enum ManagePermissionTypeEnum {
	LOOK(1, "查看"), INSERT(2, "新增"),DELETE(3, "删除"),UPDATE(4,"修改"),
	SELECT(5,"查询"),IMPORT(6,"导入"),EXPORT(7,"导出"),
	DOWNLOAD(8,"下载"),UPLOAD(9,"上传");

	private static final Map<Integer, ManagePermissionTypeEnum> lookup = Maps.newHashMap();

	static {
		for (ManagePermissionTypeEnum c : ManagePermissionTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private ManagePermissionTypeEnum(int value, String label) {
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

	public static ManagePermissionTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
