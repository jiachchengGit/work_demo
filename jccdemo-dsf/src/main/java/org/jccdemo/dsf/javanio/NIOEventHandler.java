/**   
* @Title: NIOEventHandler.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 下午5:41:51 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/** 
 * @ClassName: NIOEventHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 下午5:41:51 
 *  
 */
public class NIOEventHandler implements Runnable {
	private SelectionKey key;
	public NIOEventHandler(SelectionKey key){
		this.key=key;
	}
	
	public void run() {
		System.out.println("key = "+key.hashCode());
		SocketChannel channel = (SocketChannel)key.channel();
		ByteBuffer bb = ByteBuffer.allocate(1024);
		try {
			channel.read(bb);
			System.out.println("read msg from client="+bb.array());
			bb.clear();
			channel.write(ByteBuffer.wrap("server send msg to client".getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
