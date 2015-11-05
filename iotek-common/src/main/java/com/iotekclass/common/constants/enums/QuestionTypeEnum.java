/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @ClassName: QuestionEnum   
 * @Description： 问题社区问题类型枚举
 * @Author：GuangChen
 * @Date：2015年1月16日 下午3:44:02
 * @version
 */
public enum QuestionTypeEnum {
	QUESTIONTYPE(-1,"问题类型"),ANDROID(1,"安卓"),CPLUS(2,"C++"),C(3,"C"),JAVAEE(4,"JavaEE"),LINUX(5,"Linux"),LSD(6,"LSD"),JAVA(7,"Java"),OTHERS(8,"Others"),MATCH(9,"移动互联网大赛");
	private static final Map<Integer, QuestionTypeEnum> lookup = Maps.newHashMap();

	static {
		for (QuestionTypeEnum c : QuestionTypeEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private QuestionTypeEnum(int value, String label) {
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

	public static QuestionTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}

