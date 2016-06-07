/**   
* @Title: JDKInvocationHandler.java 
* @Package org.jccdemo.dsf.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月7日 上午10:22:33 
* @version V1.0   
*/
package org.jccdemo.dsf.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.jccdemo.dsf.base.MethodInvoker;

/** 
 * @ClassName: JDKInvocationHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月7日 上午10:22:33 
 *  
 */
public class JDKInvocationHandler implements InvocationHandler {
	
	private MethodInvoker invoker;
	
	public JDKInvocationHandler(MethodInvoker invoker) {
		this.invoker = invoker;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Class = "+method.getDeclaringClass().getName());
		System.out.println("method="+method.getName());
		Class<?>[] parameterTypes = method.getParameterTypes();
		if(parameterTypes != null){
			for(Class<?> c:parameterTypes){
				if(c != null){
					System.out.println("method paramtype="+c.getName());
				}
			}
		}
		if(args != null){
			for(Object o:args){
				System.out.println("param value="+o.toString());
			}
		}
		return null;
	}

}
