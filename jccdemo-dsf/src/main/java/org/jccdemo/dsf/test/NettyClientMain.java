/**   
 * @Title: NettyClientMain.java 
 * @Package org.jccdemo.dsf.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年6月17日 下午2:44:04 
 * @version V1.0   
 */
package org.jccdemo.dsf.test;

import io.netty.channel.socket.SocketChannel;

import org.jccdemo.dsf.model.HeartBeat;
import org.jccdemo.dsf.netty.ClientChannelFactory;
import org.jccdemo.dsf.netty.DsfNettyClient;
import org.jccdemo.dsf.utils.CreateBeanUtils;

/**
 * @ClassName: NettyClientMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:44:04
 * 
 */
public class NettyClientMain {
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					System.out.println("-----send msg to server ----");
					SocketChannel channel = ClientChannelFactory.getChannel();
					if(channel != null){
						HeartBeat msg = CreateBeanUtils.createClientMsg();
						channel.writeAndFlush(msg);
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		DsfNettyClient dc = new DsfNettyClient();
		dc.start();
	}
}
