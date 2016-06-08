/**   
* @Title: MsgProducer.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:29:43 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio;

/** 
 * @ClassName: MsgProducer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:29:43 
 *  
 */
public class MsgProducer {

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月8日 上午9:29:43
	 * @param args        
	 * @throws 
	 */
	public static void produce() {
		new Thread(){
			long i=0;
			String s="msg from client : ";
			@Override
			public void run() {
				while(true){
					NIOQueue.putEle(s+i);
					i++;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
			
			
		}.start();
	}

}
