package com.busi.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Redis工具类
 * @author wang.wending
 *
 */
public class RedisUtil {
	/**
	 * redis模板
	 */
	@Resource
	private static RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 写入缓存
	 * @param key 
	 * @param value
	 * @return
	 */
	public static boolean set(final String key, String value) {
			boolean result = false;
			
			try {
				ValueOperations<String, String> operations = redisTemplate.opsForValue();
				operations.set(key, value);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
	}
	
	
	/**
	 * 写入缓存设置失效时间
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */
	 public static boolean set(final String key, String value, Long expireTime) {  
	        boolean result = false;  
	        try {  
	            ValueOperations<String, String> operations = redisTemplate  
	                    .opsForValue();  
	            operations.set(key, value);  
	            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);  
	            result = true;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return result;  
	    }  
	 
	 /**
	  * 获取缓存值
	  * @param key
	  * @return
	  */
	 public static String get(final String key) {
		 String result = null;
		 
		 ValueOperations<String, String> operations =  redisTemplate.opsForValue();
		 operations.get(key);
		 
		 return result;
	 }
	 
	 /**
	  * 删除数据
	  * @param key
	  */
	 public static void remove(final String key) {
		 if (exist(key)) {
			 redisTemplate.delete(key);
		 }
	 }

	 /**
	  * 批量删除
	  * @param keys
	  */
	 public static void remove(final String... keys) {
		 for (String key : keys) {
			 remove(key);
		 }
	 }
	 
	 /**
	  * 判断缓存键是否存在
	  * @param key
	  * @return
	  */
	private static boolean exist(String key) {
		return redisTemplate.hasKey(key);
	}

}
