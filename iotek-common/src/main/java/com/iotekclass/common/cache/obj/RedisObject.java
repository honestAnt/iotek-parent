package com.iotekclass.common.cache.obj;

import com.fasterxml.jackson.core.type.TypeReference;
import com.iotekclass.common.cache.redis.RedisClientTemplate;
import com.iotekclass.common.util.JsonMapper;
import com.iotekclass.common.util.SpringUtil;

/*********************************************************************************
//* Copyright (C) 2014 mo-win. All Rights Reserved.
//*
//* Filename:      RedisObject.java
//* Revision:      1.0
//* Author:        lxu
//* Created On:    2014-8-13
//* Modified by:   
//* Modified On:   
//*
//* Description:   <description>
/********************************************************************************/

public class RedisObject<T> implements Cache<T> {

	private static RedisClientTemplate redisClientTemplate;

	private static JsonMapper binder;

	static {
		redisClientTemplate = (RedisClientTemplate) SpringUtil.getApplicationContext().getBean("redisClientTemplate");
		binder = JsonMapper.nonEmptyMapper();
	}

	public RedisObject() {
	}

	/**
	 * @return
	 */
	public static RedisClientTemplate getRedisClientTemplate() {
		return redisClientTemplate;
	}

	@Override
	public void refresh(String key, T target) {
		if (redisClientTemplate.exists(key)) {
			redisClientTemplate.del(key);
		}
		redisClientTemplate.set(key, /*Jacksons.me().readAsString(target)*/binder.toJson(target));
	}

	@Override
	public T getCache(String key) {
		if (!this.exist(key)) {
			return null;
		}
		String data = redisClientTemplate.get(key);
		return binder.fromJson(data, new TypeReference<T>() {
		});
	}

	@Override
	public Boolean isExpired(String key) {
		return redisClientTemplate.exists(key);
	}

	@Override
	public void setExpired(Long millsec) {
		//redisClientTemplate.expire(seconds);
	}

	@Override
	public Boolean exist(String key) {
		return redisClientTemplate.exists(key);
	}

	public Long getNextStoreKey() {
		if (!exist(STORE_KEY)) {
			redisClientTemplate.set(STORE_KEY, STORE_KEY_START);
		}
		return redisClientTemplate.incr(STORE_KEY);
	}

	private static final String STORE_KEY_START = "900000000";
	private static final String STORE_KEY = "admin_store_key";

}
