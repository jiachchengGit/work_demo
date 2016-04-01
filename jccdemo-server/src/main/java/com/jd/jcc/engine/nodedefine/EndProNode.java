/**   
* @Title: EndProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午1:38:44 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

import java.util.ArrayList;
import java.util.List;

import com.jd.jcc.engine.model.ProNodeTypeEnum;

/** 
 * @ClassName: EndProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午1:38:44 
 *  
 */
public class EndProNode extends BaseProNode {

	private List<BaseProNode> parentNodes;
	
	public EndProNode() {
		super(ProNodeTypeEnum.end.name());
		parentNodes = new ArrayList<BaseProNode>();
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

	@Override
	public void addChildNode(BaseProNode node) {
		
	}
	

	@Override
	public void addParentNode(BaseProNode node) {
		this.parentNodes.add(node);
	}
}
