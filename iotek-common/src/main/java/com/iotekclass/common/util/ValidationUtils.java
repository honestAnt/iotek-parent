/**
 * 
 */
package com.iotekclass.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZengAihui
 * 
 */
public final class ValidationUtils {

	/**
	 * 验证邮箱格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validEmail(String email) {
		String patternFormat = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(patternFormat);
		Matcher mat = pattern.matcher(email);
		return mat.matches();
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean validMobile(String mobile) {
		String patternFormat = "^((13[0-9])|(147)|(15[0-9])|(18[0-9]))\\d{8}$";
		Pattern pattern = Pattern.compile(patternFormat);
		Matcher mat = pattern.matcher(mobile);
		return mat.matches();
	}
}
