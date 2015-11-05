package com.iotekclass.service;

/**
 * 
 * ClassName: ServiceException
 * Description： Service层公用的Exception.继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * Author：ZengAihui
 * Date：2013年8月26日 下午1:19:12
 * 
 * @version
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
