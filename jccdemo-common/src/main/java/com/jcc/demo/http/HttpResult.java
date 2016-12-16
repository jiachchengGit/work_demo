/**   
* @Title: HttpResult.java 
* @Package com.jcc.demo.http 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午9:44:26 
* @version V1.0   
*/
package com.jcc.demo.http;

import org.apache.commons.httpclient.Cookie;

/** 
 * @ClassName: HttpResult 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午9:44:26 
 *  
 */
public class HttpResult {
	private String body;
	private Cookie[] cookies;
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the cookies
	 */
	public Cookie[] getCookies() {
		return cookies;
	}
	/**
	 * @param cookies the cookies to set
	 */
	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}
	
	
}
