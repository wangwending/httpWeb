package com.busi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ******************  类说明  *********************
 * class       :  HttpUtils
 * @author     :  hejinyun@umpay.com
 * @version    :  1.0  
 * description :  HTTP请求公共类-"后期需要修改为连接池"
 * @see        :                        
 * ***********************************************
 */
public class HttpUtils {
	
	private static final Logger LOG = LogManager.getLogger(HttpUtils.class);
	
	/**
	 * ********************************************
	 * method name   : doPost 
	 * description   : Http协议Post方式请求
	 * @return       : String
	 * @param        : @param url
	 * @param        : @param body
	 * @param        : @param headers
	 * @param        : @param connectionTimeout
	 * @param        : @param readTimeout
	 * @param        : @param charset
	 * @param        : @return
	 * modified      : hejinyun@umpay.com ,  2016-11-7  下午3:28:02
	 * @see          : 
	 * *******************************************
	 */
	public static String doPost(String url, String body, String[][] headers,
			int connectionTimeout, int readTimeout, String charset){
		URL uri = null;
		HttpURLConnection urlConnection = null;
		BufferedReader br = null;
		InputStream is = null;
		try {
			uri = new URL(url);
			urlConnection = (HttpURLConnection)uri.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setConnectTimeout(1000 * connectionTimeout); // 连接超时时间
			urlConnection.setReadTimeout(1000 * readTimeout); // 响应超时时间
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			// 设置Http报文请求头
			for (String[] header : headers){
				urlConnection.addRequestProperty(header[0], header[1]);
			}
			// 提交Http请求参数
			urlConnection.connect();
			if (null != body){
				urlConnection.getOutputStream().write(body.getBytes(charset));
				urlConnection.getOutputStream().flush();
				urlConnection.getOutputStream().close();
			}
			
			// 读取响应参数
			int responseCode = urlConnection.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode){
				is = urlConnection.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, charset));
				StringBuffer response = new StringBuffer();
				String line = br.readLine();
				while (null != line){
					response.append(line);
					line = br.readLine();
				}
				return response.toString();
			} else {
				LOG.info("#doPost() 请求失败！"+responseCode);
			}
		} catch(Exception e){
			LOG.error("#doPost() 请求异常:"+e.toString(), e);
		} finally{
			if (null != urlConnection){
				urlConnection.disconnect();
			}
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					LOG.error("#doPost() BufferedReader关闭异常:"+e.toString(), e);
				}
			}
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					LOG.error("#doPost() InputStream关闭异常:"+e.toString(), e);
				}
			}
		}
		return null;
	}
	
	/**
	 * ********************************************
	 * method name   : doGet 
	 * description   : Http协议Get方式请求
	 * @return       : String
	 * @param        : @param url
	 * @param        : @param headers
	 * @param        : @param connectionTimeout
	 * @param        : @param readTimeout
	 * @param        : @param charset
	 * @param        : @return
	 * modified      : hejinyun@umpay.com ,  2016-11-7  下午3:28:18
	 * @see          : 
	 * *******************************************
	 */
	public static String doGet(String url, String[][] headers,
			int connectionTimeout, int readTimeout, String charset){
		URL uri = null;
		HttpURLConnection urlConnection = null;
		BufferedReader br = null;
		InputStream is = null;
		try {
			uri = new URL(url);
			urlConnection = (HttpURLConnection) uri.openConnection();
			urlConnection.setConnectTimeout(1000 * connectionTimeout);
			urlConnection.setReadTimeout(1000 * readTimeout);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
//			for (String[] header : headers){
//				urlConnection.addRequestProperty(header[0], header[1]);
//			}
			urlConnection.connect();
			// 读取响应参数
			int responseCode = urlConnection.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode){
				is = urlConnection.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, charset));
				StringBuffer response = new StringBuffer();
				String line = br.readLine();
				while (null != line){
					response.append(line);
					line = br.readLine();
				}
				return response.toString();
			} else {
				LOG.info("#doGet() 请求失败！"+responseCode);
			}
		} catch (Exception e){
			LOG.error("#doGet() 请求异常", e);
		} finally {
			if (null != urlConnection){
				urlConnection.disconnect();
			}
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					LOG.error("#doPost() BufferedReader关闭异常:"+e.toString(), e);
				}
			}
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					LOG.error("#doPost() InputStream关闭异常:"+e.toString(), e);
				}
			}
		}
		return null;
	}
	
	/**
	 * ********************************************
	 * method name   : doPost 
	 * description   : post请求，默认json格式、连接超时时间3s、读超时5s、utf-8编码
	 * @return       : String
	 * @param        : @param url
	 * @param        : @param body
	 * @param        : @return
	 * modified      : xuhuafeng ,  2017-4-12  下午5:01:31
	 * *******************************************
	 */
	public static String doPost(String url, String body){
		String[][] headers = {{"Content-Type", "application/json"}};
		int connectionTimeout = 3;
		int readTimeout = 15;
		String charset = "utf-8";
		return doPost(url, body, headers, connectionTimeout, readTimeout, charset);
	}
	
	public static String doGet(String url){
		String[][] headers = {{"Content-Type", "application/json"}};
		int connectionTimeout = 3;
		int readTimeout = 15;
		String charset = "utf-8";
		return doGet(url, headers, connectionTimeout, readTimeout, charset);
	}
}
