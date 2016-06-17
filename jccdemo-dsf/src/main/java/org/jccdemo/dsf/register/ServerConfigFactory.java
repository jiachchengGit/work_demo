/**   
* @Title: ServerConfigFactory.java 
* @Package org.jccdemo.dsf.register 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午5:25:57 
* @version V1.0   
*/
package org.jccdemo.dsf.register;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jccdemo.dsf.config.ServerConfig;

/** 
 * @ClassName: ServerConfigFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午5:25:57 
 *  
 */
public class ServerConfigFactory {
	
	private static Map<String,Set<ServerConfig>> servers = new HashMap<String,Set<ServerConfig>>();
	
	public void register(ServerConfig sc){
		String clazzName = sc.getClazzName();
		Set<ServerConfig> set = servers.get(clazzName);
		if(set == null){
			set = new HashSet<ServerConfig>();
			servers.put(clazzName, set);
		}
		set.add(sc);
	}
	
	public static Set<ServerConfig> getServerConfigs(String clazzName){
		Set<ServerConfig> ss = servers.get(clazzName);
		return ss;
	}
}
