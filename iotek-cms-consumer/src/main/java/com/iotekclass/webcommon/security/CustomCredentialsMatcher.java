package com.iotekclass.webcommon.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.iotekclass.common.util.MD5Util;

/**
 * 自定义 密码验证类
 * 
 * @author ZengAihui
 * 
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if(token.isValidatePassword()){
			Object tokenCredentials = MD5Util.md5(String.valueOf(token.getPassword()));
			Object accountCredentials = getCredentials(info);
			// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
			return equals(tokenCredentials, accountCredentials);
		}
		return true;
	}
}
