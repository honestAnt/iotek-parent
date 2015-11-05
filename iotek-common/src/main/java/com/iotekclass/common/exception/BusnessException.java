/**
 * 
 */
package com.iotekclass.common.exception;

/**
 * ClassName: BusnessException
 * Description：业务异常类
 * Author：王啸
 * Date：2014年11月5日 下午1:41:35
 * 
 * @version
 */
public class BusnessException extends AppException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6786541501141713248L;

	/**
	 * Constructor for DataAccessException.
	 * 
	 * @param msg the detail message
	 */
	public BusnessException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for DataAccessException.
	 * 
	 * @param msg the detail message
	 * @param cause the root cause (usually from using a underlying
	 *            data access API such as JDBC)
	 */
	public BusnessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
