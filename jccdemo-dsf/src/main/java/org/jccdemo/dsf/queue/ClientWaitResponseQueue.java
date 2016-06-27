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

import org.jccdemo.dsf.model.RequestMsg;

/** 
 * @ClassName: NIOQueue 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:25:07 
 *  
 */
public class ClientWaitResponseQueue {
	
	private static final Map<String,RequestMsg> waitRespone = new HashMap<String,RequestMsg>();
	
	public static void saveWaitRespone(RequestMsg request){
		String msgId = request.getMsgId();
		waitRespone.put(msgId, request);
	}
	
	public static RequestMsg getWaitRespone(String msgId){
		return waitRespone.get(msgId);
	}
}
