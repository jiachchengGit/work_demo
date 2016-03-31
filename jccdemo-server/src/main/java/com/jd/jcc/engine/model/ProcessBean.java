/**   
* @Title: ProcessBean.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:40:40 
* @version V1.0   
*/
package com.jd.jcc.engine.model;

import java.util.Map;

import com.jd.jcc.engine.nodedefine.AbstractProNode;

/** 
 * @ClassName: ProcessBean 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午2:40:40 
 *  
 */
public class ProcessBean {
	private AbstractProNode startNode;
	private Map<String,AbstractProNode> nodes;
	/**
	 * @return the startNode
	 */
	public AbstractProNode getStartNode() {
		return startNode;
	}
	/**
	 * @param startNode the startNode to set
	 */
	public void setStartNode(AbstractProNode startNode) {
		this.startNode = startNode;
	}
	/**
	 * @return the nodes
	 */
	public Map<String, AbstractProNode> getNodes() {
		return nodes;
	}
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(Map<String, AbstractProNode> nodes) {
		this.nodes = nodes;
	}
}
