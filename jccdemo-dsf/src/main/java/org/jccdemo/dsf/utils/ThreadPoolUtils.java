/**   
* @Title: ThreadPoolUtils.java 
* @Package org.jccdemo.dsf.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午3:41:58 
* @version V1.0   
*/
package org.jccdemo.dsf.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * @ClassName: ThreadPoolUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午3:41:58 
 *  
 */
public class ThreadPoolUtils {
	private static ExecutorService pool = Executors.newFixedThreadPool(10);
	public static void execute(Runnable task){
		pool.execute(task);
	}
}
