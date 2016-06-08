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
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(8088));
		ssc.register(selector, SelectionKey.OP_ACCEPT);		
		System.out.println("---Start server---");
		ExecutorService pool = Executors.newFixedThreadPool(10);
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey next = iterator.next();
				iterator.remove();
				if(next.isAcceptable()){
					SocketChannel accept = ssc.accept();
					accept.configureBlocking(false);
					accept.register(selector, SelectionKey.OP_READ);
				}
				pool.submit(new ServerTask(next));
			}
		}
	}
}
class ServerTask implements Runnable{
	private SelectionKey next;
	private Selector selector;
	public ServerTask(SelectionKey next){
		this.next = next;
		this.selector=next.selector();
	}
	
	public void run() {
		try {
			if(next.isReadable()){
				SocketChannel channel = (SocketChannel)next.channel();
				ByteBuffer buf = ByteBuffer.allocate(1024);
				channel.read(buf);
				System.out.println(channel.hashCode()+",read msg from client ="+new String(buf.array()));
				channel.register(selector, SelectionKey.OP_WRITE);
			}
			if(next.isWritable()){
				SocketChannel channel = (SocketChannel)next.channel();
				byte[] src = (channel.hashCode()+", server respone to client").getBytes();
				channel.write(ByteBuffer.wrap(src));
				channel.register(selector, SelectionKey.OP_READ);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
