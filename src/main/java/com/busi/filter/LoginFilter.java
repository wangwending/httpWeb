package com.busi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.busi.service.RedisService;

/**
 * 
	 * ******************  类说明  *********************
	 * class       :  LoginFilter
	 * description :  登录过滤器
	 * @author     :  wang.wending
	 * @date       :  Aug 13, 2018-6:43:27 PM
	 * @version    :  1.0  
	 * @see        :                        
	 * ***********************************************
 */
@WebFilter(filterName = "authorFilter", urlPatterns = "/*")
@Configuration
public class LoginFilter implements Filter {

	private final static Logger logger = LogManager
			.getLogger(LoginFilter.class);

	@Autowired
	private RedisService redisService;

	@Override
	public void destroy() {
		logger.debug("destory......");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String key = getKey(req);
		String string = redisService.get(key);
		logger.debug("token value {}", string);
		if (StringUtils.isEmpty(string)) {
			res.sendRedirect("http://localhost:8080/plateform-manage/login");
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * ******************************************** method name : getKey
	 * description :获取浏览器cookie中token值
	 * 
	 * @param : @param req
	 * @param : @return
	 * @return : String modified : wang.wending , Aug 13, 2018-6:41:55 PM
	 * @see : *******************************************
	 */
	private String getKey(HttpServletRequest req) {
		String key = "";
		Cookie[] cookies = req.getCookies();
		
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					key = cookie.getValue();
					break;
				}
			}
		}
		
		return key;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.debug("init......");
	}

}
