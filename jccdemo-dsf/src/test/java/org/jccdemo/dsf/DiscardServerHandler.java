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
public class DiscardServerHandler  extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	   ByteBuf in = (ByteBuf) msg;
    	    try {
    	        while (in.isReadable()) { // (1)
    	            System.out.print((char) in.readByte());
    	            System.out.flush();
    	        }
    	        System.out.println();
    	    } finally {
    	        ReferenceCountUtil.release(msg); // (2)
    	    }
    }
    
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ChannelFuture writeAndFlush = ctx.writeAndFlush(getSendMsg());
	}

	private ByteBuf getSendMsg(){
    	ByteBuf bb = Unpooled.buffer();
    	bb.writeBytes("this is server send msg".getBytes());
    	return bb;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        cause.printStackTrace();
        ctx.close();
    }
}
