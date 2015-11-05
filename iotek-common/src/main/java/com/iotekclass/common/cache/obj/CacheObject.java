package com.iotekclass.common.cache.obj;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*********************************************************************************
//* Copyright (C) 2014 mo-win. All Rights Reserved.
//*
//* Filename:      CacheManager.java
//* Revision:      1.0
//* Author:        lxu
//* Created On:    2014-8-13
//* Modified by:   
//* Modified On:   
//*
//* Description:   <description>
/********************************************************************************/

public class CacheObject<T> implements Cache<T> {

	/**
	 * 缓存数据索引
	 */
	private Map<String, CacheLast<T>> cache = new ConcurrentHashMap<String, CacheLast<T>>();

	/**
	 * 缓存超时时间，单位：毫秒
	 */
	private Long expired = 0L;

	public CacheObject() {
		this(5 * 1000 * 60L);
	}

	public CacheObject(Long expired) {
		this.expired = expired;
	}

	@Override
	public void refresh(String key, T target) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
		cache.put(key, new CacheLast<T>(target));
	}

	@Override
	public T getCache(String key) {
		if (!this.exist(key)) {
			return null;
		}

		return cache.get(key).getData();
	}

	@Override
	public Boolean isExpired(String key) {
		if (!this.exist(key)) {
			return null;
		}

		long currtime = new Date().getTime();
		long lasttime = cache.get(key).getRefreshtime();

		return (currtime - lasttime) > expired;
	}

	@Override
	public void setExpired(Long millsec) {
		this.expired = millsec;
	}

	@Override
	public Boolean exist(String key) {
		return cache.containsKey(key);
	}

}
