package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;

/**
 * 
 * @ClassName: ManageRoleResourceRel   
 * @Description： 角色资源关系实体
 * @Author：hujing
 * @Date：2015年4月27日 下午2:30:13
 * @version
 */
public class ManageRoleResourceRel extends IdExtEntity implements Serializable{

	
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8487801415393187115L;
	
	public static final String tableName = "tb_manage_role_resource_rel";
	/**
	 * 账号id
	 */
	private int roleId;
	/**
	 * 角色描述
	 */
	private int resourceId;
	/**
	 * 权限标识
	 */
	private String permission;


	public ManageRoleResourceRel(){}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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
