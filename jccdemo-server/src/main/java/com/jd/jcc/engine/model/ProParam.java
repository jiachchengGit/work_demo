/**   
* @Title: ProParam.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:38:26 
* @version V1.0   
*/
package com.jd.jcc.engine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: ProParam 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @param <P>
 * @date 2016年3月31日 下午2:38:26 
 *  
 */
public class ProParam {
	private ProResult result;
	private ProContext  proContext = new ProContext();
	private Object requestParam;
	
	/**
	 * @return the proContext
	 */
	public ProContext getProContext() {
		return proContext;
	}


	/**
	 * @param proContext the proContext to set
	 */
	public void setProContext(ProContext proContext) {
		this.proContext = proContext;
	}


	/**
	 * @return the requestParam
	 */
	public Object getRequestParam() {
		return requestParam;
	}


	/**
	 * @param requestParam the requestParam to set
	 */
	public void setRequestParam(Object requestParam) {
		this.requestParam = requestParam;
	}


	/**
	 * @return the result
	 */
	public ProResult getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(ProResult result) {
		if(result != null){
			this.result = result;
			this.proContext.getResults().add(result);
		}
	}

	
}
