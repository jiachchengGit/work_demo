/**   
* @Title: IServerMsgHandler.java 
* @Package org.jccdemo.dsf.server 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月28日 上午10:27:03 
* @version V1.0   
*/
package org.jccdemo.dsf.server;

import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;

/** 
 * @ClassName: IServerMsgHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月28日 上午10:27:03 
 *  
 */
public interface IServerMsgHandler {
	public ResponseMsg handler(RequestMsg req);
	public int getMsgType();
}
