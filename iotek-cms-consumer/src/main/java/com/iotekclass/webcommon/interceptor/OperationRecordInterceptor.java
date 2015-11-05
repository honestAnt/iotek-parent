/**
 * 
 */
package com.iotekclass.webcommon.interceptor;


import com.alibaba.dubbo.config.annotation.Reference;
import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.model.permission.ManageOperationRecord;
import com.iotekclass.cms.model.permission.OperationResource;
import com.iotekclass.cms.service.permission.ManageOperationRecordService;
import com.iotekclass.cms.service.permission.OperationResourceService;
import com.iotekclass.common.constants.UserConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: OperationRecordInterceptor   
 * @Description： 操作记录拦截器
 * @Author：GuangChen
 * @Date：2015年5月14日 下午2:39:47
 * @version
 */
@Component
public class OperationRecordInterceptor implements HandlerInterceptor {

	@Reference(version = "1.0.0")
	OperationResourceService operationResourceService;

	@Reference(version = "1.0.0")
	ManageOperationRecordService manageOperationRecordService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//获取请求action
		String url = request.getRequestURI().trim().replace("/cms/", "/");
		
		//排除权限管理的action url
		if(!url.equals("/systemManage/validAccountPermission")){
			
			//通过url到资源表匹配对应操作
			OperationResource operationResource = operationResourceService.getByUrl(url);
			if(null!=operationResource){//判断资源表中是否存在记录，如果存在保存操作记录
				String params = "";//action参数
				Map parameterMap = request.getParameterMap();
				Set<String> keySets = parameterMap.keySet();
				for (String key : keySets) {
					 Object value = request.getParameter(key);
					 params += key+"="+value+",";//拼接参数用“,”隔开
				}
				ManageOperationRecord manageOperationRecord = new ManageOperationRecord();//准备操作记录
				manageOperationRecord.setUrl(url);
				
				//获取操作人信息
				HttpSession session = request.getSession();
				if (session != null) {
					ManageAccount manageAccount = (ManageAccount) session.getAttribute(UserConstants.LOGIN_USER_KEY);
					manageOperationRecord.setManageAccountId(manageAccount.getId());
				}
				manageOperationRecord.setModule(operationResource.getModule());
				manageOperationRecord.setParams(params);
				manageOperationRecord.setType(operationResource.getType());
				manageOperationRecord.setResult(1);
				manageOperationRecord.setDetail(operationResource.getDetail());
				manageOperationRecordService.save(manageOperationRecord);//保存操作记录
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
