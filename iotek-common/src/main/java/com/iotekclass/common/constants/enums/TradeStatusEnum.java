package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * ClassName: TradeStatusEnum
 * Description： 交易状态枚举
 * Author：王啸
 * Date：2014年1月9日 上午10:04:01
 * 
 * @version
 */
public enum TradeStatusEnum {

	TRADE_FINISHED("交易结束"), TRADE_SUCCESS("交易成功"), TRADE_FAILED("交易失败");
	private static final Map<Integer, TradeStatusEnum> lookup = Maps.newHashMap();
	static {
		int ordinal = 0;
		for (TradeStatusEnum c : TradeStatusEnum.values()) {
			lookup.put(ordinal, c);
			ordinal += 1;
		}
	}

	private String label;

	private TradeStatusEnum(String label) {
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

	public static TradeStatusEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
