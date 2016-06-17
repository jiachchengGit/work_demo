/**   
* @Title: DsfNettyClientChannelInitializer.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:06:00 
* @version V1.0   
*/
package org.jccdemo.dsf.netty.handler;

import org.jccdemo.dsf.netty.ClientChannelFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/** 
 * @ClassName: DsfNettyClientChannelInitializer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:06:00 
 *  
 */
public class DsfNettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ObjectEncoder()
		,new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader()))
		,new DsfNettyClientHandler());
		ClientChannelFactory.setChannel(ch);
	}
}
