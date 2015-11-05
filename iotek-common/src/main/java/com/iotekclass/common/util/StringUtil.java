package com.iotekclass.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class StringUtil {
 
    /**
     * 正则表达式：20位以内数字和字母
     */
    public static final String REGEX_CODE= "^[a-zA-Z0-9]{1,20}$";
    /**
     * 正则表达式：5-15位数字
     */
    public static final String REGEX_NUMBER= "^[0-9]{5,15}$";
    /**
     * 正则表达式：年份
     */
    public static final String REGEX_YEAR= "^[0-9]{4}$";
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[34578][0-9]{9}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,10}$";
 
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
 
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
 
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
 

	public static String intArr2Str(int[] array, String separator) {
		String result = null;
		if ((null != array) && (array.length > 0)) {
			StringBuffer sb = new StringBuffer();
			for (int i : array) {
				sb.append(String.valueOf(i));
				sb.append(separator);
			}
			result = sb.toString();
			int index = result.lastIndexOf(separator);
			if (index > 0) {
				result = result.substring(0, index);
			}
		}
		return result;
	}

	/**
	 * 剪切字符串
	 * 
	 * @param remark
	 *            文本
	 * @param limit
	 *            字符限制
	 * @return
	 */
	public static String cutOutString(String remark, int limit) {
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(remark) && (remark.length() > limit)) {
			buffer.append(StringUtils.left(remark, limit)).append("...");
		} else {
			buffer.append(remark);
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @Description: 处理查询关键字
	 * @param keyWords
	 * @return
	 * @throws
	 */
	public static String handleKeyWords(String keyWords) {
		String keyWords2 = null;
		if (StringUtils.isNotBlank(keyWords)) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("'%");
			stringBuilder.append(keyWords.trim());
			stringBuilder.append("%'");
			keyWords2 = stringBuilder.toString();
		}
		return keyWords2;
	}

	public static boolean isEmpty(String text) {
		return (text == null) || text.isEmpty();
	}

	/**
	 * @Description: 生成密码
	 * @return
	 * @throws
	 */
	public static String generatePassword() {
		return "iotek123";
	}

	/**
	 * @Description: 生成当天年月日的字符串
	 * @return
	 * @throws
	 */
	public static String generateTodayString() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String month_ = String.valueOf(month);
		if (month < 10) {
			month_ = "0" + month;
		}
		int day = c.get(Calendar.DAY_OF_MONTH);
		String day_ = String.valueOf(day);
		if (day < 10) {
			day_ = "0" + day;
		}
		return year + "-" + month_ + "-" + day_;
	}

	/**
	 * 格式化HTML文本
	 * 
	 * @param content
	 * @return
	 */
	public static String html(String content) {
		if (content == null) {
			return "";
		}
		String html = content;
		// html = StringUtils.replace(html, "'", "&apos;");
		html = StringUtils.replace(html, "\"", "&quot;");
		html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
		html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格
		html = StringUtils.replace(html, "<", "&lt;");
		html = StringUtils.replace(html, ">", "&gt;");
		return html;
	}

	/**
	 * 格式化HTML文本
	 * 
	 * @param content
	 * @return
	 */
	public static String htmlspecialchars(String str) {
		if (str == null) {
			return "";
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		return suffix.toLowerCase();
	}

	/**
	 * 根据分隔符,返回list
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<String> getListByString(String string, String separator) {
		String[] strings = string.trim().split(separator);
		ArrayList<String> list = Lists.newArrayList();
		for (String string2 : strings) {
			list.add((string2));
		}
		return list;
	}

	    /**
	     * 校验用户名
	     * 
	     * @param username
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isUsername(String code) {
	        return Pattern.matches(REGEX_CODE, code);
	    }
	 
	    /**
	     * 校验手机号
	     * 
	     * @param mobile
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isMobile(String mobile) {
	    	if("".equals(mobile)||mobile==null){
	    		return true;
	    	}else{
	    		return Pattern.matches(REGEX_MOBILE, mobile);
	    	}
	        
	    }
	 
	    /**
	     * 校验邮箱
	     * 
	     * @param email
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isEmail(String email) {
	    	if("".equals(email)||email==null){
	    		return true;
	    	}else{
	    		return Pattern.matches(REGEX_EMAIL, email);
	    	}
	        
	    }
	 
	    /**
	     * 校验汉字
	     * 
	     * @param chinese
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isChinese(String chinese) {
	        return Pattern.matches(REGEX_CHINESE, chinese);
	    }
	 
	    /**
	     * 校验QQ
	     * 
	     * @param QQ
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isQQ(String qqCode) {
	    	if("".equals(qqCode)||qqCode==null){
	    		return true;
	    	}else{
	    		return Pattern.matches(REGEX_NUMBER, qqCode);
	    	}
	        
	    }
	 
	    /**
	     * 校验年份
	     * 
	     * @param url
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isYear(String year) {
	        return Pattern.matches(REGEX_YEAR, year);
	    }
	 
	    /**
	     * 校验IP地址
	     * 
	     * @param ipAddr
	     * @return
	     */
	    public static boolean isIPAddr(String ipAddr) {
	        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	    }
}
