/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 
 * @ClassName: InformationStatusEnum
 * @Description： cms资讯状态
 * @Author：Joshua
 * @Date：2015年7月21日 下午2:18:46
 * @version
 */
public enum InformationStatusEnum {

	 APPROVING(0, "待审核"), PUBLISHED(1, "已发布"), REJECTION(2, "退回");

	private static final Map<Integer, InformationStatusEnum> lookup = Maps.newHashMap();

	static {
		for (InformationStatusEnum c : InformationStatusEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private InformationStatusEnum(int value, String label) {
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

	public static InformationStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
