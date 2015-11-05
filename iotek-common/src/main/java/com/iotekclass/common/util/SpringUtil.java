package com.iotekclass.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext factory;

	public static ApplicationContext getApplicationContext() {
		return factory;
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		
		factory = context;
	}
}