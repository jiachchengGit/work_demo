/**   
* @Title: DiscardClient.java 
* @Package com.jd.jcc.server.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月2日 下午3:25:15 
* @version V1.0   
*/
package org.jccdemo.dsf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/** 
 * @ClassName: DiscardClient 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月2日 下午3:25:15 
 *  
 */
public class DiscardClient {

	/**
	 * @throws InterruptedException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月2日 下午3:25:15
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) throws InterruptedException {
		 String host = "127.0.0.1";
	        int port = 8080;
	        EventLoopGroup workerGroup = new NioEventLoopGroup();

	        try {
	            Bootstrap b = new Bootstrap(); // (1)
	            b.group(workerGroup); // (2)
	            b.channel(NioSocketChannel.class); // (3)
	            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
	            b.handler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                public void initChannel(SocketChannel ch) throws Exception {
	                    ch.pipeline().addLast("1",new DiscardClientHandler());
	                    ch.pipeline().addLast("2",new DiscardClientHandler2());
	                }
	            });

	            ChannelFuture f = b.connect(host, port).sync(); // (5)
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	        }
	}

}
