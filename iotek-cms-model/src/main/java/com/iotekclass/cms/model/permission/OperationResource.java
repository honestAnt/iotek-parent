/**
 * 
 */
package com.iotekclass.cms.model.permission;

import java.io.Serializable;

import com.iotekclass.cms.model.IdExtEntity;
import com.iotekclass.common.constants.enums.OperationResourceTypeEnum;

/**
 * @ClassName: OperationResource   
 * @Description： 后台操作资源表
 * @Author：GuangChen
 * @Date：2015年5月15日 上午9:48:00
 * @version
 */
public class OperationResource extends IdExtEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -845834610082279170L;
	public static final String tableName = "tb_operation_resource";
	
	/**
	 * 业务模块，菜单
	 */
	private String module;
	
	/**
	 * 操作类型：1,增加  2,删除  3,修改  4,查询  5,授权
	 */
	private int type;
	
	/**
	 * Action地址
	 */
	private String url;
	
	/**
	 * 操作详情
	 */
	private String detail;
	
	
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
	
	public OperationResourceTypeEnum getTypeEnum() {
		return OperationResourceTypeEnum.fromOrdinal(this.type);
	}
	
	public String getTypeLabel() {
		return OperationResourceTypeEnum.fromOrdinal(this.type).getLabel();
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
	
}
