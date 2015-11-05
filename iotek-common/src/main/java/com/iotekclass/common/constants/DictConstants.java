package com.iotekclass.common.constants;

/**
 * 枚举类型的数据字典
 * 
 * @author tc
 * 
 */
public final class DictConstants {

	// 岗位类型
	public enum PositionType {

		ANDROID(1, "Android开发工程师"), JAVA(2, "java软件工程师"), C(3, "C/C++软件工程师");

		private int value;
		private String name;

		PositionType(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public int getValue() {
			return value;
		}
	}
}
