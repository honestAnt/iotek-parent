package com.iotekclass.cms.model;

import java.util.Date;

/**
 * 扩展抽象类
 * 创建时间、创建人、修改时间、修改人
 * 
 * @author zengaihui
 * 
 */
public abstract class IdExtEntity extends IdEntity {
	/**
	 * 创建时间
	 */
	protected Date createTime;

	/**
	 * 创建人Id
	 */
	@MyBatisNullable
	protected int createUser;

	/**
	 * 修改时间
	 */
	@MyBatisNullable
	protected Date updateTime;

	/**
	 * 修改人Id
	 */
	@MyBatisNullable
	protected int updateUser;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public int getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

}
