/**   
* @Title: LoginServiceImpl.java 
* @Package com.jccdemo.client.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年5月26日 下午4:56:33 
* @version V1.0   
*/
package com.jccdemo.web.service.login;

import org.springframework.stereotype.Service;

/** 
 * @ClassName: LoginServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年5月26日 下午4:56:33 
 *  
 */
@Service
public class LoginServiceImpl implements LoginService {
	public String getGood() {
		return "Teset service node";
	}
}
