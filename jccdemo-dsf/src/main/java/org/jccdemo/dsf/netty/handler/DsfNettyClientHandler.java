/**   
* @Title: DsfNettyClientHandler.java 
* @Package org.jccdemo.dsf.netty.handler 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:23:16 
* @version V1.0   
*/
package org.jccdemo.dsf.netty.handler;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.jccdemo.dsf.model.HeartBeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/** 
 * @ClassName: DsfNettyClientHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:23:16 
 *  
 */
public class DsfNettyClientHandler extends SimpleChannelInboundHandler<HeartBeat> {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(createMsg());
			super.channelActive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HeartBeat msg)throws Exception {
		if(msg != null){
			log.info("client retrieve msg from server:"+msg.toString());
		}
//		Thread.sleep(2000);
//		ctx.writeAndFlush(createMsg());
	}
	
	private HeartBeat createMsg(){
		HeartBeat hb = new HeartBeat();
		hb.setContent("this is client msg to server");
		try {
			hb.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
		}
		return hb;
	}
}
