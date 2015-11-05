package com.iotekclass.persist.pagination;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * ClassName: Page
 * Description： 分页对象
 * Author：王啸
 * Date：2014年10月17日 下午2:57:33
 * 
 * @version
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageSize = 10; // 每页显示记录数

	private int currentPage = 1; // 当前页码

	private int dataCount = Integer.MAX_VALUE;// 数据总条数

	/**
	 * 条件组
	 */
	private Map<String, Object> paramsMap = Maps.newHashMap();

	/**
	 * 排序
	 */
	private Sort pageSort;

	/**
	 * 排序参数
	 */
	private String sortParam;

	/**
	 * 是否有下一页
	 */
	boolean next = false;
	/**
	 * 是否有前一页
	 */
	boolean prev = false;

	private List<T> result = Lists.newArrayList();

	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	// 获取offset
	public int getOffset() {
		return (currentPage - 1) * pageSize;
	}

	// 获取limit
	public int getLimit() {
		return getPageSize();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		int temPage = currentPage;
		if (temPage < 1) {
			temPage = 1;
		}
		if (temPage > getMaxPage()) {
			temPage = getMaxPage();
		}
		this.currentPage = temPage;
	}

	// 获取最大的页码
	public int getMaxPage() {
		int maxPage = (int) Math.ceil((double) dataCount / pageSize);// ceil 无条件进位，主要满足 1/4 5/4 默认整数相除被截断的问题
		if (maxPage < 1) {
			maxPage = 1;
		}
		return maxPage;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public boolean isNext() {
		return currentPage < getMaxPage();
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return (currentPage - 1) > 0;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getSortParam() {
		return sortParam;
	}

	public void setSortParam(String sortParam) {
		this.sortParam = sortParam;
	}

	public Sort getPageSort() {
		return pageSort;
	}

	public void setPageSort(Sort pageSort) {
		this.pageSort = pageSort;
	}

}
