package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.ManageAccountLogTypeEnum;

/**
 * 
 * @ClassName: ManageAccountLog   
 * @Description： 后台账号操作日志实体 
 * @Author：hujing
 * @Date：2015年4月27日 下午2:37:27
 * @version
 */
public class ManageAccountLog extends IdExtEntity implements Serializable{

	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8122264404260860351L;
	
	public static final String tableName = "tb_manage_account_log";
	/**
	 * 账号id
	 */
	private int accountId;
	/**
	 * 角色描述 1:新增 2:修改 3:删除 4:分配权限 5:修改权限;6:登录
	 */
	private int updateType;
	/**
	 * 权限标识
	 */
	private String description;


	public ManageAccountLog(){}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUpdateType() {
		return updateType;
	}

	/**
	 * 
	 * @description 获取枚举类型 
	 * @author wangfengbao
	 * @date 2015年4月27日 下午4:45:22
	 *
	 * @return
	 */
	public ManageAccountLogTypeEnum getUpdateTypeEnum() {
		return ManageAccountLogTypeEnum.fromOrdinal(updateType);
	}
	
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
