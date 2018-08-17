/**
 * 
 */
package com.busi.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * ******************  类说明  *********************
 * class       :  DataSourceConfig
 * description :  数据源配置
 * @author     :  wang.wending
 * @date       :  Aug 15, 2018-4:37:19 PM
 * @version    :  1.0  
 * @see        :                        
 * ***********************************************
 */
@Configuration
public class DataSourceConfig {
	
	/**
	 * 
		 * ********************************************
		 * method name   : duriDataSource 
		 * description   : 注入数据源
		 * @param        : @return
	     * @return       : DataSource
		 * modified      : wang.wending ,  Aug 15, 2018-4:41:32 PM
		 * @see          : 
		 * *******************************************
	 */
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.dataSource")
	public DataSource duriDataSource () {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}
	
}	
