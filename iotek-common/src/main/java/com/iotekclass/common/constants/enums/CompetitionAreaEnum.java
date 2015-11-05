package com.iotekclass.common.constants.enums;


import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 
 * @ClassName: CompetitionAreaEnum
 * @Description： 竞赛项目参赛区域的枚举
 * @Author：Joshua
 * @Date：2015年8月19日09:49:28
 * @version
 */
public enum CompetitionAreaEnum {

    /**
     1.华东赛区
     2.华南赛区
     3.华中赛区
     4.西北赛区
     5.西南赛区
     6.东北赛区
     7.港澳台赛区
	 8.华北赛区
	 9.北斗专赛区
     */
	ALL(-1,"全部"),
    EASTCHINA(1, "华东赛区"),
    SOUTHCHINA(2, "华南赛区"),
    CENTRALCHINA(3,"华中赛区"),
    NORTHWEST(4,"西北赛区"),
    SOURTHWEST(5,"西南赛区"),
    NORTHEAST(6,"东北赛区"),
    HKMACAO(7,"港澳台赛区"),
  	NORTHCHINA(8,"华北赛区"),
  	BEIDOU(9,"北斗专赛区");

	private static final Map<Integer, CompetitionAreaEnum> lookup = Maps.newHashMap();

	static {
		for (CompetitionAreaEnum c : CompetitionAreaEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private CompetitionAreaEnum(int value, String label) {
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

	public static CompetitionAreaEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
