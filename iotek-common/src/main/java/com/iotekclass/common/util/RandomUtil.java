package com.iotekclass.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 
 * 随机工具类
 * 
 * @author ZengAihui
 * 
 */
public final class RandomUtil {

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String numberChar = "0123456789";

	private static final Logger logger = LoggerFactory.getLogger(RandomUtil.class);

	private RandomUtil() {

	}

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字).
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	/***
	 * 返回一个UUID.
	 * 
	 * @return 随机UUID字符串
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.replaceAll("-", "");// 去掉“-”符号
	}

	/**
	 * 返回定长的UUID
	 * 
	 * @param length
	 * @return
	 */
	public static String getFixedUUID(final int length) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");// 去掉“-”符号
		if (length < uuid.length()) {
			int start = uuid.length() - length;
			return uuid.substring(start).toUpperCase();
		} else {
			return uuid.toUpperCase();
		}
	}

	/**
	 * 返回定长的12位不重复的数字集合(带有隐藏批次)
	 * 
	 * @param length
	 * @return
	 */
	public static ArrayList<String> getFixedCode(final int count, final int cardType, final String seq) {
		HashSet<String> codes = Sets.newHashSet();
		// 遍历数量
		for (int i = 0; i < count; i++) {
			StringBuilder sb = new StringBuilder();
			// 第1,2位随机产生数字
			sb.append(generateMixNumber(2));
			// 第3,4位为序列前两位
			sb.append(seq.substring(0, 2));
			// 第5,6随机产生
			sb.append(generateMixNumber(2));
			// 第7位输激活类型位
			sb.append(cardType);
			// 第8,9位随机产生
			sb.append(generateMixNumber(2));
			// 第10,11位序列后两位
			sb.append(seq.substring(2, 4));
			// 第12位随机产生
			sb.append(generateMixNumber(1));
			codes.add(sb.toString());
		}
		if (codes.size() != count) {
			return getFixedCode(count, cardType, seq);
		}
		ArrayList<String> list = Lists.newArrayList();
		list.clear();// 清空list，
		list.addAll(codes);//
		return list;
	}

	/***
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母).
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/***
	 * 返回一个定长的随机纯数字字符串(只包含数字).
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixNumber(int length) {
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母).
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母).
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串 .
	 * 
	 * @param length 字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0.
	 * 
	 * @param num 数字
	 * @param fixdlenth 字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuilder sb = new StringBuilder();
		try {
			String strNum = String.valueOf(num);
			if ((fixdlenth - strNum.length()) >= 0) {
				sb.append(generateZeroString(fixdlenth - strNum.length()));
			}
			sb.append(strNum);
		} catch (RuntimeException e) {
			logger.error("Change Number: " + num + " to String ,but it's length:" + fixdlenth + "have  exception!", e);
		}
		return sb.toString();
	}

	/**
	 * 生成随机码(定长).
	 * 
	 * @param length
	 * @param getLength
	 */
	public static String generateRandomNumber(final int length, int getLength) {
		String[] r = new String[getLength];
		for (int i = 0; i < r.length; i++) {
			int v = new SecureRandom().nextInt(length) + 1;
			int g = i;
			while (g > 0) {
				if (r[g - 1].equals(String.valueOf(v))) {
					v = new SecureRandom().nextInt(length) + 1;
				} else {
					g--;
				}
			}
			r[i] = String.valueOf(v);
		}
		StringBuilder ss = new StringBuilder();
		for (String f : r) {
			ss.append(f);
		}
		return ss.toString();
	}

	// public static void main(String[] args) {
	// for (int i = 0; i < 10; i++) {
	// System.out.println("UUID:" + getUUID() + "/--------/FixedUUID:" + getFixedUUID(16));
	// }
	// }

}
