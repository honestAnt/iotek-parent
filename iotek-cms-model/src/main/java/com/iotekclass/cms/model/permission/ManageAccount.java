package com.iotekclass.cms.model.permission;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.ManageAccountTypeEnum;


/**
 * 
 * @ClassName: ManageAccount   
 * @Description： 后台账号实体类
 * @Author：hujing
 * @Date：2015年4月27日 下午1:26:40
 * @version
 */
public class ManageAccount extends IdExtEntity implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -585879787453278073L;

	public static final String tableName = "tb_manage_account";
	
	/**
	 * 学校id或企业id
	 */
	private int unitId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 类型 1:网校管理员。2：院校管理员;3:企业管理员
	 */
	private int type;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 用户状态
	 */
	private int enabled;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 角色名称 视图字段
	 */
	private String roleName;
	
	public ManageAccount(){}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getType() {
		return type;
	}
	
	/**
	 * 
	 * @description 获取枚举类型 
	 * @author wangfengbao
	 * @date 2015年4月27日 下午4:43:44
	 *
	 * @return
	 */
	public ManageAccountTypeEnum getTypeEnum() {
		return ManageAccountTypeEnum.fromOrdinal(type);
	}


	public int getUnitId() {
		return unitId;
	}


	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getEnabled() {
		return enabled;
	}


	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	
	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
