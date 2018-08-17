package com.busi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
	 * ******************  类说明  *********************
	 * class       :  CacheConfig
	 * description :  配置redis缓存
	 * @author     :  wang.wending
	 * @date       :  Aug 13, 2018-6:43:55 PM
	 * @version    :  1.0  
	 * @see        :                        
	 * ***********************************************
 */
@Configuration
public class CacheConfig {

	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.maxIdle}")
    private int maxIdle;

    @Value("${spring.redis.maxTotal}")
    private int maxTotal;

    @Value("${spring.redis.maxWaitMillis}")
    private int maxWaitMillis;
    
    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;
    
    /**
     * 
    	 * ********************************************
    	 * method name   : cacheManager 
    	 * description   : 缓存管理器
    	 * @param        : @param redisTemplate
    	 * @param        : @return
         * @return       : CacheManager
    	 * modified      : wang.wending ,  Aug 10, 2018-5:38:18 PM
    	 * @see          : 
    	 * *******************************************
     */
    @Bean
	public CacheManager cacheManager(
			@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// 设置缓存过期时间
		cacheManager.setDefaultExpiration(10000);
		return cacheManager;
	}
    
    /**
     * 
    	 * ********************************************
    	 * method name   : jedisPoolConfig 
    	 * description   : 配置Jedis连接池
    	 * @param        : @return
         * @return       : JedisPoolConfig
    	 * modified      : wang.wending ,  Aug 10, 2018-10:18:50 AM
    	 * @see          : 
    	 * *******************************************
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        //jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        //jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        //jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        //jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }
    
   /**
    * 
   	 * ********************************************
   	 * method name   : jedisConnectionFactory 
   	 * description   : 配置Jedis连接工厂
   	 * @param        : @param jedisPoolConfig
   	 * @param        : @return
        * @return       : JedisConnectionFactory
   	 * modified      : wang.wending ,  Aug 10, 2018-10:19:15 AM
   	 * @see          : 
   	 * *******************************************
    */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //连接池  
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);  
        //IP地址  
        jedisConnectionFactory.setHostName(host);  
        //端口号  
        jedisConnectionFactory.setPort(port);  
        //如果Redis设置有密码  
        //JedisConnectionFactory.setPassword(password);  
        //客户端超时时间单位是毫秒  
        jedisConnectionFactory.setTimeout(5000);  
        return jedisConnectionFactory; 
    }
    
    /**
     * 
    	 * ********************************************
    	 * method name   : redisTemplate 
    	 * description   : 实例化RedisTemplate对象
    	 * @param        : @param redisConnectionFactory
    	 * @param        : @return
         * @return       : RedisTemplate<String,Object>
    	 * modified      : wang.wending ,  Aug 10, 2018-10:19:48 AM
    	 * @see          : 
    	 * *******************************************
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    	StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
    	template.afterPropertiesSet();
        return template;
    }
}
