/**   
 * @Title: MyMatch.java 
 * @Package com.jccdemo.client.expression 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年2月25日 下午4:50:29 
 * @version V1.0   
 */
package com.jccdemo.client.expression;

import java.util.regex.Pattern;

/**
 * @ClassName: MyMatch
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年2月25日 下午4:50:29
 * 
 */
public class MyMatch {
	public boolean match(String value, String exp) {
		System.out.println("----正则表达式计算----");
		return Pattern.compile(exp).matcher(value).find();
	}
}
