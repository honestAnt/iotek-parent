package com.iotekclass.cms.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.common.constants.UserConstants;
import com.iotekclass.common.util.JsonMapper;
import com.iotekclass.common.util.StringUtil;

/**
 * ClassName: BaseController   
 * Description： 基础controller父类
 * Author：王啸
 * Date：2015年5月20日 下午4:14:42
 * @version
*/
public class BaseController {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4914179626292891061L;

	/**
	 * 日志接口
	 */
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * json接口
	 */
	protected static final JsonMapper binder = JsonMapper.nonDefaultMapper();

	protected String jsonString;
	
	/**
	 * 
	 * @Description: 获取登录用户id
	 * @Author：hujing
	 * @Date：2015年7月15日 上午9:19:18
	 * @param request
	 * @return
	 * @throws
	 */
	protected int getLoginUserId(HttpServletRequest request) {
		Integer userId = (Integer) request.getSession().getAttribute(UserConstants.LOGIN_USER_ID_KEY);
		return ((null != userId) ? userId : 0);
	} 
	
	/**
	 * 
	 * @Description: 获取登录用户详细信息
	 * @Author：hujing
	 * @Date：2015年7月15日 上午9:21:35
	 * @param request
	 * @return
	 * @throws
	 */
	protected ManageAccount getLoginUser(HttpServletRequest request) {
		return (ManageAccount) request.getSession().getAttribute(UserConstants.LOGIN_USER_KEY);
	}
	
	/**
	 * 
	 * @Description: 是否已登录
	 * @return
	 * @throws
	 */
	public boolean isLogin() {
		Subject subject = SecurityUtils.getSubject();
		return (subject.isAuthenticated() && (subject.getPrincipal() != null));
	}
	
	/**
	 * 
	 * @Description: 转换multipartfile成file
	 * @Author：hujing
	 * @Date：2015年7月15日 上午10:00:10
	 * @param file
	 * @return
	 * @throws
	 */
	protected File convertMultipartFileToIoFile(MultipartFile file) {
		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
		DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
		File f = fi.getStoreLocation();
		//临时文件不存在时创建临时文件
		if (!f.exists()) { 
			OutputStream os;
			try {
				os = new FileOutputStream(f);
				os.flush();
				os.write(file.getBytes());
				os.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}
	
	/**
	 * 把对象转换成键值对的json格式数据
	 * @param obj
	 */
	protected static String objToJsonData(String key, Object obj) {
		if (obj != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(key, obj);
			return binder.toJson(map);
		}
		return "";
	}
	
	/**
	 * 简单转换字符类型
	 * @param str
	 * @return
	 */
	protected String convertStr(String str) {
		if (!StringUtil.isEmpty(str)) {
			return str.trim();
		}
		return "";
	}
	
	/**
	 * 简单转换数字类型
	 * @param str
	 * @return
	 */
	protected int convertInteger(String str) {
		if (!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str.trim());
		}
		return 0;
	}
}
