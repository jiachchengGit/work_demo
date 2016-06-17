/**   
* @Title: ClientChannelFactory.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午4:17:31 
* @version V1.0   
*/
package org.jccdemo.dsf.netty;

import io.netty.channel.socket.SocketChannel;

/** 
 * @ClassName: ClientChannelFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午4:17:31 
 *  
 */
public class ClientChannelFactory {
	private static SocketChannel channel;

	/**
	 * @return the channel
	 */
	public static SocketChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public static void setChannel(SocketChannel channel) {
		ClientChannelFactory.channel = channel;
	}
}
