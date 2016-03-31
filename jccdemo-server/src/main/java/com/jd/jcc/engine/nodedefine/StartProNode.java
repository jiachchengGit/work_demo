/**   
* @Title: StartProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午1:38:26 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

import com.jd.jcc.engine.model.ProNodeTypeEnum;

/** 
 * @ClassName: StartProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午1:38:26 
 *  
 */
public class StartProNode extends AbstractProNode {
	
	private AbstractProNode nextNode;
	
	public StartProNode() {
		super(ProNodeTypeEnum.start.name());
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
