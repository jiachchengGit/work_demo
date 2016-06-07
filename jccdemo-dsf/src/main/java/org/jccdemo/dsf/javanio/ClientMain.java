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
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		sc.connect(new InetSocketAddress("127.0.0.1", 8088));
		Selector selector = Selector.open();
		sc.register(selector, SelectionKey.OP_CONNECT);
		while (true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				keyIterator.remove();
				if (key.isConnectable()) { 
                    sc.finishConnect(); 
                    sc.register(selector, SelectionKey.OP_READ); 
                    SocketChannel ch = (SocketChannel)key.channel();
                    ch.write(ByteBuffer.wrap(" begin to connet server".getBytes()));
                    key.attach(new SocketHandler(key));
                    System.out.println("client connected server ..."); 
                    break; 
                }
				if(key.isReadable()){
					SocketHandler sh = (SocketHandler)key.attachment();
					sh.handerRead();
					System.out.println("--client begin to send msg");
					sh.handerWrite("this is client send msg to server,id=");
				}
			}
		}
	}

}
