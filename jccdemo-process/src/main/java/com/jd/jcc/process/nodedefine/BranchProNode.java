/**   
* @Title: BranchProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:49:45 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

import java.util.ArrayList;
import java.util.List;

import com.jd.jcc.process.model.ProNodeTypeEnum;

/** 
 * @ClassName: BranchProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:49:45 
 *  
 */
public class BranchProNode extends BaseProNode {
	
	private BaseProNode parentNode;
	private List<BaseProNode> nextNodes;
	
	private List<BranchNodeItem> items;
	
	public BranchProNode() {
		super(ProNodeTypeEnum.branch.name());
		nextNodes = new ArrayList<BaseProNode>();
	}
	
	/**
	 * @return the items
	 */
	public List<BranchNodeItem> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(List<BranchNodeItem> items) {
		this.items = items;
	}


	/**
	 * @return the parentNode
	 */
	public BaseProNode getParentNode() {
		return parentNode;
	}
	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(BaseProNode parentNode) {
		this.parentNode = parentNode;
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
		this.parentNode = node;
	}
	
}
