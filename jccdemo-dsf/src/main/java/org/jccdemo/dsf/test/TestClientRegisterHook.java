/**   
* @Title: SimpleClientRegisterHook.java 
* @Package org.jccdemo.dsf.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午3:32:24 
* @version V1.0   
*/
package org.jccdemo.dsf.test;

import java.util.ArrayList;
import java.util.List;

import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.config.ServerConfig;
import org.jccdemo.dsf.hook.ServerRegisterHook;
import org.jccdemo.dsf.test.interfaces.HeatBeatService;

/** 
 * @ClassName: SimpleClientRegisterHook 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午3:32:24 
 *  
 */
public class TestClientRegisterHook implements ServerRegisterHook {

	public List<ServerConfig> getRegisterServers() {
		List<ServerConfig> list = new ArrayList<ServerConfig>();
		ServerConfig sc = new ServerConfig();
		sc.setClazzName(HeatBeatService.class.getCanonicalName());
		sc.setIp("127.0.0.1");
		sc.setPort(DsfConst.PORT);
		sc.setVersionAlias(DsfConst.TEST_VERSION_ALIAS);
		list.add(sc);
		return list;
	}
}
