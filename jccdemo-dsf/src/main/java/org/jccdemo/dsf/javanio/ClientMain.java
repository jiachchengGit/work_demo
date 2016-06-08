/**   
 * @Title: ClientMain.java 
 * @Package org.jccdemo.dsf.javanio 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年6月7日 下午4:01:48 
 * @version V1.0   
 */
package org.jccdemo.dsf.javanio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: ClientMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年6月7日 下午4:01:48
 * 
 */
public class ClientMain {

	/**
	 * @throws IOException 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @Author chenjiacheng
	 * @Date 2016年6月7日 下午4:01:48
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) throws IOException {
		String threadName="threadName";
		Selector selector = Selector.open();
		for(int i=0;i<10;i++){
			SocketChannel sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("127.0.0.1", 8088));
			sc.register(selector, SelectionKey.OP_CONNECT);
			System.out.println(i+" sc="+sc.hashCode());
		}
		while (true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				keyIterator.remove();
				if (key.isConnectable()) {
					SocketChannel sc1 = (SocketChannel)key.channel();
					threadName=sc1.hashCode()+",";
                    sc1.finishConnect(); 
                    sc1.register(selector, SelectionKey.OP_READ); 
                    SocketChannel ch = (SocketChannel)key.channel();
                    ch.write(ByteBuffer.wrap((threadName+", begin to connet server").getBytes()));
                    System.out.println(threadName+"client connected server ..."); 
                    break; 
                }
				if(key.isReadable()){
					SocketChannel channel1 = (SocketChannel)key.channel();
					threadName=channel1.hashCode()+",";
					ByteBuffer buf = ByteBuffer.allocate(1024);
					long bytesRead=channel1.read(buf);
					System.out.println(threadName+"read from server ="+new String(buf.array()));
			        if(bytesRead==-1) {  
			            channel1.close();  
			        } else {  
			        	channel1.register(key.selector(),SelectionKey.OP_READ | SelectionKey.OP_WRITE );  
			        }  
				}
				if(key.isWritable()){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					SocketChannel channel2 = (SocketChannel)key.channel();
					threadName=channel2.hashCode()+",";
					String src = threadName+"test client msg to server ";
					channel2.write(ByteBuffer.wrap(src.getBytes()));
					channel2.register(key.selector(),SelectionKey.OP_READ | SelectionKey.OP_WRITE );  
				}
			}
		}
	}
}
