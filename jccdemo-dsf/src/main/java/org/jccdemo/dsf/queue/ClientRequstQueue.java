/**   
* @Title: NIOQueue.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:25:07 
* @version V1.0   
*/
package org.jccdemo.dsf.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jccdemo.dsf.model.RequestMsg;

/** 
 * @ClassName: NIOQueue 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:25:07 
 *  
 */
public class ClientRequstQueue {
	
	private static final BlockingQueue<RequestMsg> queue = new LinkedBlockingQueue<RequestMsg>(10240);
	private ClientRequstQueue(){}
	public static RequestMsg getEle() throws Exception{
		RequestMsg poll = queue.poll();
		return poll;
	}
	
	public static void putEle(RequestMsg ele){
		queue.offer(ele);
	}
	
	private static final Map<String,RequestMsg> waitRespone = new HashMap<String,RequestMsg>();
	
	public static void saveWaitRespone(RequestMsg request){
		String msgId = request.getHearder().getMsgId();
		waitRespone.put(msgId, request);
	}
	
	public static RequestMsg getWaitRespone(String msgId){
		return waitRespone.get(msgId);
	}
}
