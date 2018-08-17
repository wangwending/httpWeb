package com.busi.demo;

import redis.clients.jedis.Jedis;

public class RedisDemo {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		String string = jedis.get("1");
		jedis.close();
		System.out.println(string);
	}
}
