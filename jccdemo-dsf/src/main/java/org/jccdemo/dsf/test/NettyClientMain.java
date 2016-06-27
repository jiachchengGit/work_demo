/**   
 * @Title: NettyClientMain.java 
 * @Package org.jccdemo.dsf.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年6月17日 下午2:44:04 
 * @version V1.0   
 */
package org.jccdemo.dsf.test;

import org.jccdemo.dsf.client.NettyClientConnect;
import org.jccdemo.dsf.invoke.SendMsgMethodInvoker;
import org.jccdemo.dsf.test.bean.HeatReq;
import org.jccdemo.dsf.test.bean.HeatResp;
import org.jccdemo.dsf.test.interfaces.HeatBeatService;
import org.jccdemo.dsf.utils.JDKProxy;

/**
 * @ClassName: NettyClientMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:44:04
 * 
 */
public class NettyClientMain {
	public static void main(String[] args) throws InterruptedException {
		TestClientRegisterHook hook = new TestClientRegisterHook();
		NettyClientConnect.connectServers(hook);		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					System.out.println("-----send msg to server ----");
					HeatBeatService proxy = JDKProxy.getProxy(HeatBeatService.class, new SendMsgMethodInvoker());
					HeatReq req= new HeatReq();
					req.setAsk("Hello server ,this is client handshake to you");
					HeatResp sayHello = proxy.sayHello(req);
					System.out.println("method call result:"+(sayHello==null?"no response":sayHello.toString()));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
	}
}
