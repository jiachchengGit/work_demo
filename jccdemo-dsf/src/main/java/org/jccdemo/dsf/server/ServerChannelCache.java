/**   
* @Title: ClientChannelFactory.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午4:17:31 
* @version V1.0   
*/
package org.jccdemo.dsf.server;

import java.util.HashMap;
import java.util.Map;

import org.jccdemo.dsf.config.ServerConfig;

import io.netty.channel.socket.SocketChannel;

/** 
 * @ClassName: ClientChannelFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午4:17:31 
 *  
 */
public class ServerChannelCache {
	
	private static Map<String,SocketChannel> channels = new HashMap<String,SocketChannel>();

	/**
	 * @return the channel
	 */
	public static SocketChannel getChannel(String clazzName) {
		return channels.get(clazzName);
	}

	/**
	 * @param channel the channel to set
	 * @param sc 
	 */
	public static void setChannel(SocketChannel channel, String clazzName) {
		channels.put(clazzName, channel);
	}
	
}
