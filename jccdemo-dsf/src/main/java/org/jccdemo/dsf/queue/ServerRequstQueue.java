/**   
* @Title: NIOQueue.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:25:07 
* @version V1.0   
*/
package org.jccdemo.dsf.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jccdemo.dsf.model.RequestMsg;

/** 
 * @ClassName: NIOQueue 
 * @Description: 服务端等待处理 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:25:07 
 *  
 */
public class ServerRequstQueue {
	
	private static final BlockingQueue<RequestMsg> queue = new LinkedBlockingQueue<RequestMsg>(10240);
	private ServerRequstQueue(){}
	public static RequestMsg getEle() throws Exception{
		RequestMsg poll = queue.take();
		return poll;
	}
	
	public static void putEle(RequestMsg ele){
		queue.offer(ele);
	}
}
