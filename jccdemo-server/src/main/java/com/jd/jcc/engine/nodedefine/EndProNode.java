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
public class EndProNode extends AbstractProNode {

	private List<AbstractProNode> parentNodes;
	
	public EndProNode() {
		super(ProNodeTypeEnum.end.name());
		parentNodes = new ArrayList<AbstractProNode>();
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
