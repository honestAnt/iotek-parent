package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: OrderTypeEnum   
 * Description： TODO(这里用一句话描述这个类的作用)   
 * Author：王啸
 * Date：2015年6月24日 下午5:13:34
 * @version
 */
public enum OrderTypeEnum {

	NON_PAY(1, "零元购买"), ALI_PAY(1, "支付宝"), TEN_PAY(2, "财付通");
	private static final Map<Integer, OrderTypeEnum> lookup = Maps.newHashMap();
	static {
		int ordinal = 0;
		for (OrderTypeEnum c : OrderTypeEnum.values()) {
			lookup.put(ordinal, c);
			ordinal += 1;
		}
	}

	private int value;
	private String label;

	private OrderTypeEnum(int value, String label) {
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

	public static OrderTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
