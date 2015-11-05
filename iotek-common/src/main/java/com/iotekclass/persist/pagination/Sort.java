package com.iotekclass.persist.pagination;

import java.io.Serializable;

/**
 * @author ZengAihui
 * 
 */
public class Sort implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 排序项
	private String sortItem;
	// 对应的数据库字段
	private String sortColumn;
	// 降序
	private boolean desc;

	public Sort(String sortItem, String sortColumn, boolean desc) {
		super();
		this.sortItem = sortItem;
		this.sortColumn = sortColumn;
		this.desc = desc;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

}
