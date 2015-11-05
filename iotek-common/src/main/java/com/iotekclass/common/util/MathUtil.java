package com.iotekclass.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 数字转换工具类
 * 
 * @author ZengAihui
 * 
 */
public final class MathUtil {

	private MathUtil() {
	}

	private static final Logger logger = LoggerFactory.getLogger(MathUtil.class);

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	 * 
	 * @param v1
	 * @param v2
	 * @param scale 表示需要精确到小数点以后几位
	 * @param roundMode 表示用户指定的舍入模式
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2, int scale, int roundMode) {
		try {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.divide(b2, scale, roundMode).toString();
		} catch (IllegalArgumentException e) {
			logger.error("小数点保留位至少应大于等于0", e);
			return null;
		}
	}

	/**
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @param round_mode 指定的舍入模式
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale, int round_mode) {
		try {
			BigDecimal b = new BigDecimal(Double.toString(v));
			return b.setScale(scale, round_mode).doubleValue();
		} catch (IllegalArgumentException e) {
			logger.error("小数点保留位至少应大于等于0", e);
			return 0;
		}
	}

	/**
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果，以字符串格式返回
	 */
	public static String round(String v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @param round_mode 指定的舍入模式
	 * @return 四舍五入后的结果，以字符串格式返回
	 */
	public static String round(String v, int scale, int round_mode) {
		try {
			BigDecimal b = new BigDecimal(v);
			return b.setScale(scale, round_mode).toString();
		} catch (IllegalArgumentException e) {
			logger.error("小数点保留位至少应大于等于0", e);
			return null;
		}
	}

	/**
	 * Double转百分比
	 * 
	 * @param number
	 * @param newValue 保留百分比小数点多少位
	 * @return
	 */
	public static String formatPercent(double number, int newValue) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(newValue);
		return nf.format(number);
	}

	/**
	 * 百分比转Double
	 * 
	 * @param value
	 * @return 返回double
	 */
	public static double formatNumber(String value) {
		double n = 0;
		NumberFormat nf = NumberFormat.getPercentInstance();
		try {
			n = nf.parse(value).doubleValue();
		} catch (ParseException e) {
			logger.error("格式转换错误", e);
			return 0;
		}
		return n;
	}
}
