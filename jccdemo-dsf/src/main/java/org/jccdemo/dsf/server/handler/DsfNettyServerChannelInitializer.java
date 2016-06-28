/**   
* @Title: DsfNettyClientChannelInitializer.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:06:00 
* @version V1.0   
*/
package org.jccdemo.dsf.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DsfNettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	private Logger log  = LoggerFactory.getLogger(getClass());
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		log.info("[Server]-init server Channel Handler -- ");
		ch.pipeline().addLast(new ObjectEncoder()
		,new ObjectDecoder(ClassResolvers.cacheDisabled(null))
		,new DsfNettyServerHandler());
	}
}
