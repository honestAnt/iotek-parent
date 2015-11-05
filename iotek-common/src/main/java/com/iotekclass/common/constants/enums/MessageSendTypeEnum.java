package com.iotekclass.common.constants.enums;


public enum MessageSendTypeEnum {

	ALL("全部"), CAPTCHA("验证码");

	private String label;

	private MessageSendTypeEnum(String label) {
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
}
