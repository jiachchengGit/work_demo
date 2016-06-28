/**   
* @Title: DsfNettyClientHandler.java 
* @Package org.jccdemo.dsf.netty.handler 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:23:16 
* @version V1.0   
*/
package org.jccdemo.dsf.server.handler;

import org.jccdemo.dsf.model.MethodInvocation;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.queue.ServerRequstQueue;
import org.jccdemo.dsf.server.ServerChannelCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

/** 
 * @ClassName: DsfNettyClientHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:23:16 
 *  
 */
public class DsfNettyServerHandler extends SimpleChannelInboundHandler<RequestMsg> {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RequestMsg msg)throws Exception {
		if(msg != null){
			log.info("Server:retrieve msgId="+msg.getMsgId());
			MethodInvocation invocation = msg.getInvocation();
			SocketChannel channel = (SocketChannel)ctx.channel();
			ServerChannelCache.setChannel(channel, invocation.getClazzName());
			ServerRequstQueue.putEle(msg);
		}
	}
}
