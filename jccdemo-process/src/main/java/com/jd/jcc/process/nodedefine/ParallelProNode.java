/**   
* @Title: ParallelProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:50:16 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

import java.util.ArrayList;
import java.util.List;

import com.jd.jcc.process.model.ProNodeTypeEnum;

/** 
 * @ClassName: ParallelProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:50:16 
 *  
 */
public class ParallelProNode extends BaseProNode {
	
	private List<BaseProNode> parentNodes;
	private List<BaseProNode> nextNodes;
	private List<ParallelLineItem> lineItems;
	
	public ParallelProNode() {
		super(ProNodeTypeEnum.parallel.name());
		nextNodes = new ArrayList<BaseProNode>();
		parentNodes = new ArrayList<BaseProNode>();
	}
	
	/**
	 * @return the lineItems
	 */
	public List<ParallelLineItem> getLineItems() {
		return lineItems;
	}


	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<ParallelLineItem> lineItems) {
		this.lineItems = lineItems;
	}



	/**
	 * @return the parentNodes
	 */
	public List<BaseProNode> getParentNodes() {
		return parentNodes;
	}

	/**
	 * @param parentNodes the parentNodes to set
	 */
	public void setParentNodes(List<BaseProNode> parentNodes) {
		this.parentNodes = parentNodes;
	}

	/**
	 * @return the nextNodes
	 */
	public List<BaseProNode> getNextNodes() {
		return nextNodes;
	}
	/**
	 * @param nextNodes the nextNodes to set
	 */
	public void setNextNodes(List<BaseProNode> nextNodes) {
		this.nextNodes = nextNodes;
	}

	@Override
	public void addChildNode(BaseProNode node) {
		this.nextNodes.add(node);
	}

	@Override
	public void addParentNode(BaseProNode node) {
		this.parentNodes.add(node);
	}
}
