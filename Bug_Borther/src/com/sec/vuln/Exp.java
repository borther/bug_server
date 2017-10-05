package com.sec.vuln;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.sec.io.Out;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * EXP利用
 * @author Administrator
 *
 */
public class Exp {
	private static Out save = Out.getInstance();
	// redis未授权访问
	public static void redisAccess(String ip) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxWait(10l);
		Jedis jedis= null;
		JedisPool pool;
		pool = new JedisPool(config,ip);
		try {
			jedis = pool.getResource();
			if ("PONG".equals(jedis.ping())) {
				save.savaResult(ip + " redis");
			}
		} catch (Exception e) {
			System.out.println(ip + "不存在redis未授权访问");
		}
	}
	
	// Mongodb未授权访问
	public static void mongodbAccess(String ip) {
		Mongo client = null;
		try {
			MongoOptions mongoOptions = new MongoOptions();
			mongoOptions.connectTimeout = 200;
			client = new Mongo(ip,mongoOptions);
			if (client.getDatabaseNames().toString().contains("local")) {
				save.savaResult(client.getAddress().toString() + " mongodb");
			}
		} catch (Exception e) {
			System.out.println(ip + "不存在mongodb未授权访问");
		}
	}
}
