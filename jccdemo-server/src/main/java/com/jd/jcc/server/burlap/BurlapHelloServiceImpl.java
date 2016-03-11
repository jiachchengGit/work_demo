/**   
* @Title: BurlapHelloImpl.java 
* @Package com.remote.serviceImpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年10月28日 上午11:38:13 
* @version V1.0   
*/
package com.jd.jcc.server.burlap;

import org.jccdemo.api.burlap.IBurlapHelloService;

import com.caucho.burlap.server.BurlapServlet;

/** 
 * @ClassName: BurlapHelloImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015年10月28日 上午11:38:13 
 *  
 */
public class BurlapHelloServiceImpl extends BurlapServlet implements IBurlapHelloService {

	/**
	 * @Field serialVersionUID: TODO
	 * @User chenjiacheng
	 * @date 2015年10月28日 上午11:38:18
	 */
	private static final long serialVersionUID = -2901808345944900323L;

	public String sayHello() {
		return "Hello burlap XML test";
	}

}
