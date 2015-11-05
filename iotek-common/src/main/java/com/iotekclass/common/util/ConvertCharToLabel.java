/**
 * 
 */
package com.iotekclass.common.util;

import java.util.List;

/**
 * @Description 转义常用的字符为标签
 * @author wangfengbao
 * @date 2015年3月18日 上午10:22:48
 *
 */
public class ConvertCharToLabel {

	/**
	 * 
	 * @description  转换字符串
	 * @author wangfengbao
	 * @date 2015年3月18日 上午10:28:05
	 *
	 * @param str
	 * @return
	 */
	public static String toLabel(String str) {
		
		return convert(str);
	}
	
	/**
	 * 
	 * @description 转换集合 
	 * @author wangfengbao
	 * @date 2015年3月18日 上午10:31:15
	 *
	 * @param list
	 * @return
	 */
	public static List<String> toLabel(List<String> list) {
		
		for (int i = 0;list != null && i < list.size(); i++) {
			list.set(i, convert(list.get(i)));
		}
		return list;
	}
	
	
	private static String convert(String str) {
		if (!StringUtil.isEmpty(str)) {
			str = str.replaceAll("&amp;", "&")
				.replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">")
				.replaceAll("&nbsp;", " ")
				.replaceAll("&quot;", "\"")
				.replaceAll("&apos;", "\'");
		}
		return str;
	}
}
