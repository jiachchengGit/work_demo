/**   
* @Title: TextProxy.java 
* @Package org.jccdemo.dsf.proxy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月7日 上午10:28:53 
* @version V1.0   
*/
package org.jccdemo.dsf.proxy;

import org.jccdemo.dsf.utils.JDKProxy;

/** 
 * @ClassName: TextProxy 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月7日 上午10:28:53 
 *  
 */
public class TextProxy {

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月7日 上午10:28:53
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) {
		ProxyInterface proxy = JDKProxy.getProxy(ProxyInterface.class,null);
		
		Person person = new Person("20","China Bejing");
		proxy.getName(" this is my name",person);
	}

}
