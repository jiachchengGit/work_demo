/**   
* @Title: MethodInvokation.java 
* @Package org.jccdemo.dsf.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月12日 上午10:30:17 
* @version V1.0   
*/
package org.jccdemo.dsf.model;

import java.io.Serializable;

/** 
 * @ClassName: MethodInvokation 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月12日 上午10:30:17 
 *  
 */
public class MethodInvocation implements Serializable{
	private static final long serialVersionUID = 1L;
	private String methodName;
	private String clazzName;
	private Class<?>[] paramTypes;
	private Object[] paramValues;
	private String versionAlias;
	
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * @return the clazzName
	 */
	public String getClazzName() {
		return clazzName;
	}
	/**
	 * @param clazzName the clazzName to set
	 */
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	/**
	 * @return the paramTypes
	 */
	public Class<?>[] getParamTypes() {
		return paramTypes;
	}
	/**
	 * @param paramTypes the paramTypes to set
	 */
	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	/**
	 * @return the paramValues
	 */
	public Object[] getParamValues() {
		return paramValues;
	}
	/**
	 * @param paramValues the paramValues to set
	 */
	public void setParamValues(Object[] paramValues) {
		this.paramValues = paramValues;
	}
	/**
	 * @return the versionAlias
	 */
	public String getVersionAlias() {
		return versionAlias;
	}
	/**
	 * @param versionAlias the versionAlias to set
	 */
	public void setVersionAlias(String versionAlias) {
		this.versionAlias = versionAlias;
	}
}
