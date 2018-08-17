/**
 * 
 */
package com.busi.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.pagehelper.PageHelper;

/**
 * ******************  类说明  *********************
 * class       :  MybatisConfig
 * description :  mybatis配置
 * @author     :  wang.wending
 * @date       :  Aug 15, 2018-4:43:00 PM
 * @version    :  1.0  
 * @see        :                        
 * ***********************************************
 */
@Configuration
public class MybatisConfig {
	
	private static final Logger log = LogManager.getLogger(MybatisConfig.class);
	
	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	/**
	 * 
		 * ********************************************
		 * method name   : pageHelper 
		 * description   : 配置分页插件
		 * @param        : @return
	     * @return       : PageHelper
		 * modified      : wang.wending ,  Aug 15, 2018-4:45:13 PM
		 * @see          : 
		 * *******************************************
	 */
	@Bean
    public PageHelper pageHelper() {
		log.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
		p.setProperty("dialect","mysql");
		pageHelper.setProperties(p);
        return pageHelper;
    }
	
	/**
	 * 
		 * ********************************************
		 * method name   : transactionManager 
		 * description   : 配置事务管理
		 * @param        : @return
	     * @return       : PlatformTransactionManager
		 * modified      : wang.wending ,  Aug 15, 2018-4:41:44 PM
		 * @see          : 
		 * *******************************************
	 */
	@Bean
	public PlatformTransactionManager transactionManager () {
		return new DataSourceTransactionManager(dataSource);
	}
}
