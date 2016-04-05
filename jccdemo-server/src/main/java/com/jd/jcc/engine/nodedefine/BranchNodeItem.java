/**   
* @Title: NodeBranchItem.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午3:01:53 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

/** 
 * @ClassName: NodeBranchItem 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午3:01:53 
 *  
 */
public class BranchNodeItem {
	
	private String id;
	
	private String refNodeKey;
	
	private String expression;
	
	private String nextNodeKey;
	
	private BranchExpression branchExpression;
	
	/**
	 * @return the nextNodeKey
	 */
	public String getNextNodeKey() {
		return nextNodeKey;
	}
	/**
	 * @param nextNodeKey the nextNodeKey to set
	 */
	public void setNextNodeKey(String nextNodeKey) {
		this.nextNodeKey = nextNodeKey;
	}
	/**
	 * @return the branchExpression
	 */
	public BranchExpression getBranchExpression() {
		return branchExpression;
	}
	/**
	 * @param branchExpression the branchExpression to set
	 */
	public void setBranchExpression(BranchExpression branchExpression) {
		this.branchExpression = branchExpression;
	}
	/**
	 * @return the refNodeKey
	 */
	public String getRefNodeKey() {
		return refNodeKey;
	}
	/**
	 * @param refNodeKey the refNodeKey to set
	 */
	public void setRefNodeKey(String refNodeKey) {
		this.refNodeKey = refNodeKey;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
