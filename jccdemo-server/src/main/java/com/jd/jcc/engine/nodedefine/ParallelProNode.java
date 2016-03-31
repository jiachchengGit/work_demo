/**   
* @Title: ParallelProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:50:16 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

import java.util.ArrayList;
import java.util.List;

import com.jd.jcc.engine.model.ProNodeTypeEnum;

/** 
 * @ClassName: ParallelProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:50:16 
 *  
 */
public class ParallelProNode extends AbstractProNode {
	
	private AbstractProNode parentNode;
	private List<AbstractProNode> nextNodes;
	
	public ParallelProNode() {
		super(ProNodeTypeEnum.parallel.name());
		nextNodes = new ArrayList<AbstractProNode>();
	}
	
	/**
	 * @return the parentNode
	 */
	public AbstractProNode getParentNode() {
		return parentNode;
	}
	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(AbstractProNode parentNode) {
		this.parentNode = parentNode;
	}
	/**
	 * @return the nextNodes
	 */
	public List<AbstractProNode> getNextNodes() {
		return nextNodes;
	}
	/**
	 * @param nextNodes the nextNodes to set
	 */
	public void setNextNodes(List<AbstractProNode> nextNodes) {
		this.nextNodes = nextNodes;
	}
}
