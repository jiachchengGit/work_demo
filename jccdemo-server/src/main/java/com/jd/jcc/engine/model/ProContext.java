/**   
* @Title: ProContext.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午3:29:55 
* @version V1.0   
*/
package com.jd.jcc.engine.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: ProContext 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午3:29:55 
 *  
 */
public class ProContext {
	
	private List<ProResult> results = new ArrayList<ProResult>();
	/**
	 * @return the results
	 */
	public List<ProResult> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<ProResult> results) {
		this.results = results;
	}
}
