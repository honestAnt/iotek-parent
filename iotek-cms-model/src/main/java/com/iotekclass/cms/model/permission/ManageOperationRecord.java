/**
 * 
 */
package com.iotekclass.cms.model.permission;

import java.io.Serializable;
import java.util.Date;

import com.iotekclass.cms.model.IdExtEntity;

/**
 * @ClassName: ManageOperationRecord   
 * @Description：后台操作日志记录表   
 * @Author：GuangChen
 * @Date：2015年5月15日 上午9:37:47
 * @version
 */
public class ManageOperationRecord extends IdExtEntity implements Serializable {

	
	private static final long serialVersionUID = -5117754218139331160L;
	
	public static final String tableName = "tb_manage_operation_record";
	
	/**
	 * 操作人Id
	 */
	private int manageAccountId;
	
	/**
	 * 操作url
	 */
	private String url;
	
	/**
	 * 请求参数
	 */
	private String params;
	
	/**
	 * 操作类型：1,增加  2,删除  3,修改  4,查询  5,授权

	 */
	private int type;
	
	/**
	 * 业务模块
	 */
	private String module;
	
	/**
	 * 操作详情
	 */
	private String detail;
	
	/**
	 * 操作结果:  1成功，0失败
	 */
	private int result;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * 用户名（视图字段）
	 */
	private String username;

	/**
	 * @return the manageAccountId
	 */
	public int getManageAccountId() {
		return manageAccountId;
	}

	/**
	 * @param manageAccountId the manageAccountId to set
	 */
	public void setManageAccountId(int manageAccountId) {
		this.manageAccountId = manageAccountId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
