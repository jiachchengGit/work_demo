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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clazzName == null) ? 0 : clazzName.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + port;
		result = prime * result
				+ ((versionAlias == null) ? 0 : versionAlias.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerConfig other = (ServerConfig) obj;
		if (clazzName == null) {
			if (other.clazzName != null)
				return false;
		} else if (!clazzName.equals(other.clazzName))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port != other.port)
			return false;
		if (versionAlias == null) {
			if (other.versionAlias != null)
				return false;
		} else if (!versionAlias.equals(other.versionAlias))
			return false;
		return true;
	}
}
