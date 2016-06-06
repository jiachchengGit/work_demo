/**   
* @Title: DiscardServerHandler.java 
* @Package com.jd.jcc.server.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月2日 下午2:45:35 
* @version V1.0   
*/
package org.jccdemo.dsf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/** 
 * @ClassName: DiscardServerHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月2日 下午2:45:35 
 *  
 */
public class DiscardClientHandler2  extends ChannelInboundHandlerAdapter { // (1)
	private  ByteBuf firstMessage;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	    System.out.println("TTTT = "+getClass());
    	    ByteBuf buffer = Unpooled.buffer();
    	    buffer.writeBytes("This is Client Handler 2".getBytes());
			ctx.writeAndFlush(buffer);
    }

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("111 this class = "+getClass());
		ctx.close();
	}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
      byte[] data = "DiscardClientHandler2 give me an APPLE".getBytes();
      firstMessage=Unpooled.buffer();
      firstMessage.writeBytes(data);
      ctx.writeAndFlush(firstMessage);
    }

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
