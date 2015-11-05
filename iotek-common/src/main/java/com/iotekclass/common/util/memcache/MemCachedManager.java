package com.iotekclass.common.util.memcache;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iotekclass.common.util.Config;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * ClassName: MemCachedManager
 * Description： 缓存操作接口
 * Author：唐超
 * Date：2013-11-10 下午04:02:58
 * 
 * @version
 */
public class MemCachedManager {

	// 创建全局的唯一实例
	protected static MemCachedClient mcc = new MemCachedClient();
	protected static MemCachedManager memCachedManager = new MemCachedManager();
	protected static final Log log = LogFactory.getLog(MemCachedManager.class);

	// 设置与缓存服务器的连接池
	static {
		String server1 = Config.getCacheServer();
		// 服务器列表和其权重
		String[] servers = { server1 };
		// String[] servers = {"10.6.12.147:11211" };
		// String[] servers = {"192.168.8.129:11211" };
		Integer[] weights = { 3 };

		// 获取socke连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();

		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);

		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		// 初始化连接池
		pool.initialize();

		// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
		// mcc.setCompressEnable(true);
		// mcc.setCompressThreshold(64 * 1024);
	}

	/**
	 * 保护型构造方法，不允许实例化！
	 *
	 */
	protected MemCachedManager() {

	}

	/**
	 * 获取唯一实例.
	 * 
	 * @return
	 */
	public static MemCachedManager getInstance() {
		return memCachedManager;
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		if (log.isDebugEnabled()) {
			log.debug("have gotten a pojo to cache : key is " + key);
		}

		return mcc.get(key);
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		if (log.isDebugEnabled()) {
			log.debug("have added a pojo to cache : key is " + key);
		}

		return mcc.set(key, value);
	}

	/**
	 * @Description: 添加指定的值到缓存中，并指定过期时间
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 * @throws
	 */
	public boolean add(String key, Object value, Integer time) {

		if (log.isDebugEnabled()) {
			log.debug("have added a pojo to cache : key is " + key);
		}

		return mcc.set(key, value, time);
	}

	public boolean add(String key, Object value, Date expiry) {

		if (log.isDebugEnabled()) {
			log.debug("have added a pojo to cache : key is " + key + ";expiryDate is " + expiry.toString());
		}

		return mcc.set(key, value, expiry);
	}

	public boolean replace(String key, Object value) {

		if (log.isDebugEnabled()) {
			log.debug("have replaced a pojo to cache : key is " + key);
		}

		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {

		if (log.isDebugEnabled()) {
			log.debug("have replaced a pojo to cache : key is " + key + ";expiryDate is " + expiry.toString());
		}

		return mcc.replace(key, value, expiry);
	}

	// public boolean delete(String key, Date expiry) {
	//
	// if(log.isDebugEnabled()){
	// log.debug("have deleted a pojo to cache : key is " + key + ";expiryDate is " + expiry.toString());
	// }
	//
	// return mcc.delete(key, expiry);
	// }

	public boolean delete(String key) {

		if (log.isDebugEnabled()) {
			log.debug("have deleted a pojo to cache : key is " + key);
		}

		return mcc.delete(key);
	}

	// 检测Cache中当前Key是否存在
	public boolean exists(String key) {
		return mcc.keyExists(key);
	}

	/**
	 * 根据指定一批Key批量获取缓存内容。
	 * 
	 * @param sKeys
	 *            指定的一批Key。
	 * @return Map<sKey, oValue>
	 */
	public Map<String, Object> getMulti(String[] sKeys) {
		return mcc.getMulti(sKeys);
	}

}