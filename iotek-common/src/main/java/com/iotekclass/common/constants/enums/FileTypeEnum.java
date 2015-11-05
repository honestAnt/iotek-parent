package com.iotekclass.common.constants.enums;


import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 
 * @ClassName: FileTypeEnum   
 * @Description：文件类型的枚举
 * @Author：zhangwenlei
 * @Date：2014年12月16日 上午9:48:47
 * @version
 */
public enum FileTypeEnum {

	ALL(0,""),TOOL(1, "工具"), DOCUMENT(2, "帮助文档"), CODE(3, "参考代码");

	private static final Map<Integer, FileTypeEnum> lookup = Maps.newHashMap();

	static {
		for (FileTypeEnum c : FileTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private FileTypeEnum(int value, String label) {
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

	public static FileTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
