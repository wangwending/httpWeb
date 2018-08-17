package com.busi.service.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.busi.service.RedisService;

/**
 * 
 * @author wang.wending
 *
 */
@Service
public class RedisServiceImpl implements  RedisService{
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public String get(String key) {
		
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void remove(String key) {
		if (exist(key)) {
			redisTemplate.delete(key);
		}
	}

	private boolean exist(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void remove(String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	@Override
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, String value, Long expireTime) {
		redisTemplate.opsForValue().set(key, value, expireTime);
	}

}
