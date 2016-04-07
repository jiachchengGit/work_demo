/**   
* @Title: IBusinessNodeService.java 
* @Package com.jd.jcc.engine.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 下午5:38:54 
* @version V1.0   
*/
package com.jd.jcc.process.service;

import com.jd.jcc.process.nodedefine.BaseProNode;

/** 
 * @ClassName: IBusinessNodeService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 下午5:38:54 
 *  
 */
public interface IBusinessNodeService {

	/** 
	 * @Description: 业务节点执行接口 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 下午5:40:44
	 * @param node
	 * @param requestParam
	 * @return        
	 * @throws 
	 */
	public Object excuteService(BaseProNode node, Object requestParam);

}
