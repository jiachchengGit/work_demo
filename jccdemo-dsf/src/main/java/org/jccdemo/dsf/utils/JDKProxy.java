/**   
 * @Title: JDKProxy.java 
 * @Package org.jccdemo.dsf.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年6月7日 上午10:06:03 
 * @version V1.0   
 */
package org.jccdemo.dsf.utils;

import java.lang.reflect.Proxy;

import org.jccdemo.dsf.base.MethodInvoker;

/**
 * @ClassName: JDKProxy
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年6月7日 上午10:06:03
 * 
 */
public class JDKProxy {

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> interfaceClass,MethodInvoker methodInvoker){
		JDKInvocationHandler handler = new JDKInvocationHandler(methodInvoker);
		Object newProxyInstance = Proxy.newProxyInstance(getCurrentClassLoader(), new Class[]{interfaceClass}, handler);
		return (T) newProxyInstance;
	}

	public static ClassLoader getCurrentClassLoader() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if (cl == null) {
			cl = JDKProxy.class.getClassLoader();
		}
		return cl == null ? ClassLoader.getSystemClassLoader() : cl;
	}
}
