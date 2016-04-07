/**   
* @Title: ProcessNodeService.java 
* @Package com.jd.jcc.engine.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 上午9:53:15 
* @version V1.0   
*/
package com.jd.jcc.process.service;

import com.jd.jcc.process.model.ProcessBean;

/** 
 * @ClassName: ProcessNodeService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 上午9:53:15 
 *  
 */
public interface IProcessNodeService {
	public ProcessBean queryProcessBeanById(String processId);
}
