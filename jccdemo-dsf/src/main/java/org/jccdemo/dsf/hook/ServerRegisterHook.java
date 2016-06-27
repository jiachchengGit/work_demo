/**   
* @Title: ClientRegisterHook.java 
* @Package org.jccdemo.dsf.hook 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午2:55:52 
* @version V1.0   
*/
package org.jccdemo.dsf.hook;

import java.util.List;

import org.jccdemo.dsf.config.ServerConfig;

/** 
 * @ClassName: ClientRegisterHook 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午2:55:52 
 *  
 */
public interface ServerRegisterHook {
	public List<ServerConfig> getRegisterServers();
}
