package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: UserAuthentificationEnum   
 * @Description：用户身份验证状态
 * @Author：zhangwenlei
 * @Date：2014年11月4日 上午11:16:18
 * @version
 */
public enum UserAuthentificationEnum {
	PROGRESS(0,"正在审核中"),PASS(1,"已通过"),NO_PASS(2,"未通过 ");
	private int value;
	private String label;
	
	private UserAuthentificationEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, UserAuthentificationEnum> lookup = Maps.newHashMap();
	
	static {
		for(UserAuthentificationEnum c : UserAuthentificationEnum.values()) {
			lookup.put(c.getValue(),c);
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
	
	public int getId() {
		return this.ordinal();
	}
	
	public String getName() {
		return this.name();
	}
	
	public static UserAuthentificationEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
