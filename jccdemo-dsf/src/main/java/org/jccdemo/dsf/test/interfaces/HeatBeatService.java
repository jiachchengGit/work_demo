/**   
* @Title: UserLoginService.java 
* @Package org.jccdemo.dsf.test.interfaces 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午3:31:39 
* @version V1.0   
*/
package org.jccdemo.dsf.test.interfaces;

import org.jccdemo.dsf.test.bean.HeatReq;
import org.jccdemo.dsf.test.bean.HeatResp;

/** 
 * @ClassName: UserLoginService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午3:31:39 
 *  
 */
public interface HeatBeatService {
	public HeatResp sayHello(HeatReq req);
}
