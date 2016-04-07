/**   
* @Title: ServiceMain.java 
* @Package com.remote.rmi 
* @Description: TODO(用一句话描述该文件做�?��) 
* @author chenjiacheng   
* @date 2015�?0�?9�?下午2:42:27 
* @version V1.0   
*/
package com.jd.jcc.server.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.jccdemo.constant.JccConst;

/** 
 * @ClassName: ServiceMain 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015�?0�?9�?下午2:42:27 
 *  
 */
public class RmiServiceMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(JccConst.port);
		Naming.rebind(JccConst.url, new RmiServiceImpl());
		System.out.println("--Start RMI server !!! ");
	}
}
