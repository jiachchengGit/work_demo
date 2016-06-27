/**   
* @Title: DsfNettyClientHandler.java 
* @Package org.jccdemo.dsf.netty.handler 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:23:16 
* @version V1.0   
*/
package org.jccdemo.dsf.client.handler;

import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.queue.ClientWaitResponseQueue;
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
public class DsfNettyClientHandler extends SimpleChannelInboundHandler<ResponseMsg> {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)	throws Exception {
		// TODO Auto-generated method stub
		log.info("--client read msg from server -- ");
		super.channelRead(ctx, msg);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ResponseMsg msg)throws Exception {
		if(msg != null){
			log.info("client retrieve msg from server:"+msg.getBody().getMsg().toString());
			//put response msg to queue
			RequestMsg waitRespone = ClientWaitResponseQueue.getWaitRespone(msg.getMsgId());
			waitRespone.getResponeHanlder().put(msg);
		}
	}
}
