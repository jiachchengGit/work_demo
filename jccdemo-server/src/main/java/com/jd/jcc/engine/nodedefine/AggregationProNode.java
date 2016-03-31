/**   
* @Title: AggregationProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:51:09 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

import java.util.ArrayList;
import java.util.List;

import com.jd.jcc.engine.model.ProNodeTypeEnum;

/** 
 * @ClassName: AggregationProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:51:09 
 *  
 */
public class AggregationProNode extends AbstractProNode {

	private AbstractProNode nextNode;
	
	private List<AbstractProNode> parentNodes;
	
	public AggregationProNode() {
		super(ProNodeTypeEnum.aggregation.name());
		parentNodes = new ArrayList<AbstractProNode>();
	}

	/**
	 * @return the nextNode
	 */
	public AbstractProNode getNextNode() {
		return nextNode;
	}

	/**
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(AbstractProNode nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * @return the parentNodes
	 */
	public List<AbstractProNode> getParentNodes() {
		return parentNodes;
	}

	/**
	 * @param parentNodes the parentNodes to set
	 */
	public void setParentNodes(List<AbstractProNode> parentNodes) {
		this.parentNodes = parentNodes;
	}
}
