/**   
* @Title: DsfNettyServer.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午2:05:01 
* @version V1.0   
*/
package org.jccdemo.dsf.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.config.ServerConfig;
import org.jccdemo.dsf.hook.ServerRegisterHook;
import org.jccdemo.dsf.server.handler.DsfNettyServerChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: DsfNettyServer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午2:05:01 
 *  
 */
public class NettyServerFactory {
	private Logger log = LoggerFactory.getLogger(getClass());
	private static Map<String,ServerConfig> servers = new HashMap<String,ServerConfig>();
	public static void registerServer(ServerRegisterHook hook){
		List<ServerConfig> servers = hook.getRegisterServers();
		if(servers != null){
			for(ServerConfig sc:servers){
				registerServer(sc);
				
			}
		}
	}
	
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月20日 下午2:16:30
	 * @param sc        
	 * @throws 
	 */
	public static void registerServer(ServerConfig sc) {
		servers.put(sc.getClazzName(), sc);
	}


	
	public void startServer(){
		log.info("---start ServerTaskDistributeThread ---");
		new ServerTaskDistributeThread().start();
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
            ChannelFuture f = bootstrap.bind(DsfConst.SERVER_PORT).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
			log.error("start netty server error",e);
		} finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
}
