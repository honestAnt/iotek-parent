package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName GoodsStatusEnum.java
 * @Description 操作资源枚举-->后台管理系统-->系统管理-->操作资源
 * @Author sq.xiao@iotek.com.cn
 * @Date 2014年12月19日 下午3:48:29
 * @Version 1.0
 */

public enum OperationResourceTypeEnum {
	
	ALL(0,""),
	ADD(1,"增加"), 
	REMOVE(2,"删除"), 
	MODIFY (3,"修改"), 
	QUERY(4,"查询"),
	AUTHORIZATION(5,"授权"),
	IMPORT_UPLOAD(6,"导入导出");
	
	private int value;

	private String label;

	private OperationResourceTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	private static final Map<Integer, OperationResourceTypeEnum> lookup = Maps.newHashMap();

	static {
		for (OperationResourceTypeEnum c : OperationResourceTypeEnum.values()) {
			lookup.put(c.getValue(), c);
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
	
	public static OperationResourceTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
