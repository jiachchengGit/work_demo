/**   
* @Title: ServiceImpl.java 
* @Package com.remote.rmi 
* @Description: TODO(用一句话描述该文件做�?��) 
* @author chenjiacheng   
* @date 2015�?0�?9�?下午2:41:51 
* @version V1.0   
*/
package com.jd.jcc.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.jccdemo.api.rmi.IRmiService;

/**
 * 
 * @author cdchenjiacheng
 * 必须继承UnicastRemoteObject 用于暴露远程对象方法调用
 */
public class RmiServiceImpl extends UnicastRemoteObject implements IRmiService {

	private static final long serialVersionUID = -2210045076092718463L;

	/**
	 * 需要存在构造函数
	 * @throws RemoteException
	 */
	protected RmiServiceImpl() throws RemoteException {
		super();
	}

	public String sayHello() throws RemoteException {
		return "Hello, RMI test !";
	}
}
