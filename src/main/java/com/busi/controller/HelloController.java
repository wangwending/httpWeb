package com.busi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.busi.demo.Activity;
import com.busi.service.RedisService;
import com.busi.utils.HttpUtils;

@Controller
public class HelloController {
	private final static Logger logger = LogManager
			.getLogger(HelloController.class);

	@Autowired
	private RedisService redisService;
	
	
	@RequestMapping("hello")
	public @ResponseBody String hello() {
		return "hello";
	}

	@RequestMapping("test")
	public @ResponseBody String test(@RequestBody String params) {
		String url = "http://172.16.17.93:8097"
				+ "/emall/settle/api/settletrans/list";
		String[][] headers = { { "Content-Type", "application/json" } };
		String body = getBody(params);
		logger.debug("#listData() 请求报文:" + body);
//		 String jsonString = HttpUtils.doPost(url, "{\"page\":1,\"rows\":20}",
//		 headers, 3, 15, "utf-8");
		String jsonString = HttpUtils
				.doPost(url, body, headers, 3, 15, "utf-8");
		logger.debug("#listData 返回信息:" + jsonString);
		return jsonString;
	}

	/**
	 * ******************************************** method name : getBody
	 * description : 组装参数
	 * 
	 * @param : @param params
	 * @param : @return
	 * @return : String modified : wang.wending , Aug 14, 2018-2:43:48 PM
	 * @see : *******************************************
	 */
	private String getBody(String params) {
		Map<String, String> map = new HashMap<String, String>();
		String[] paramStr = params.split("&");
		String key = "";
		String value = "";
		for (String param : paramStr) {
			String[] par = param.split("=");
			key = par[0];
			if (par.length > 1) {
				value = par[1];
			}
			map.put(key, value);
		}
		String body = "";
		body = JSON.toJSONString(map);

		return body;
	}

	@RequestMapping("listActivity")
	public @ResponseBody String listActivity(@RequestBody String params) {
		// 根据商户id查商户的商品列表
		String body = getBody(params);
		String url = "http://172.16.16.13:8081"
				+ "/emall/admin/activity/queryactivity";
		String[][] headers = { { "Content-Type", "application/json" } };
		logger.debug("#listData() 请求报文:" + body);
		String jsonString = HttpUtils
				.doPost(url, body, headers, 3, 15, "utf-8");
		logger.debug("#listData 返回信息:" + jsonString);
		return jsonString;
	}
	
	@RequestMapping("list")
	public String list() {
		return "activity/list";
	}

	@RequestMapping("addActivity")
	public @ResponseBody String addActivity(@RequestBody String params,
			HttpServletRequest request) throws UnsupportedEncodingException {
		String string = URLDecoder.decode(params, "UTF-8");
		// 添加广告
		String key = getkey(request);
		String user = redisService.get(key + "sysUser");
		String user_name = JSONObject.parseObject(user).getJSONObject("attrs")
				.getString("user_name");
		String body = getBody(string + "&createuser=" + user_name);
		String url = "http://172.16.16.13:8081"
				+ "/emall/admin/activity/addactivity";
		String[][] headers = { { "Content-Type", "application/json" } };
		logger.debug("#add() 添加商品请求报文:" + body + ",操作人：" + user_name);
		String jsonString = HttpUtils
				.doPost(url, body, headers, 3, 15, "utf-8");
		logger.debug("#listData 返回信息:" + jsonString);

		JSONObject ret1 = (JSONObject) JSONObject.parse(jsonString);
		if ("0000".equals(ret1.get("ret"))) {
			logger.debug("#add() 添加商品成功,操作人：" + user_name + ",ret:"
					+ jsonString);
		} else {
			logger.debug("#add() 添加商品失败,操作人：" + user_name + ",ret:"
					+ jsonString);
		}
		return jsonString;
	}
	
	@RequestMapping("add")
	public String add() {
		return "activity/add";
	}

	@RequestMapping("updateActivity")
	public @ResponseBody String updateActivity(@RequestBody String params,
			HttpServletRequest request) throws Exception {
		String string = URLDecoder.decode(params, "UTF-8");
		// 添加广告
		String key = getkey(request);
		String user = redisService.get(key + "sysUser");
		String user_name = JSONObject.parseObject(user).getJSONObject("attrs")
				.getString("user_name");
		String body = getBody(string + "&createuser=" + user_name);
		String url = "http://172.16.16.13:8081"
				+ "/emall/admin/activity/notifyactivity";
		String[][] headers = { { "Content-Type", "application/json" } };
		logger.debug("#add() 修改商品请求报文:" + body + ",操作人：" + user_name);
		String jsonString = HttpUtils
				.doPost(url, body, headers, 3, 15, "utf-8");
		logger.debug("#listData 返回信息:" + jsonString);

		JSONObject ret1 = (JSONObject) JSONObject.parse(jsonString);
		if ("0000".equals(ret1.get("ret"))) {
			logger.debug("#add() 修改商品成功,操作人：" + user_name + ",ret:"
					+ jsonString);
		} else {
			logger.debug("#add() 修改商品失败,操作人：" + user_name + ",ret:"
					+ jsonString);
		}
		return jsonString;
	}

	@RequestMapping("deleteActivity")
	public @ResponseBody String deleteActivity(@RequestBody String params,
			HttpServletRequest request) {
		// 添加广告
		String body = request.getParameter("activityid");
		String url = "http://172.16.16.13:8081"
				+ "/emall/admin/activity/deletactivity/" + body;
		String[][] headers = { { "Content-Type", "application/json" } };
		logger.debug("#add() 修改商品请求报文:" + body);
		String jsonString = HttpUtils.doGet(url, headers, 3, 15, "utf-8");
		logger.debug("#listData 返回信息:" + jsonString);

		JSONObject ret1 = (JSONObject) JSONObject.parse(jsonString);
		if ("0000".equals(ret1.get("ret"))) {
			logger.debug("#deleteActivity() 删除商品成功,ret:" + jsonString);
		} else {
			logger.debug("#deleteActivity() 删除商品失败,ret:" + jsonString);
		}
		return jsonString;
	}

	/**
	 * ******************************************** method name : getkey
	 * description :
	 * 
	 * @param : @param request
	 * @param : @return
	 * @return : String modified : wang.wending , Aug 14, 2018-3:57:56 PM
	 * @see : *******************************************
	 */
	private String getkey(HttpServletRequest request) {
		String key = "";
		Cookie[] cookies = request.getCookies();
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

	public static void main(String[] args) {
		String ss = "lendsubid=1&lendaccid=1&transuserid=1&loansubid=1&loanaccid=1&page=1&rows=20";
		Map<String, String> map = new HashMap<String, String>();
		String[] s = ss.split("&");
		for (String a : s) {
			String[] aa = a.split("=");
			map.put(aa[0], aa[1]);
		}
		String string = JSON.toJSONString(map);
		System.err.println(string);
	}

	@RequestMapping("update")
	public String update(@RequestParam("activityid") String param, HttpServletRequest request) {
		// 根据商户id查商户的商品列表
		String body = getBody("activityid", param);
		String url = "http://172.16.16.13:8081"
				+ "/emall/admin/activity/querylist";
		String[][] headers = { { "Content-Type", "application/json" } };
		logger.debug("#listData() 请求报文:" + "");
//		String jsonString = HttpUtils
//				.doPost(url, "{\"activityid\":\"12344\",\"count\":\"0\"}", headers, 3, 15, "utf-8");
		String jsonString = HttpUtils
				.doPost(url, body, headers, 3, 15, "utf-8");
		logger.debug("#listData 返回信息:" + jsonString);
		Activity activity = JSON.parseObject(jsonString, Activity.class);
		request.setAttribute("activity", activity);
		return "activity/update";
	}

	/**
		 * ********************************************
		 * method name   : getBody 
		 * description   : 
		 * @param        : @param request
		 * @param        : @param string
		 * @param        : @return
	     * @return       : String
		 * modified      : wang.wending ,  Aug 15, 2018-3:34:56 PM
		 * @see          : 
		 * *******************************************
		 */
	private String getBody(String key, String value) {
		Map<String, String> map = new HashMap<>();
		map.put(key, value);
		map.put("count", "0");
		
		String result = JSON.toJSONString(map);
		return result;
	}
}
