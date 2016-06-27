/**   
* @Title: ServerConfigFactory.java 
* @Package org.jccdemo.dsf.register 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午5:25:57 
* @version V1.0   
*/
package org.jccdemo.dsf.register;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jccdemo.dsf.config.ServerConfig;

/** 
 * @ClassName: ServerConfigFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午5:25:57 
 *  
 */
public class ClientConfigFactory {
	
	private static Map<String,ServerConfig> servers = new HashMap<String,ServerConfig>();
	
	public static void register(ServerConfig sc){
		servers.put(sc.getClazzName(), sc);
	}
	
	public static void register(List<ServerConfig> servers){
		if(servers != null){
			for(ServerConfig sc:servers){
				register(sc);
			}
		}
	}
	
	public static ServerConfig getServerConfigs(String clazzName,String versionAlias){
		ServerConfig ss = servers.get(clazzName);
		return ss;
	}
	
	public static Collection<ServerConfig> getServerConfigs(){
		Collection<ServerConfig> values = servers.values();
		return values;
	}
}
