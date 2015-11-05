package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: FriendlyLinkTypeEnum   
 * @Description： 友情链接打开方式枚举  
 * @Author：hujing
 * @Date：2015年7月9日 下午2:53:26
 * @version
 */
public enum FriendlyLinkOpenWayEnum {
	
	ALL(0, "全部"), NEW_DIALOG(1, "新窗口打开"), NOW_DIALOG(2, "本窗口打开");

	private static final Map<Integer, FriendlyLinkOpenWayEnum> lookup = Maps.newHashMap();

	static {
		for (FriendlyLinkOpenWayEnum c : FriendlyLinkOpenWayEnum.values()) {
			lookup.put(c.getValue(), c);
		}
	}

	private int value;
	private String label;

	private FriendlyLinkOpenWayEnum(int value, String label) {
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

	public static FriendlyLinkOpenWayEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}

}
