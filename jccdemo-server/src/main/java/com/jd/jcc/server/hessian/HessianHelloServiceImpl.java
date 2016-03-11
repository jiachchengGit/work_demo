/**   
* @Title: HessianHelloImpl.java 
* @Package com.remote.serviceImpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年10月19日 上午10:07:08 
* @version V1.0   
*/
package com.jd.jcc.server.hessian;

import org.jccdemo.api.hessian.IHessianHelloService;

import com.caucho.hessian.server.HessianServlet;

/** 
 * @ClassName: HessianHelloImpl 
 * @Description: 需要继承HessianServlet
 * @author chenjiacheng
 * @date 2015年10月19日 上午10:07:08 
 *  
 */
public class HessianHelloServiceImpl extends HessianServlet implements IHessianHelloService {
	/**
	 * @Field serialVersionUID: TODOs
	 * @User chenjiacheng
	 * @date 2015年10月19日 上午10:07:41
	 */
	private static final long serialVersionUID = 1L;

	public String sayHello() {
		return "sayHello:Hello ,welcome to Hessian world !";
	}
}
