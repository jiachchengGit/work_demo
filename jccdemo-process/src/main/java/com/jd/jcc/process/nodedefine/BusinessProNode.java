/**   
* @Title: BusinessProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:49:22 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

import java.util.ArrayList;
import java.util.List;

import com.jd.jcc.process.model.ProNodeTypeEnum;

/** 
 * @ClassName: BusinessProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:49:22 
 *  
 */
public class BusinessProNode extends BaseProNode {

	private List<BaseProNode> parentNodes;
	
	private BaseProNode nextNode;
	
	public BusinessProNode() {
		super(ProNodeTypeEnum.business.name());
		this.parentNodes = new ArrayList<BaseProNode>();
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
	 * @return the nextNode
	 */
	public BaseProNode getNextNode() {
		return nextNode;
	}

	/**
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(BaseProNode nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public void addChildNode(BaseProNode node) {
		this.nextNode = node;
	}

	@Override
	public void addParentNode(BaseProNode node) {
		this.parentNodes.add(node);
	}
	
}
