package com.iotekclass.webcommon.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.common.cache.redis.RedisClientTemplate;
import com.iotekclass.common.constants.UserConstants;
import org.springframework.stereotype.Component;

/**
 * 
 * ClassName: CustomSessionListener
 * Description： 自定义session监听器,解决用户并发登录
 * Author：王啸
 * Date：2014年9月22日 上午11:02:40
 * 
 * @version
 */
@Component
public class CustomSessionListener implements HttpSessionListener {

	private static Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	/**
	 * 防止redis中键值重复,保证本系统唯一
	 */
	private static final String system_label = "_cms";
	// 无参构造方法
	public CustomSessionListener() {
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.debug("创建用户session:" + new Date());
	}

	/**
	 * 
	 * @Description: session销毁,从缓存中移除用户信息
	 * @param se
	 * @throws
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.debug("销毁用户session:" + new Date());
		// 移除全局用户信息
		HttpSession session = se.getSession();
		ManageAccount manageAccount = (ManageAccount) session.getAttribute(UserConstants.LOGIN_USER_KEY);
		if ((manageAccount != null) && redisClientTemplate.exists(String.valueOf(manageAccount.getId())+system_label)) {
			redisClientTemplate.del(String.valueOf(manageAccount.getId()));
		}
		if (redisClientTemplate.exists(String.valueOf(session.getId())+system_label)) {
			redisClientTemplate.del(String.valueOf(session.getId())+system_label);
		}

	}

}
