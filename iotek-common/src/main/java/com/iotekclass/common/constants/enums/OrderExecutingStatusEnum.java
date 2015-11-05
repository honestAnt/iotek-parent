package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: OrderStatusEnum
 * Description： 订单执行状态枚举
 * Author：王啸
 * Date：2014年1月9日 上午10:04:01
 * 
 * @version
 */
public enum OrderExecutingStatusEnum {

	FINISHED("结束"), PROCESSING("进行中");
	private static final Map<Integer, OrderExecutingStatusEnum> lookup = Maps.newHashMap();
	static {
		int ordinal = 0;
		for (OrderExecutingStatusEnum c : OrderExecutingStatusEnum.values()) {
			lookup.put(ordinal, c);
			ordinal += 1;
		}
	}

	private String label;

	private OrderExecutingStatusEnum(String label) {
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

	public static OrderExecutingStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
