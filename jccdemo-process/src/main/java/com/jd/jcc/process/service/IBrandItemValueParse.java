/**   
* @Title: BranchItemValueParse.java 
* @Package com.jd.jcc.engine.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午5:21:03 
* @version V1.0   
*/
package com.jd.jcc.process.service;

import java.util.Map;

import com.jcc.demo.expression.VariableType;

/** 
 * @ClassName: BranchItemValueParse 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午5:21:03 
 *  
 */
public interface IBrandItemValueParse {
	public Map<String, VariableType> parseVariableValue(Map<String, VariableType> codeValues, Object requestParam);
}
