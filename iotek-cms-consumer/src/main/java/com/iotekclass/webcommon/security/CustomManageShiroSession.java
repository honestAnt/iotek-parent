/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.iotekclass.webcommon.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.model.permission.ManageRole;
import com.iotekclass.cms.service.filter.FilterService;
import com.iotekclass.cms.service.permission.ManageAccountLogService;
import com.iotekclass.cms.service.permission.ManageAccountService;
import com.iotekclass.cms.service.permission.ManageRoleService;
import com.iotekclass.common.cache.redis.RedisClientTemplate;
import com.iotekclass.common.constants.UserConstants;
import com.iotekclass.common.constants.enums.ManageAccountLogTypeEnum;
import com.iotekclass.common.util.JsonMapper;

/**
 * 
 * ClassName: CustomShiroSession
 * Description： 自定义会话工具
 * Author：王啸
 * Date：2014年9月22日 下午4:16:10
 * 
 * @version
 */
@Component
public class CustomManageShiroSession {

	private static Logger logger = LoggerFactory.getLogger(CustomManageShiroSession.class);


	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	/**
	 * 防止redis中键值重复,保证本系统唯一
	 */
	private static final String system_label = "_cms";

	@Autowired
	private ManageAccountService manageAccountService;
	@Autowired
	private ManageRoleService manageRoleService;

	@Autowired
	private FilterService filterService;

	@Autowired
	private ManageAccountLogService manageAccountLogService;
	
	/**
	 * 用户登录后产生的Shiro Session
	 * 
	 * @param user
	 * @param session
	 */
	public void generateUserSessions(ManageAccount manageAccount) {
		HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils.getSubject()).getServletRequest();
		HttpSession session = request.getSession();

		int userId = manageAccount.getId();
		session.setAttribute(UserConstants.LOGIN_USER_ID_KEY, userId);
		session.setAttribute(UserConstants.LOGIN_USER_KEY, manageAccount);
		//将对象json后传到前台
		JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
		session.setAttribute(UserConstants.JSON_LOGIN_USER_KEY, jsonMapper.toJson(manageAccount));
		//添加账号角色
		List<ManageRole> manageRoles = manageRoleService.getByAccountId(manageAccount.getId());
		if (manageRoles != null && manageRoles.size() > 0) {
			session.setAttribute(UserConstants.ACCOUNT_ROLE_KEY, 
					manageRoles.get(0).getId());
			
			//获取所有用户权限资源
			List<String> urls = filterService.getAllListByRole(manageRoles.get(0).getId());
			
			//把用户权限资源放在memcache中
			redisClientTemplate.set(manageAccount.getId()+UserConstants.ACCOUNT_PERMISSION_KEY+system_label,
					jsonMapper.toJson(urls));
		}
		
		
		//登录日志
		manageAccountLogService.saveInfo(manageAccount.getId(), 
				manageAccount.getId(), ManageAccountLogTypeEnum.LOGIN);
		//更新用户登录时间
		manageAccount.setLastLoginTime(new Date());
		manageAccountService.update(manageAccount);
		
		// 登录成功之后记录sessioniId Token
		String jSessionId = session.getId();
		logger.debug("sessionId: " + jSessionId);

		// 登录成功之后记录userId jsessionid。添加cms后缀，防止和其它项目冲突
		redisClientTemplate.set(String.valueOf(manageAccount.getId())+system_label, jSessionId);

		if (logger.isDebugEnabled()) {
			logger.debug("userId：" + userId + "  jsessionid：" + redisClientTemplate.get(String.valueOf(manageAccount.getId())+system_label));
		}

	}
}
