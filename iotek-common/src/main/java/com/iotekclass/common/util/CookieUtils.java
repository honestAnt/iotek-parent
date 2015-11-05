package com.iotekclass.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iotekclass.common.constants.Constants;

public class CookieUtils {

	public static final int HOURS_IN_A_DAY = 24;
	public static final int SENCONDS_IN_A_MINUTE = 60;
	public static final int MINUTES_IN_AN_HOUR = 60;
	public static final int DAYS_IN_A_WEEK = 7;

	private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

	/**
	 * 
	 * Description: 设置cookie
	 * 
	 * @param response
	 * @param cookieName cookie名称
	 * @param expiry 过期时间秒数
	 * @param defaultValue 默认值
	 * @throws
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, int expiry, String defaultValue) {
		String cookieValue = null;
		try {
			cookieValue = URLEncoder.encode(defaultValue, Constants.DEFAULT_ENCODE_ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}

	/**
	 * 
	 * @description 设置cookie(含有domain)
	 * @author wangfengbao
	 * @date 2015年6月30日 上午11:50:50
	 *
	 * @param response
	 * @param cookieName
	 * @param expiry
	 * @param defaultValue
	 * @param domain
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, int expiry, String defaultValue, String domain) {
		String cookieValue = null;
		try {
			cookieValue = URLEncoder.encode(defaultValue, Constants.DEFAULT_ENCODE_ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setDomain(domain);
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}
	
	/**
	 * 
	 * @Description: 从cookie中查找键对应值
	 * @param request
	 * @param key
	 *            cookie键值
	 * @return
	 * @throws
	 */
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					String value = cookie.getValue();
					try {
						if (StringUtils.isNotBlank(value)) {
							value = URLDecoder.decode(cookie.getValue(), Constants.DEFAULT_ENCODE_ENCODING);
						}
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					return value;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Description: 删除cookie中键对应值
	 * @param request
	 * @param key
	 *            cookie键值
	 * @return
	 * @throws
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath("/");// 根据你创建cookie的路径进行填写
					response.addCookie(cookie);
					return;
				}
			}
		}
	}
}
