/**   
* @Title: AbstractProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:47:22 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

/** 
 * @ClassName: AbstractProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:47:22 
 *  
 */
public class BaseProNode extends AbstractProNode{
	
	protected String nodeId;//当前节点ID
	protected String refNodeId;//复制来自其他定义好的节点ID
	protected String nodeName;
	protected String nodeType;
	protected String nodeKey;
	protected String parentKey;
	protected String nodeStatus;//0-暂停；1-激活
	protected String processId;
	
	public BaseProNode(){}
	
	public BaseProNode(String nodeType){
		this.nodeType = nodeType;
	}
	
	/**
	 * @return the parentKey
	 */
	public String getParentKey() {
		return parentKey;
	}

	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}


	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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
	 * @return the nodeStatus
	 */
	public String getNodeStatus() {
		return nodeStatus;
	}


	/**
	 * @param nodeStatus the nodeStatus to set
	 */
	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}


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
	 * @return the nodeKey
	 */
	public String getNodeKey() {
		return nodeKey;
	}


	/**
	 * @param nodeKey the nodeKey to set
	 */
	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}


	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @return the nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}
	/**
	 * @param nodeType the nodeType to set
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public void addChildNode(BaseProNode node) {
		throw new RuntimeException("must over right the addChildNode method");
	}

	@Override
	public void addParentNode(BaseProNode node) {
		throw new RuntimeException("must over right the addParentNode method");
	}
}
