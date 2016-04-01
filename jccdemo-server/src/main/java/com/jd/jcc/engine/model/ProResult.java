/**   
* @Title: ProResult.java 
* @Package com.jd.jcc.engine.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:38:08 
* @version V1.0   
*/
package com.jd.jcc.engine.model;

/** 
 * @ClassName: ProResult 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @param <T>
 * @date 2016年3月31日 下午2:38:08 
 *  
 */
public class ProResult<T> {
	
	private T result;

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}
	
}
