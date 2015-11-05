/**
 * 
 */
package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 
 * @ClassName: HomeBannerEnableEnum   
 * @Description： 首页广告状态枚举   
 * @Author：王凤宝
 * @Date：2015年7月29日 下午1:30:42
 * @version
 */
public enum HomeBannerEnableEnum {

	ALL(0, "全部"), UNAVAILABLE(1, "否"), AVAILABLE(2, "是");

	private static final Map<Integer, HomeBannerEnableEnum> lookup = Maps.newHashMap();

	static {
		for (HomeBannerEnableEnum c : HomeBannerEnableEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private HomeBannerEnableEnum(int value, String label) {
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

	public static HomeBannerEnableEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
