/**   
* @Title: NIOQueue.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:25:07 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio.tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.jccdemo.dsf.javanio.model.ResponseMsg;

/** 
 * @ClassName: NIOQueue 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:25:07 
 *  
 */
public class ServerResponseQueue {
	
	private static BlockingQueue<ResponseMsg> queue = new ArrayBlockingQueue<ResponseMsg>(10240);
	
	public static ResponseMsg getEle() throws Exception{
		return queue.poll();
	}
	
	public static void putEle(ResponseMsg ele){
		queue.offer(ele);
	}
	
}
