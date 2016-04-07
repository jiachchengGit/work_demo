/**   
* @Title: ProcessModel.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午2:27:29 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

/** 
 * @ClassName: ProcessModel 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午2:27:29 
 *  
 */
public class ProcessModel {
	
	private String id;
	private String processCode;
	private String processName;
	private String processType;
	private String processStatus;
	private String processVersion;
	
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
	 * @return the processCode
	 */
	public String getProcessCode() {
		return processCode;
	}
	/**
	 * @param processCode the processCode to set
	 */
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}
	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}
	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	/**
	 * @return the processStatus
	 */
	public String getProcessStatus() {
		return processStatus;
	}
	/**
	 * @param processStatus the processStatus to set
	 */
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	/**
	 * @return the processVersion
	 */
	public String getProcessVersion() {
		return processVersion;
	}
	/**
	 * @param processVersion the processVersion to set
	 */
	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}
}
