package com.iotekclass.common.constants.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: UploadResultEnum   
 * @Description： 文件上传状态   
 * @Author：zhangwenlei
 * @Date：2015年7月2日 上午10:02:27
 * @version
 */
public enum UploadResultEnum {
	PROGRESS(0,"上传中"),PASS(1,"成功"),NO_PASS(2,"失败 ");
	private int value;
	private String label;
	
	private UploadResultEnum(int value,String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, UploadResultEnum> lookup = Maps.newHashMap();
	
	static {
		for(UploadResultEnum c : UploadResultEnum.values()) {
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
	
	public static UploadResultEnum fromOrdinal(int ordinal) {
		return lookup.get(ordinal);
	}
}
