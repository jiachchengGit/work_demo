/**   
* @Title: BusinessNodeServiceImpl.java 
* @Package com.jd.jcc.process.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月7日 下午7:40:36 
* @version V1.0   
*/
package com.jd.jcc.process.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jcc.process.nodedefine.BaseProNode;

/** 
 * @ClassName: BusinessNodeServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月7日 下午7:40:36 
 *  
 */
public class SimpleBusinessNodeServiceImpl implements BusinessNodeService {
	private Logger log = LoggerFactory.getLogger(getClass());
	public Object excuteService(BaseProNode node, Object requestParam) {
		log.info("***#执行业务节点#***,nodeName="+node.getNodeName());
		return "这是业务节点name="+node.getNodeName();
	}

}
