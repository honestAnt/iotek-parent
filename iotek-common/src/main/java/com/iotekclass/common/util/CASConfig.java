package com.iotekclass.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 
 * ClassName: Config
 * Description： 配置文件
 * Author：王啸
 * Date：2014年9月17日 下午2:39:22
 * 
 * @version
 */
public class CASConfig {

	private static PropertiesConfiguration config = null;
	static {
		try {
			config = new PropertiesConfiguration("cas.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static int getIntValue(String key) {
		return config.getInt(key);
	}

	public static double getDoubleValue(String key) {
		return config.getDouble(key);
	}

	public static float getFloatValue(String key) {
		return config.getFloat(key);
	}

	public static String getStringValue(String key) {
		return config.getString(key);
	}

	/*
	 * public static String getStringValue(String values){
	 * String value = config.getString(values);
	 * if(value == null){
	 * return value;
	 * }
	 * try {
	 * value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
	 * } catch (UnsupportedEncodingException e) {
	 * e.printStackTrace();
	 * }
	 * return value;
	 * }
	 */

	public static String[] getStringArrayValues(String key) {
		return config.getStringArray(key);
	}

	/** 下面是定制的方法 **/

	public static String getCASLoginUrl() {
		return getStringValue("cas.loginUrl");
	}

	public static String getCASlogoutUrl() {
		return getStringValue("cas.redirectUrl");
	}

}
