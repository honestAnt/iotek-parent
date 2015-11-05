package com.iotekclass.common.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IpUtils {

	// 禁止继承
	private IpUtils() {

	}

	private static Logger logger = LoggerFactory.getLogger(IpUtils.class);

	private static final int radix = 16;

	private static final int ipSegments = 4;// IP段数
	/**
	 * IP字符串正则表达式
	 */
	private static final String ipregex = "^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$";

	/**
	 * 默认IP
	 */
	public static final String DEFAULT_IP = "0.0.0.0";

	/**
	 * 获取真实IP:
	 * 先取
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if ((null != ip) && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if ((null != ip) && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
			// get first ip from proxy ip
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	/**
	 * 判断是否是合法的IP字符串
	 */
	public static boolean isIPStr(String ipStr) {
		return ipStr.matches(ipregex);
	}

	/**
	 * 将127.0.0.1形式的IP字符串转换为保存入数据库的10进制数
	 */
	public static Long str2dec(String ipStr) {
		if ((ipStr == null) || "".equals(ipStr)) {
			logger.warn("传入的IP字符串为空");
			return null;
		}
		// 判断IP字符串是否合法
		if (!DEFAULT_IP.equals(ipStr) && !isIPStr(ipStr)) {
			logger.error("\"" + ipStr + "\"不是一个合法的IP字符串!");
			throw new IllegalArgumentException("\"" + ipStr + "\"不是一个合法的IP字符串!");
		}
		String[] arr = ipStr.split("\\.");
		StringBuffer hexStr = new StringBuffer();
		for (String element : arr) {
			String hex = Long.toHexString(Long.parseLong(element));
			// 补零位
			if (hex.length() < 2) {
				hex = "0" + hex;
			}
			hexStr.append(hex);
		}
		return Long.valueOf(hexStr.toString(), radix);
	}

	/**
	 * 将数据库取出的10进制数形式的IP转换为127.0.0.1形式的IP字符串
	 */
	public static String dec2str(Long ip) {
		if (ip == null) {
			logger.warn("传入的IP为空");
			return null;
		}
		String hexStr = Long.toHexString(ip.longValue());
		// 补零位
		for (int i = hexStr.length(); i < 8; i++) {
			hexStr = "0" + hexStr;
		}
		StringBuffer ipStrB = new StringBuffer();
		for (int i = 0; i < ipSegments; i++) {
			ipStrB.append(".").append(Integer.parseInt(hexStr.substring(i * 2, (i + 1) * 2), radix));
		}
		String ipStr = ipStrB.toString();
		ipStr = ipStr.substring(1);
		return ipStr;
	}
}
