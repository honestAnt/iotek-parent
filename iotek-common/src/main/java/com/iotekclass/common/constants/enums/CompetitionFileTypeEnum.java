package com.iotekclass.common.constants.enums;


import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: CompetitionFileTypeEnum   
 * @Description： 竞赛项目文件类型的枚举   
 * @Author：Joshua
 * @Date：2015年7月29日 上午10:12:50
 * @version
 */
public enum CompetitionFileTypeEnum {

	ALL(0,"全部"),IOTEK(1, "职坐标资料"), OFFICIAL(2, "官方资料");

	private static final Map<Integer, CompetitionFileTypeEnum> lookup = Maps.newHashMap();

	static {
		for (CompetitionFileTypeEnum c : CompetitionFileTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private CompetitionFileTypeEnum(int value, String label) {
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

	public static CompetitionFileTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
