/**   
* @Title: SocketHandler.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月7日 下午4:35:18 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/** 
 * @ClassName: SocketHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月7日 下午4:35:18 
 *  
 */
public class SocketHandler {
	private SelectionKey key;
	private SocketChannel channel;
	public SocketHandler(SelectionKey key) {
		this.key = key;
		this.channel = (SocketChannel)key.channel();
	}
	
	public void handerRead() throws IOException{
		System.out.println("---开始读消息------");
		ByteBuffer buf = ByteBuffer.allocate(1024);
		long bytesRead=channel.read(buf);
		System.out.println("-- content ="+new String(buf.array()));
		System.out.println("---结束读消息------");
        if(bytesRead==-1) {  
            channel.close();  
        } else {  
            key.interestOps( SelectionKey.OP_READ | SelectionKey.OP_WRITE );  
        }  
	}
	
	public void handerWrite(String src) throws IOException{
		if(src != null){
			byte[] bytes = src.getBytes();
			ByteBuffer buf = ByteBuffer.allocate(bytes.length);
			buf.put(bytes);
			channel.write(buf);
		}
	}
}
