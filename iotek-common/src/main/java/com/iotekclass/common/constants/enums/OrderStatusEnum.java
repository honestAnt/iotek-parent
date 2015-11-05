package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: OrderStatusEnum
 * Description： 订单状态枚举
 * Author：王啸
 * Date：2014年1月9日 上午10:04:01
 * 
 * @version
 */
public enum OrderStatusEnum {

	NONPAID(0,"未付款"), PAID(1,"已付款"), CANCELED(2,"已取消"), CLOSED(3,"已关闭"), FAILED(4,"失败");
	private static final Map<Integer, OrderStatusEnum> lookup = Maps.newHashMap();
	static {
		int ordinal = 0;
		for (OrderStatusEnum c : OrderStatusEnum.values()) {
			lookup.put(ordinal, c);
			ordinal += 1;
		}
	}
	
	private int value;
	private String label;

	private OrderStatusEnum(int value,String label) {
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

	public static OrderStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
