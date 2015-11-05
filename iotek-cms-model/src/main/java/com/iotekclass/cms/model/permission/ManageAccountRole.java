package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;

/**
 * 
 * @ClassName: ManageAccountRole   
 * @Description： 账号角色关系实体类
 * @Author：hujing
 * @Date：2015年4月27日 下午2:05:52
 * @version
 */
public class ManageAccountRole extends IdExtEntity implements Serializable{

	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5185290171515917743L;
	
	public static final String tableName = "tb_manage_account_role";
	/**
	 * 账号id
	 */
	private int accountId;
	/**
	 * 角色描述
	 */
	private int roleId;


	public ManageAccountRole(){}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
