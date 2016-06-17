/**   
* @Title: DsfNettyServer.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:05:01 
* @version V1.0   
*/
package org.jccdemo.dsf.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.jccdemo.dsf.DiscardClientHandler;
import org.jccdemo.dsf.DiscardClientHandler2;
import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.netty.handler.DsfNettyClientChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: DsfNettyServer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:05:01 
 *  
 */
public class DsfNettyClient {
	private Logger log = LoggerFactory.getLogger(getClass());
	private String ip=null;
	private int port=0;
	public DsfNettyClient(){
	      this.ip=DsfConst.IP;
		  this.port=DsfConst.PORT;
	}
	public DsfNettyClient(String ip,int port){
		this.ip=ip;
		this.port=port;
	}
	
	public void start(){
		log.info("----start netty client bootstrap ----");
		EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); 
            b.group(workerGroup);
            b.channel(NioSocketChannel.class); 
            b.option(ChannelOption.SO_KEEPALIVE, true); 
            b.handler(new DsfNettyClientChannelInitializer());

            ChannelFuture f = b.connect(ip, port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
			log.error("netty client start error",e);
		} finally {
            workerGroup.shutdownGracefully();
        }
	}
}
