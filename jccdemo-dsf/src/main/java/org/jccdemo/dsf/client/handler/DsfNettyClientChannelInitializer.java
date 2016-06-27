/**   
* @Title: DsfNettyClientChannelInitializer.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:06:00 
* @version V1.0   
*/
package org.jccdemo.dsf.client.handler;

import org.jccdemo.dsf.client.ClientChannelCache;
import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.config.ServerConfig;

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
	private ServerConfig sc;
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @author chenjiacheng
	 * @Date 2016年6月18日 下午3:19:33
	 * @param sc 
	 */
	public DsfNettyClientChannelInitializer(ServerConfig sc) {
		this.sc = sc;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ObjectEncoder()
		,new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader()))
		,new DsfNettyClientHandler());
		ClientChannelCache.setChannel(ch,sc);
		sc.setStatus(DsfConst.ServerStatus.RUN);
	}
}
