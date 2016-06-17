/**   
* @Title: CreateBeanUtils.java 
* @Package org.jccdemo.dsf.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月17日 下午4:19:17 
* @version V1.0   
*/
package org.jccdemo.dsf.utils;

import java.net.InetAddress;

import org.jccdemo.dsf.model.HeartBeat;

/** 
 * @ClassName: CreateBeanUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月17日 下午4:19:17 
 *  
 */
public class CreateBeanUtils {
	public static HeartBeat createClientMsg(){
		HeartBeat hb = new HeartBeat();
		hb.setContent("this is client msg to server");
		try {
			hb.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
		}
		return hb;
	}
}
