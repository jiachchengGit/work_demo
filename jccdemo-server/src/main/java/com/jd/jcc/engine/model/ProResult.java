/**   
* @Title: ProResult.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:38:08 
* @version V1.0   
*/
package com.jd.jcc.engine.model;

import com.jd.jcc.engine.nodedefine.BaseProNode;

/** 
 * @ClassName: ProResult 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @param <T>
 * @date 2016年3月31日 下午2:38:08 
 *  
 */
public class ProResult {
	
	private Object result;

	private BaseProNode node;
	
	/**
	 * @return the node
	 */
	public BaseProNode getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(BaseProNode node) {
		this.node = node;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	
}
