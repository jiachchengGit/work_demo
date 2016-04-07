/**   
* @Title: SubProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:01:30 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

import com.jd.jcc.process.model.ProNodeTypeEnum;

/** 
 * @ClassName: SubProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午2:01:30 
 *  
 */
public class SubProNode extends BaseProNode {
	//子流程ID
	private String subProcessId;
	private BaseProNode nextNode;
	
	public SubProNode() {
		super(ProNodeTypeEnum.sub_process.name());
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


	/**
	 * @return the subProcessId
	 */
	public String getSubProcessId() {
		return subProcessId;
	}


	/**
	 * @param subProcessId the subProcessId to set
	 */
	public void setSubProcessId(String subProcessId) {
		this.subProcessId = subProcessId;
	}

	@Override
	public void addChildNode(BaseProNode node) {
		this.nextNode = node;
	}

	@Override
	public void addParentNode(BaseProNode node) {
		
	}
}
