/**   
* @Title: ServerMain.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月7日 下午4:01:57 
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
 * @ClassName: ServerMain 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月7日 下午4:01:57 
 *  
 */
public class ServerMain {

	/**
	 * @throws IOException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月7日 下午4:01:57
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(8088));
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("---Start server---");
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey next = iterator.next();
				iterator.remove();
				if(next.isAcceptable()){
					SocketChannel accept = ssc.accept();
					accept.configureBlocking(false);
					SelectionKey key = accept.register(selector, SelectionKey.OP_READ);
					key.attach(new SocketHandler(key));
				}
				if(next.isReadable()){
					SocketHandler sh = (SocketHandler)next.attachment();
					sh.handerRead();
				}
				
				if(next.isValid() && next.isWritable()){
					SocketHandler sh = (SocketHandler)next.attachment();
					sh.handerWrite("this is server respone for");
				}
			}
		}
	}

}
