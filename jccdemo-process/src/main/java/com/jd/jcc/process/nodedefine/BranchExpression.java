/**   
* @Title: BranchExpression.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午3:48:39 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

import java.util.List;

/** 
 * @ClassName: BranchExpression 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午3:48:39 
 *  
 */
public class BranchExpression {
	public static final String TYPE_RULE = "rule";
	public static final String TYPE_EXPRESSION = "expression";

	private String expressionType;
	private String script;
	private String ruleName;
	private List<BranchExpressionItem> expressionItems;
	/**
	 * @return the expressionType
	 */
	public String getExpressionType() {
		return expressionType;
	}
	/**
	 * @param expressionType the expressionType to set
	 */
	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
	}
	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}
	/**
	 * @param script the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}
	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}
	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	/**
	 * @return the expressionItems
	 */
	public List<BranchExpressionItem> getExpressionItems() {
		return expressionItems;
	}
	/**
	 * @param expressionItems the expressionItems to set
	 */
	public void setExpressionItems(List<BranchExpressionItem> expressionItems) {
		this.expressionItems = expressionItems;
	}
}
