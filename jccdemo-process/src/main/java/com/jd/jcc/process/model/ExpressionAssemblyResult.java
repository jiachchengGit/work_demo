/**   
* @Title: ExpressionAssemblyResult.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午4:23:38 
* @version V1.0   
*/
package com.jd.jcc.process.model;

import java.util.Map;

import com.jccdemo.expression.VariableType;

/** 
 * @ClassName: ExpressionAssemblyResult 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午4:23:38 
 *  
 */
public class ExpressionAssemblyResult {
	private String expression;
	private Map<String,VariableType> values;
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
	/**
	 * @return the values
	 */
	public Map<String, VariableType> getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(Map<String, VariableType> values) {
		this.values = values;
	}
}
