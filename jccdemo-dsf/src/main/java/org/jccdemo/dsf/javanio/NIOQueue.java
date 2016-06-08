/**   
* @Title: NIOQueue.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:25:07 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
 * @ClassName: NIOQueue 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:25:07 
 *  
 */
public class NIOQueue {
	
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10240);
	
	public static String getEle() throws Exception{
		return queue.take();
	}
	
	public static void putEle(String ele){
		queue.offer(ele);
	}
	
}
