package com.iotekclass.common.cache.redis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

	protected static Logger log = Logger.getLogger(RedisDataSource.class);

	@Resource(name = "shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;

	@Override
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			log.error("getRedisClent error", e);
		}
		return null;
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
			shardedJedisPool.returnBrokenResource(shardedJedis);
		} else {
			shardedJedisPool.returnResource(shardedJedis);
		}
	}
}