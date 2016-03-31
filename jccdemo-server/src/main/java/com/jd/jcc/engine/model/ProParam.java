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
 * @date 2016年3月31日 下午2:38:26 
 *  
 */
public class ProParam {
	private ProResult result;
	private List<ProResult> results = new ArrayList<ProResult>();
	private Map<String,ProResult> parallelResults = new HashMap<String,ProResult>();
	private List<String> aggregationKey = new ArrayList<String>();

	/**
	 * @return the aggregationKey
	 */
	public List<String> getAggregationKey() {
		return aggregationKey;
	}


	/**
	 * @param aggregationKey the aggregationKey to set
	 */
	public void setAggregationKey(List<String> aggregationKey) {
		this.aggregationKey = aggregationKey;
	}


	/**
	 * @return the results
	 */
	public List<ProResult> getResults() {
		return results;
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
			this.results.add(result);
		}
	}

	/**
	 * @return the parallelResults
	 */
	public Map<String, ProResult> getParallelResults() {
		return parallelResults;
	}

	/**
	 * @param parallelResults the parallelResults to set
	 */
	public void setParallelResults(Map<String, ProResult> parallelResults) {
		this.parallelResults = parallelResults;
	}

	
}
