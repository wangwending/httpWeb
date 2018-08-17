package com.busi.service;

/**
 * 
	 * ******************  类说明  *********************
	 * class       :  RedisService
	 * description :  redis操作接口
	 * @author     :  wang.wending
	 * @date       :  Aug 13, 2018-6:44:27 PM
	 * @version    :  1.0  
	 * @see        :                        
	 * ***********************************************
 */
public interface RedisService {
	/**
	 * 
		 * ********************************************
		 * method name   : get 
		 * description   : 获取数据
		 * @param        : @param key 键
	     * @return       : String
		 * modified      : wang.wending ,  Aug 13, 2018-6:44:49 PM
		 * @see          : 
		 * *******************************************
	 */
	String get(String key);
	
	/**
	 * 
		 * ********************************************
		 * method name   : remove 
		 * description   : 删除数据
		 * @param        : @param key 键
	     * @return       : void
		 * modified      : wang.wending ,  Aug 13, 2018-6:44:58 PM
		 * @see          : 
		 * *******************************************
	 */
	void remove(String key);
	
	/**
	 * 
		 * ********************************************
		 * method name   : remove 
		 * description   : 批量删除数据
		 * @param        : @param keys 键集合
	     * @return       : void
		 * modified      : wang.wending ,  Aug 13, 2018-6:45:08 PM
		 * @see          : 
		 * *******************************************
	 */
	void remove(String... keys);
	
	/**
	 * 
		 * ********************************************
		 * method name   : set 
		 * description   : 设置数据
		 * @param        : @param key 键
		 * @param        : @param value 值
	     * @return       : void
		 * modified      : wang.wending ,  Aug 13, 2018-6:45:22 PM
		 * @see          : 
		 * *******************************************
	 */
	void set(String key, String value);
	
	/**
	 * 
		 * ********************************************
		 * method name   : set 
		 * description   : 设置有效期数据
		 * @param        : @param key 键
		 * @param        : @param value 值
		 * @param        : @param expireTime 有效期时间 单位秒
	     * @return       : void
		 * modified      : wang.wending ,  Aug 13, 2018-6:45:37 PM
		 * @see          : 
		 * *******************************************
	 */
	void set(String key, String value, Long expireTime);
}
