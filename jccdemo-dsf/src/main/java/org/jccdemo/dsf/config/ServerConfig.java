/**   
* @Title: ServerConfig.java 
* @Package org.jccdemo.dsf.config 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午5:21:37 
* @version V1.0   
*/
package org.jccdemo.dsf.config;

/** 
 * @ClassName: ServerConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午5:21:37 
 *  
 */
public class ServerConfig {
	private String clazzName;
	private String versionAlias;
	private String ip;
	private int port;
	private int status;
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return "ServerConfig [clazzName=" + clazzName + ", versionAlias="
				+ versionAlias + ", ip=" + ip + ", port=" + port + "]";
	}
}
