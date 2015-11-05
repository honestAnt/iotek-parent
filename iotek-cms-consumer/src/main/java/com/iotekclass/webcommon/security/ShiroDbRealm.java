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

import com.google.common.base.Objects;
import com.iotekclass.cms.model.permission.ManageAccount;
import com.iotekclass.cms.service.permission.ManageAccountService;
import com.iotekclass.cms.service.permission.OperationResourceService;
import com.iotekclass.common.constants.enums.BooleanEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * 登录后的回调函数
 * 
 * @author ZengAihui
 * 
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm {

	
	private static final String OR_OPERATOR = " or ";  
    private static final String AND_OPERATOR = " and ";  
    private static final String NOT_OPERATOR = "not ";
	@Autowired
	private ManageAccountService manageAccountService;
	@Autowired
	private OperationResourceService operationResourceService;
	@Autowired
	protected CustomManageShiroSession customManageShiroSession;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		System.out.println(operationResourceService.getById(1));
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
//		User user = userService.findByUsername(token.getUsername());
		ManageAccount manageAccount = manageAccountService.getByNameAndPwd(token.getUsername(), "");
		if (manageAccount != null) {
			if (ObjectUtils.toString(BooleanEnum.FALSE.getValue()).equals(String.valueOf(manageAccount.getEnabled()))) {
				throw new AccountException("当前用户无效");
			}
			return new SimpleAuthenticationInfo(new ShiroUser(Long.valueOf(manageAccount.getId()), manageAccount.getUsername(),manageAccount.getUsername()),
					manageAccount.getPassword(),manageAccount.getUsername());
		} else {
			throw new AccountException("当前用户无效");
		}
	}
	

	/**
	 * 设定Password校验.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		// 该句作用是重写shiro的密码验证，让shiro用我自己的验证
		setCredentialsMatcher(new CustomCredentialsMatcher());
	}


	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("Principal对象不能为空");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		/*
		 //判断是否是管理员
		if ("administrator".equals(principals.getPrimaryPrincipal().toString())) {
			info.addRole("adminstrator");//添加管理员角色
		} else {
			//普通用户添加角色和资源权限
//			info.addRole("administrator");//添加管理员角色
//			info.addRole("张三");//添加管理员角色 
//			info.addStringPermission("test_shiro:"+0x001);
//			info.addStringPermission("test_shiro:"+0x002);
		}*/
		info = manageAccountService.dealAccountRoleAndPermissions(principals.getPrimaryPrincipal().toString());
		return info;
	}

    /** 
     * 覆盖父类权限判断，使其支持or and not 关键词  不支持and or混用 
     * @param principals 
     * @param permission 
     * @return 
     */  
	@Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {  
        if(permission.contains(OR_OPERATOR)) {  
            String[] permissions = permission.split(OR_OPERATOR);  
            for(String orPermission : permissions) {  
                if(isPermittedWithNotOperator(principals, orPermission.trim())) {  
                    return true;  
                }  
            }  
            return false;  
        } else if(permission.contains(AND_OPERATOR)) {  
            String[] permissions = permission.split(AND_OPERATOR);  
            for(String orPermission : permissions) {  
                if(!isPermittedWithNotOperator(principals, orPermission.trim())) {  
                    return false;  
                }  
            }  
            return true;  
        } else {  
            return isPermittedWithNotOperator(principals, permission.trim());  
        }  
    }  
      
	/**
	 * 
	 * @description 拆分权限字符串 
	 * @author wangfengbao
	 * @date 2015年6月12日 下午5:00:53
	 *
	 * @param str
	 * @return
	 */
	/*private List<String> splitPermiss(String str,String operate_type, List<String> list ) {

		if (str.contains(operate_type.trim())) {
			for (String cell: str.split(operate_type.trim())) {
				if (cell.contains(operate_type)) {
					splitPermiss(cell, operate_type,list);
				}
				list.add(cell);
			}
		}
		return list;
	}*/
	
    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {  
        if(permission.startsWith(NOT_OPERATOR)) {  
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));  
        } else {  
            return super.isPermitted(principals, permission);  
        }  
    }

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Long id;
		public String loginName;
		public String name;

		public ShiroUser(Long id, String loginName, String name) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}
}
