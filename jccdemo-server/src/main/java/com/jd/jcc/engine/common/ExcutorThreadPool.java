/**   
* @Title: ExcutorThreadPool.java 
* @Package com.jd.jimi.kms.common.thread 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年9月8日 上午9:35:52 
* @version V1.0   
*/
package com.jd.jcc.engine.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: ExcutorThreadPool 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @param <V>
 * @param <T>
 * @date 2015年9月8日 上午9:35:52 
 *  
 */
public class ExcutorThreadPool<V> {
	
	private Logger log = LoggerFactory.getLogger(ExcutorThreadPool.class);
	private	int corePoolSize = 10;
	private	int maximumPoolSize = 200;
	private	long keepAliveTime = 200L;
	private int capacity = 50000;
	
	private static ExcutorThreadPool pool = new ExcutorThreadPool();
    
	private	ExecutorService es = null;
	
	private ExcutorThreadPool(){
		es =  new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(capacity));
	}
	
	public static ExcutorThreadPool getPool(){
		return pool;
	}
	/**
	 * @Description: 通过线程池执行任务
	 * @Author chenjiacheng
	 * @Date 2015年9月8日 上午10:17:36
	 * @param task
	 * @param taskKey
	 * @return        
	 * @throws
	 */
	public List<V> excuteTask(List<Callable<V>> task,String taskKey){
		List<V> list = new ArrayList<V>();
		int size = (task ==null)?0:task.size();
		if(size > 0){
			log.info(taskKey+"]开始执行线程,任务数="+size);
			try{
				List<Future<V>> listFu = new ArrayList<Future<V>>();
				for(Callable<V> t:task){
					if(t != null){
						Future<V> fu = es.submit(t);
						listFu.add(fu);
					}
				}
				for(Future<V> f:listFu){
					V v = f.get();
					if(v != null){
						list.add(v);
					}
				}
			}catch(Exception e){
				log.error(taskKey+"]线程池执行失败.",e);
			}finally{
			}
		}else{
			log.info(taskKey+"]任务队列为空");
		}
		return list;
	}
}
