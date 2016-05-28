/**   
* @Title: ClassReflectUtils.java 
* @Package com.jcc.demo.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年5月13日 上午9:04:29 
* @version V1.0   
*/
package com.jcc.demo.common;

/** 
 * @ClassName: ClassReflectUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年5月13日 上午9:04:29 
 *  
 */
public class ClassReflectUtils {
	
	public static String getFieldSetterMethod(String fieldName) {
		 String m = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length());
		return m;
	}
	
	public static String getFieldGetterMethod(String fieldName) {
		 String m = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length());
		return m;
	}
}
