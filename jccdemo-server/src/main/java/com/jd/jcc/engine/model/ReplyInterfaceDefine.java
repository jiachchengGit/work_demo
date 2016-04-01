/**   
* @Title: ProNodeInterface.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午2:22:04 
* @version V1.0   
*/
package com.jd.jcc.engine.model;

import java.util.List;

/** 
 * @ClassName: ProNodeInterface 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午2:22:04 
 *  
 */
public class ReplyInterfaceDefine {
	private String id;
	private String refNodeId;
	private String interfaceCode;
	private String interfaceName;
	private String interfaceType;	
	private String interfaceDesc;
	
	private List<ReplyInterfaceConfig> config;
	
	/**
	 * @return the refNodeId
	 */
	public String getRefNodeId() {
		return refNodeId;
	}

	/**
	 * @param refNodeId the refNodeId to set
	 */
	public void setRefNodeId(String refNodeId) {
		this.refNodeId = refNodeId;
	}

	/**
	 * @return the config
	 */
	public List<ReplyInterfaceConfig> getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(List<ReplyInterfaceConfig> config) {
		this.config = config;
	}

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
	 * @return the interfaceCode
	 */
	public String getInterfaceCode() {
		return interfaceCode;
	}

	/**
	 * @param interfaceCode the interfaceCode to set
	 */
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}

	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the interfaceType
	 */
	public String getInterfaceType() {
		return interfaceType;
	}

	/**
	 * @param interfaceType the interfaceType to set
	 */
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	/**
	 * @return the interfaceDesc
	 */
	public String getInterfaceDesc() {
		return interfaceDesc;
	}

	/**
	 * @param interfaceDesc the interfaceDesc to set
	 */
	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}
}
