/**   
* @Title: DsfNettyServer.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:05:01 
* @version V1.0   
*/
package org.jccdemo.dsf.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.netty.handler.DsfNettyServerChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: DsfNettyServer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:05:01 
 *  
 */
public class DsfNettyServer {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private int port;
	
	public DsfNettyServer(){
		this.port=DsfConst.PORT;
	}
	public DsfNettyServer(int port){
		this.port=port;
	}
	
	public void start(){
		log.info("----start netty server bootstrap----");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new DsfNettyServerChannelInitializer())
             .option(ChannelOption.SO_BACKLOG, 128)          
             .childOption(ChannelOption.SO_KEEPALIVE, true); 
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
			log.error("start netty server error",e);
		} finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
}
