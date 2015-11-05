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
public class Config {

	private static PropertiesConfiguration config = null;
	
	static {
		try {
			config = new PropertiesConfiguration("config.properties");
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

	public static String getImgSuffix() {
		return getStringValue("img_suffix");
	}

	public static String getVideoSuffix() {
		return getStringValue("video_suffix");
	}

	public static String getFileSuffix() {
		return getStringValue("file_suffix");
	}

	public static String getQQSuffix() {
		return getStringValue("qq_url");
	}

	public static String getCacheServer() {
		return getStringValue("cache_server");
	}

	public static String getDevMode() {
		return getStringValue("dev_mode");
	}

	public static String getCasLogin() {
		return getStringValue("cas_login");
	}

	public static String getCasRegister() {
		return getStringValue("cas_register");
	}

	public static String getCasToRegister() {
		return getStringValue("cas_to_register");
	}

	public static String getCasServerUrl() {
		return getStringValue("cas_server");
	}

	public static String getCommunityUrl() {
		return getStringValue("community_url");
	}

	public static String getCrmHost() {
		return getStringValue("crm_host");
	}

	public static int getCrmPort() {
		return getIntValue("crm_port");
	}

	public static String getCrmVisitType() {
		return getStringValue("crm_visit_type");
	}

	public static String getCrmAddCustomerUrl() {
		return getStringValue("crm_addCustomerUrl");
	}

	public static String getCrmUpdateCustomerUrl() {
		return getStringValue("crm_updateCustomerUrl");
	}

	public static void main(String[] args) {
		System.out.println(getStringValue("img_suffix"));
	}

	public static String getCourseUrl() {
		return getStringValue("course_url");
	}

	public static String getExpeirenceUrl() {
		return getStringValue("expeirence_url");
	}

	public static String getIndexUrl() {
		return getStringValue("index_url");
	}

	public static String getIdeBasePath() {
		return getStringValue("ide_base_path");
	}

	/**
	 * @Description: 获取微信活动分享URL
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年4月7日 下午3:59:51
	 * @return 
	 * @throws
	 */
	public static String getActivityShareUrl() {
		return getStringValue("wechat_share_url");
	}
	
	/**
	 * @Description: 获取微信开发者AppID(应用ID)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年4月7日 下午3:59:51
	 * @return 
	 * @throws
	 */
	public static String getWechatAppid() {
		return getStringValue("wechat_appid");
	}
	
	/**
	 * @Description: 获取微信开发者AppSecret(应用密钥)
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年4月7日 下午3:59:51
	 * @return 
	 * @throws
	 */
	public static String getWechatSecret() {
		return getStringValue("wechat_secret");
	}
	
	/**
	 * @Description: 获取微信开发者应用token
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年4月7日 下午3:59:51
	 * @return 
	 * @throws
	 */
	public static String getWechatDevelopToken() {
		return getStringValue("wechat_develop_token");
	}
	
	/**
	 * @Description: 获取微信开发者签名前需要获取微信access_token
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年4月7日 下午3:59:51
	 * @return 
	 * @throws
	 */
	public static String getWechatAccessTokenUrl() {
		return getStringValue("wechat_access_token_url");
	}
	
	/**
	 * @Description: 获取微信开发者签名前需要获取jsapi_ticket
	 * @Author: sq.xiao@iotek.com.cn
	 * @Date 2015年4月7日 下午3:59:51
	 * @return 
	 * @throws
	 */
	public static String getWechatJsApiTicketUrl() {
		return getStringValue("wechat_jsapi_ticket_url");
	}
	
	
	public static String getPortalUrl() {
		return getStringValue("portal_url");
	}
	
	public static String getCommUrl() {
		return getStringValue("comm_url");
	}
	
	public static String getManageUrl() {
		return getStringValue("manage_url");
	}

	
	public static String getVideoHtmlSuffix() {
		return getStringValue("video_html_suffix");
	}

	
	/**
	 * 
	 * @description 专题活动jsp页面链接地址
	 * @author wangfengbao
	 * @date 2015年6月25日 下午4:12:12
	 *
	 * @return
	 */
	public static String getDissertationJspFilePath() {
		return getStringValue("dissertation_jsp_file_path");
	}
	
	/**
	 * 
	 * @description 
	 * @author wangfengbao
	 * @date 2015年6月30日 上午11:53:16
	 *
	 * @return
	 */
	public static String getForwardUrlDomain() {
		return getStringValue("forward_url_domain");
	}
	public static String getLiveServerAddress() {
		return getStringValue("serverAddress");
	}
	public static String getLivePartnerId() {
		return getStringValue("partnerId");
	}
	public static String getLiveAppKey() {
		return getStringValue("appKey");
	}
	public static String getYouCaiUrl() {
		return getStringValue("youcai_url");
	}
	/**
	 * 
	 * @Description: 添加竞赛链接地址
	 * @Author：王凤宝
	 * @Date：2015年7月31日 下午2:34:20
	 * @return
	 * @throws
	 */
	public static String getCompetitionUrl() {
		return getStringValue("competition_url");
	}

	/**
	 *
	 * @Description: 服务器地址
	 * @Author：Joshua
	 * @Date：2015年10月28日09:29:05
	 * @return
	 * @throws
	 */
	public static String getIdeIpAddr() {
	  return getStringValue("ide_ip_add");
	}

	/**
	 *
	 * @Description: 用户名
	 * @Author：Joshua
	 * @Date：2015年10月28日09:29:05
	 * @return
	 * @throws
	 */
	public static String getIdeUserName() {
	  return getStringValue("ide_user_name");
	}
	/**
	 *
	 * @Description: 密码
	 * @Author：Joshua
	 * @Date：2015年10月28日09:29:05
	 * @return
	 * @throws
	 */
	public static String getIdePassword() {
	  return getStringValue("ide_password");
	}
	/**
	 *
	 * @Description: 编码
	 * @Author：Joshua
	 * @Date：2015年10月28日09:29:05
	 * @return
	 * @throws
	 */
	public static String getIdeCharset() {
	  return getStringValue("ide_charset");
	}
	/**
	 *
	 * @Description: 需要执行的脚本名称
	 * @Author：Joshua
	 * @Date：2015年10月28日09:29:05
	 * @return
	 * @throws
	 */
	public static String getIdeScriptName() {
	  return getStringValue("ide_script_name");
	}

}
