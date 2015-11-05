package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 商品购买方式
 * 
 * @author ZengAihui
 * 
 */
public enum GoodsBuyTypeEnum {

	ALL("全部"), OFF_LINE("线下购买"), ON_LINE("线上购买"), 
	EXPER_COURSE("体验课"), EXPER_CARD("体验卡"),
	PROMOTE_TRIAL("推广试用"), PROMOTE_UPGRADE("促销升级"), NOT_PURCHASED("未购买");
	// 0:ALL,1:OFF_LINE,2:ON_LINE,3:PROMOTE_TRIAL,4:PROMOTE_UPGRADE

	private static final Map<Integer, GoodsBuyTypeEnum> lookup = Maps.newHashMap();

	static {
		for (GoodsBuyTypeEnum c : GoodsBuyTypeEnum.values()) {
			lookup.put(c.getId(), c);
		}
	}

	private String label;

	private GoodsBuyTypeEnum(String label) {
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

	public static GoodsBuyTypeEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
