/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: MarvellApplicantIdentityEnum   
 * @Description： 美满活动队员身份枚举类   
 * @Author：hujing
 * @Date：2015年5月14日 下午3:55:58
 * @version
 */
public enum MarvellApplicantIdentityEnum {
	ALL(0,"all"),TEAM_LEADER(1,"队长"),TEAM_MEMBER(2,"队员");
	
	private static final Map<Integer, MarvellApplicantIdentityEnum> lookup = Maps.newHashMap();

	static {
		for (MarvellApplicantIdentityEnum c : MarvellApplicantIdentityEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private MarvellApplicantIdentityEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

	public static MarvellApplicantIdentityEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
