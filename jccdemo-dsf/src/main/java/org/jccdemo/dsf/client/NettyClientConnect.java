/**   
* @Title: NettyClientStart.java 
* @Package org.jccdemo.dsf.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午2:52:54 
* @version V1.0   
*/
package org.jccdemo.dsf.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Collection;

import org.jccdemo.dsf.client.handler.DsfNettyClientChannelInitializer;
import org.jccdemo.dsf.config.ServerConfig;
import org.jccdemo.dsf.hook.ServerRegisterHook;
import org.jccdemo.dsf.register.ClientConfigFactory;
import org.jccdemo.dsf.utils.ThreadPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: NettyClientStart 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午2:52:54 
 *  
 */
public class NettyClientConnect {
	
	private static Logger log = LoggerFactory.getLogger(NettyClientConnect.class);
	
	public static void connectServers(ServerRegisterHook hook){
		//注册服务器
		ClientConfigFactory.register(hook.getRegisterServers());
		Collection<ServerConfig> serverConfigs = ClientConfigFactory.getServerConfigs();
		if(serverConfigs != null){
			for(ServerConfig sc:serverConfigs){
				connectServer(sc);
			}
		}
	}
	
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月18日 下午3:27:19
	 * @param sc        
	 * @throws 
	 */
	public static void connectServer(ServerConfig sc) {
		ConnectTask task = new ConnectTask(sc);
		ThreadPoolUtils.execute(task);
	}
	
	static class ConnectTask implements Runnable{
		private ServerConfig sc;		
		public ConnectTask(ServerConfig sc){
			this.sc=sc;
		}
		
		public void run() {
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			try {
				log.info("[client]-init connect to server,"+sc.toString());
			    Bootstrap b = new Bootstrap(); 
			    b.group(workerGroup);
			    b.channel(NioSocketChannel.class); 
			    b.option(ChannelOption.SO_KEEPALIVE, true); 
			    b.handler(new DsfNettyClientChannelInitializer(sc));
			    ChannelFuture f = b.connect(sc.getIp(), sc.getPort()).sync();
			    f.channel().closeFuture().sync();		            
			} catch (InterruptedException e) {
				log.error("[client]-init connect to server error",e);
			} finally {
			    workerGroup.shutdownGracefully();
			}
		}
		
	}
}
