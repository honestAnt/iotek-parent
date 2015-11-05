/**
 * 
 */
package com.iotekclass.common.util;

/**
 * ClassName: OrderUtil
 * Description：订单工具类
 * Author：王啸
 * Date：2014年1月9日 下午2:12:13
 * 
 * @version
 */
public class OrderUtil {
	/**
	 * 
	 * @Description: 返回一个订单号
	 * @param orderCode
	 * @return
	 * @throws
	 */
	public static String generateMixOrderCode(String orderCode) {
		StringBuilder sb = new StringBuilder();
		// 前四位商品编号
		//sb.append(orderCode);
		// 当前日期 (六位字符串)
		sb.append(DateUtil.getCurrentDateSString());
		// 返回一个6位的随机纯数字字符串
		String generateMixNumber = RandomUtil.generateMixNumber(6);
		sb.append(generateMixNumber);
		return sb.toString();
	}

	/**
	 * 
	 * @Description: 将价格转为分,并以int计入,如5893-->589300 , 56.33-->5633
	 * @param orderCode
	 * @return
	 * @throws
	 */
	public static int exchangeToPriceData(int price) {
		StringBuilder sb = new StringBuilder();
		sb.append(price).append("00");
		return Integer.parseInt(sb.toString());
	}

	/**
	 * 
	 * @Description: 将价格转为元,并以int计入,如5893 <--589300 , 56.33-->5633
	 * @param orderCode
	 * @return
	 * @throws
	 */
	public static int fromDatatoPrice(int price) {
		StringBuilder sb = new StringBuilder();
		sb.append(price);
		return Integer.parseInt(sb.toString().substring(0, sb.toString().length() - 2));
	}
}
