/**   
* @Title: HeatReq.java 
* @Package org.jccdemo.dsf.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午4:14:09 
* @version V1.0   
*/
package org.jccdemo.dsf.test.bean;

import java.io.Serializable;

/** 
 * @ClassName: HeatReq 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午4:14:09 
 *  
 */
public class HeatReq implements Serializable {
	/**
	 * @Field serialVersionUID: TODO
	 * @User chenjiacheng
	 * @date 2016年6月18日 下午5:26:04
	 */
	private static final long serialVersionUID = 1L;
	private String ask;
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
	 * @return the ask
	 */
	public String getAsk() {
		return ask;
	}
	/**
	 * @param ask the ask to set
	 */
	public void setAsk(String ask) {
		this.ask = ask;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HeatReq [ask=" + ask + ", status=" + status + "]";
	}
}
