package com.iotekclass.common.tenpay.config;

import com.iotekclass.common.util.Config;

/**
 * 
 * ClassName: TenpayConfig   
 * Description： TODO(这里用一句话描述这个类的作用)   
 * Author：王啸
 * Date：2015年6月24日 下午2:16:39
 * @version
 */

public class TenpayConfig {

	// 合作身份者ID
	public static String partner = Config.getStringValue("tenpay_partner");
	// 商户的私钥
	public static String key = Config.getStringValue("tenpay_key");

}
