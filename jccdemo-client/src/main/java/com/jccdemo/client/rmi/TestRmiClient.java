/**   
* @Title: ServiceClient.java 
* @Package com.remote.rmi 
* @Description: TODO(用一句话描述该文件做�?��) 
* @author chenjiacheng   
* @date 2015�?0�?9�?下午2:46:17 
* @version V1.0   
*/
package com.jccdemo.client.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.jccdemo.api.rmi.IRmiService;

import com.jccdemo.utils.JccConst;

/** 
 * @ClassName: ServiceClient 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015�?0�?9�?下午2:46:17 
 *  
 */
public class TestRmiClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		Remote lookup = Naming.lookup(JccConst.url);
		IRmiService service = (IRmiService) lookup;
		String sayHello = service.sayHello();
		System.out.println(sayHello);
	}
}
