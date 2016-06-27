/**   
* @Title: ServerConfigFactory.java 
* @Package org.jccdemo.dsf.register 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午5:25:57 
* @version V1.0   
*/
package org.jccdemo.dsf.register;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.config.ServerConfig;

/** 
 * @ClassName: ServerConfigFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午5:25:57 
 *  
 */
public class ServerConfigFactory {
	
	private static Map<String,ServerConfig> servers = new HashMap<String,ServerConfig>();
	private static Map<String,Object> instances = new HashMap<String,Object>();
	public void register(Object serverInstance,String versionAlias){
		String clazzName = serverInstance.getClass().getCanonicalName();
		instances.put(clazzName, serverInstance);
		String key = getKey(clazzName, versionAlias);
		ServerConfig sc = new ServerConfig();
		sc.setClazzName(clazzName);
		try {
			sc.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		sc.setPort(DsfConst.SERVER_PORT);
		sc.setStatus(DsfConst.ServerStatus.RUN);
		sc.setVersionAlias(versionAlias);
		servers.put(key, sc);
	}
	
	public static Collection<ServerConfig> getServerConfigs(){
		Collection<ServerConfig> values = servers.values();
		return values;
	}
	private static String getKey(String clazzName,String versionAlias){
		return clazzName+"#"+versionAlias;
	}
	
}
