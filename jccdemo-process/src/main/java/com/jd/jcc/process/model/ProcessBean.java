/**   
* @Title: ProcessBean.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:40:40 
* @version V1.0   
*/
package com.jd.jcc.process.model;

import java.util.Map;

import com.jd.jcc.process.nodedefine.BaseProNode;

/** 
 * @ClassName: ProcessBean 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午2:40:40 
 *  
 */
public class ProcessBean {
	private String processId;
	private BaseProNode startNode;
	private Map<String,BaseProNode> nodes;
	
	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @return the startNode
	 */
	public BaseProNode getStartNode() {
		return startNode;
	}
	/**
	 * @param startNode the startNode to set
	 */
	public void setStartNode(BaseProNode startNode) {
		this.startNode = startNode;
	}
	/**
	 * @return the nodes
	 */
	public Map<String, BaseProNode> getNodes() {
		return nodes;
	}
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(Map<String, BaseProNode> nodes) {
		this.nodes = nodes;
	}
}
