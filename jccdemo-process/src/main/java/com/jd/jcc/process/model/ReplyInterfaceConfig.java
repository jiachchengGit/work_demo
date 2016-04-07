/**   
* @Title: ReplyInterfaceConfig.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午2:36:28 
* @version V1.0   
*/
package com.jd.jcc.process.model;

/** 
 * @ClassName: ReplyInterfaceConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午2:36:28 
 *  
 */
public class ReplyInterfaceConfig {
	private String id;
	private String refInterfaceId;
	private String configName;
	private String configCode;
	private String defaultValue;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the refInterfaceId
	 */
	public String getRefInterfaceId() {
		return refInterfaceId;
	}
	/**
	 * @param refInterfaceId the refInterfaceId to set
	 */
	public void setRefInterfaceId(String refInterfaceId) {
		this.refInterfaceId = refInterfaceId;
	}
	/**
	 * @return the configName
	 */
	public String getConfigName() {
		return configName;
	}
	/**
	 * @param configName the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	/**
	 * @return the configCode
	 */
	public String getConfigCode() {
		return configCode;
	}
	/**
	 * @param configCode the configCode to set
	 */
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
