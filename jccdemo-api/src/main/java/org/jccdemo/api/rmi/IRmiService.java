/**   
* @Title: IService.java 
* @Package com.remote.rmi 
* @Description: TODO(用一句话描述该文件做�?��) 
* @author chenjiacheng   
* @date 2015�?0�?9�?下午2:40:12 
* @version V1.0   
*/
package org.jccdemo.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author cdchenjiacheng
 *  需要继承远程接口
 */
public interface IRmiService extends Remote {
	/**
	 * @return
	 * @throws RemoteException 方法必须抛出的异常类型
	 */
	public String sayHello() throws RemoteException;
}
