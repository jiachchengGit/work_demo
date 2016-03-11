/**   
* @Title: JwsClient.java 
* @Package com.jd.cxf.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年11月7日 下午4:15:17 
* @version V1.0   
*/
package com.jccdemo.client.jws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.jccdemo.api.jws.IJwsHelloService;

/** 
 * @ClassName: JwsClient 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015年11月7日 下午4:15:17 
 *  
 */
@SuppressWarnings("restriction")
public class TestJwsClient {
	
	private static final QName SERVICE_NAME = new QName("http://server.jws.jd.com/","JwsHelloService");

	/**
	 * @throws MalformedURLException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2015年11月7日 下午4:15:17
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) throws MalformedURLException {
		Service service = Service.create(new URL(IJwsHelloService.address+"?wsdl"),SERVICE_NAME);  
		IJwsHelloService hw = service.getPort(IJwsHelloService.class);  
        System.out.println(hw.sayHello("JCC"));  
	}
}
