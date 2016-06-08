/**   
* @Title: NIOServer.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 下午5:38:12 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/** 
 * @ClassName: NIOServer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 下午5:38:12 
 *  
 */
public class NIOServer {
	
	public static void main(String[] args) throws IOException{
		NIOServer ns = new NIOServer(8088);
		ns.startService();
	}
	
	private final Selector selector; 
	private final ServerSocketChannel ssc;
	public NIOServer(int port) throws IOException{
		selector = Selector.open();
		ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(8088));
		SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);	
		key.attach(new NIOEventHandler(key));
		System.out.println("---init server channel---");
	}
	
	public void startService() throws IOException{
		System.out.println("---start server ---");
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				if(key.isAcceptable()){
					dispatch(key,ssc);
				}
			}
		}
	}

	/**
	 * @throws IOException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月8日 下午5:48:53
	 * @param key        
	 * @throws 
	 */
	private void dispatch(SelectionKey key,ServerSocketChannel ssc) throws IOException {
		SocketChannel sc = ssc.accept();
		if(sc != null){
			System.out.println("dispatch:--");
			sc.configureBlocking(false);
			sc.register(key.selector(), SelectionKey.OP_READ);
			key.selector().wakeup();
			Runnable run = (Runnable)key.attachment();
			run.run();
		}
	}
}
