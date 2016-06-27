/**   
* @Title: MethodCallTask.java 
* @Package org.jccdemo.dsf.server 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月20日 下午3:30:47 
* @version V1.0   
*/
package org.jccdemo.dsf.server;

import org.jccdemo.dsf.model.MsgBody;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.queue.ServerRequstQueue;
import org.jccdemo.dsf.test.bean.HeatResp;
import org.jccdemo.dsf.utils.ThreadPoolUtils;

/** 
 * @ClassName: MethodCallTask 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月20日 下午3:30:47 
 *  
 */
public class ServerTaskDistributeThread extends Thread {
	
	public void run() {
		while(true){
			try{
				RequestMsg ele = ServerRequstQueue.getEle();
				ThreadPoolUtils.execute(new MethodTask(ele));
			}catch(Exception e){
				
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
		String clazzName = msg.getInvocation().getClazzName();
		System.out.println("--处理客户端信息--"+clazzName);
		ResponseMsg resp = new ResponseMsg();
		MsgBody body = new MsgBody();
		HeatResp hr = new HeatResp();
		hr.setAnswer("--This is server response to client msg---");
		body.setMsg(hr);
		resp.setBody(body);
		ServerChannelCache.responseMsg(resp, clazzName, msg.getMsgId());
	}
}