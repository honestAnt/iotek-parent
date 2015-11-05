/**
 * 
 */
package com.iotekclass.cms.model.filter;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Description 拦截器实体类
 * @author wangfengbao
 * @date 2015年4月29日 上午11:08:15
 *
 */
public class UrlFilter {
	
	/**
	 * 访问路径
	 */
	private String url;
	
	/**
	 * 角色
	 */
	private String roles;
	
	/**
	 * 权限
	 */
	private String permissions;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
