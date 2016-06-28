/**   
* @Title: MethodCallTask.java 
* @Package org.jccdemo.dsf.server 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月20日 下午3:30:47 
* @version V1.0   
*/
package org.jccdemo.dsf.server;

import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.queue.ServerRequstQueue;
import org.jccdemo.dsf.server.impl.ServerMsgHandlerFactory;
import org.jccdemo.dsf.utils.ThreadPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: MethodCallTask 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月20日 下午3:30:47 
 *  
 */
public class ServerTaskDistributeThread extends Thread {
	private Logger log = LoggerFactory.getLogger(getClass());
	public void run() {
		while(true){
			try{
				RequestMsg ele = ServerRequstQueue.getEle();
				ThreadPoolUtils.execute(new MethodTask(ele));
			}catch(Exception e){
				log.error("[Server]-handler client reqeust error.",e);
			}
			
		}
	}
}

class MethodTask implements Runnable{
	private RequestMsg msg; 
	public MethodTask(RequestMsg msg){
		this.msg = msg;
	}
	
	public void run() {
		ResponseMsg handler = ServerMsgHandlerFactory.handler(msg);
		String clazzName = msg.getInvocation().getClazzName();
		ServerChannelCache.responseMsg(handler, clazzName, msg.getMsgId());
	}
}