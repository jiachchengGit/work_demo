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
	
	private String refNodeId;
	
	private String expression;
	
	private BranchExpression branchExpression;
	
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
	 * @return the refNodeId
	 */
	public String getRefNodeId() {
		return refNodeId;
	}
	/**
	 * @param refNodeId the refNodeId to set
	 */
	public void setRefNodeId(String refNodeId) {
		this.refNodeId = refNodeId;
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
