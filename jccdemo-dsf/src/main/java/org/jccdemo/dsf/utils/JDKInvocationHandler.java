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
import org.jccdemo.dsf.model.MethodInvocation;
import org.jccdemo.dsf.model.RequestMsg;

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
		RequestMsg request = new RequestMsg();
		MethodInvocation invocation = new MethodInvocation();
		invocation.setClazzName(method.getDeclaringClass().getName());
		invocation.setMethodName(method.getName());
		invocation.setParamTypes(method.getParameterTypes());
		invocation.setParamValues(args);
		request.setInvocation(invocation);
		return invoker.invoke(request);
	}

}
