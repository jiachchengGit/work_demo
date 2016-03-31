/**   
* @Title: BusinessProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午11:49:22 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

import com.jd.jcc.engine.model.ProNodeTypeEnum;

/** 
 * @ClassName: BusinessProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午11:49:22 
 *  
 */
public class BusinessProNode extends AbstractProNode {

	private AbstractProNode parentNode;
	
	private AbstractProNode nextNode;
	
	public BusinessProNode() {
		super(ProNodeTypeEnum.business.name());
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
}
