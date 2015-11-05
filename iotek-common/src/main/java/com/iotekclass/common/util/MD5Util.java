package com.iotekclass.common.util;

import java.security.MessageDigest;
import java.util.Date;

/**
 * The Class MD5Util.
 */
public class MD5Util {

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * M d5.
	 * 
	 * @param s
	 *            the s
	 * @return the string
	 */
	public static String md5(String s) {
		byte[] btInput = s.getBytes();
		return processMd5(btInput);
	}

	private static String processMd5(byte[] btInput) {
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[(byte0 >>> 4) & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Date now = new Date();
		long out1 = now.getTime();
		long out2 = now.getTime() / 1000;
		System.out.println("当前时间 毫秒 ：" + out1);
		System.out.println("当前加密  ：" + MD5Util.md5(out1 + "1ed520b"));
		System.out.println("当前时间 秒 ：" + out2);
		System.out.println("当前加密  ：" + MD5Util.md5(out2 + "1ed520b"));
		String email = "zhipengfei1988@126.com";
		String goodId = "42";
		System.out.println("当前用户 " + email + ",当前商品编号 ：" + goodId + ",当前时间 秒 ："
				+ out2);
		System.out.println("当前加密  ："
				+ MD5Util.md5(email + goodId + out2 + "1ed520b"));
		System.out.println("学习用户 " + email + ",当前时间 秒 ：" + out2);
		System.out.println("学习加密  ：" + MD5Util.md5(email + out2 + "1ed520b"));
		String freeemail = "test@12677.cn";
		System.out.println("免费用户 " + freeemail + ",当前时间 秒 ：" + out2);
		System.out.println("免费加密  ：" + MD5Util.md5(email + out2 + "1ed520b"));
		String tryemail = "zhipengfei1988@126.com";
		System.out.println("试用用户 " + tryemail + ",当前时间 秒 ：" + out2);
		System.out
				.println("试用加密  ：" + MD5Util.md5(tryemail + out2 + "1ed520b"));
		System.out.println("当前加密  ：" + MD5Util.md5("iotek"));
	}
}
