/**
 * 
 */
package com.iotekclass.common.util;

/**
 * ClassName: NumberUtils
 * Description：数字处理函数类
 * Author：ZengAihui
 * Date：2013年9月11日 下午5:11:11
 * 
 * @version
 */

public final class NumberUtils {

	private NumberUtils() {
	}

	private final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999,
			Integer.MAX_VALUE };

	/**
	 * 
	 * Description: 数字转补零字符串
	 * 
	 * @param number
	 * @param size 代表长度
	 * @return
	 * @throws
	 */
	public static String addZero(int number, int size) {
		// d 表示正整数
		String str = String.format("%0" + size + "d", number);
		return str;
	}

	/**
	 * 获取当前整数的位数
	 * 
	 * @param x
	 * @return
	 */
	public static int sizeOfInt(int x) {
		for (int i = 0;; i++) {
			if (x <= sizeTable[i]) {
				return i + 1;
			}
		}
	}

}
