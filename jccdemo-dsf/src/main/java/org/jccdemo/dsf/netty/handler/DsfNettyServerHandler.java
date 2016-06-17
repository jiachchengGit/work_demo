/**   
* @Title: DsfNettyClientHandler.java 
* @Package org.jccdemo.dsf.netty.handler 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:23:16 
* @version V1.0   
*/
package org.jccdemo.dsf.netty.handler;

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
public class DsfNettyServerHandler extends SimpleChannelInboundHandler<HeartBeat> {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HeartBeat msg)throws Exception {
		if(msg != null){
			log.info("server retrieve msg:"+msg.toString());
		}
		HeartBeat hb = new HeartBeat();
		hb.setContent("Server response to client msg...");
		ctx.writeAndFlush(hb);
	}
}
