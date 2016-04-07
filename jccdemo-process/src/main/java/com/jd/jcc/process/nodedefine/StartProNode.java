/**   
* @Title: StartProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午1:38:26 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

import com.jd.jcc.process.model.ProNodeTypeEnum;

/** 
 * @ClassName: StartProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午1:38:26 
 *  
 */
public class StartProNode extends BaseProNode {
	
	private BaseProNode nextNode;
	
	public StartProNode() {
		super(ProNodeTypeEnum.start.name());
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
		
	}
}
