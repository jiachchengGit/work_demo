/**   
* @Title: PropertiesUtils.java 
* @Package com.jcc.demo.fileutils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月11日 下午3:12:25 
* @version V1.0   
*/
package com.jccdemo.fileutils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: PropertiesUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月11日 下午3:12:25 
 *  
 */
public class PropertiesUtils {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties pro = new Properties();
	public static void init(String path){
		if(path != null){
			try {
				pro.load(PropertiesUtils.class.getResourceAsStream(path));
			} catch (IOException e) {
				log.error("--[加载properties文件失败]--path="+path,e);
			}
		}
	}
	
	public static String getValue(String key){
		return pro.getProperty(key);
	}
	
	public static void main(String[] args){
		String path ="/test.properties";
		init(path);
		String value = getValue("name");
		System.out.println(value);
	}
}
