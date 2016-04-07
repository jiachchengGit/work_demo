/**   
* @Title: ParallelLineItem.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月5日 上午11:35:25 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

/** 
 * @ClassName: ParallelLineItem 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月5日 上午11:35:25 
 *  
 */
public class ParallelLineItem {
	private String id;
	private String name;
	private String refNodeId;
	private String nextNodeKey;
	private String weight;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the nextNodeKey
	 */
	public String getNextNodeKey() {
		return nextNodeKey;
	}
	/**
	 * @param nextNodeKey the nextNodeKey to set
	 */
	public void setNextNodeKey(String nextNodeKey) {
		this.nextNodeKey = nextNodeKey;
	}
	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
