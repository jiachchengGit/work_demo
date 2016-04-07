/**   
* @Title: ExpressionItem.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午3:49:00 
* @version V1.0   
*/
package com.jd.jcc.process.nodedefine;

/** 
 * @ClassName: ExpressionItem 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午3:49:00 
 *  
 */
public class BranchExpressionItem {
	
	private String expleft;
	private String symbol;
	private String expright;
	private String connector;
	/**
	 * @return the expleft
	 */
	public String getExpleft() {
		return expleft;
	}
	/**
	 * @param expleft the expleft to set
	 */
	public void setExpleft(String expleft) {
		this.expleft = expleft;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the expright
	 */
	public String getExpright() {
		return expright;
	}
	/**
	 * @param expright the expright to set
	 */
	public void setExpright(String expright) {
		this.expright = expright;
	}
	/**
	 * @return the connector
	 */
	public String getConnector() {
		return connector;
	}
	/**
	 * @param connector the connector to set
	 */
	public void setConnector(String connector) {
		this.connector = connector;
	}
}
