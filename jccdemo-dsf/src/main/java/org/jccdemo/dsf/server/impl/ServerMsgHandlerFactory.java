/**   
* @Title: ServerMsgHandlerFactory.java 
* @Package org.jccdemo.dsf.server.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月28日 上午10:34:49 
* @version V1.0   
*/
package org.jccdemo.dsf.server.impl;

import java.util.HashMap;
import java.util.Map;

import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.server.IServerMsgHandler;

/** 
 * @ClassName: ServerMsgHandlerFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月28日 上午10:34:49 
 *  
 */
public class ServerMsgHandlerFactory {
	public static Map<String,IServerMsgHandler> handlers = new HashMap<String,IServerMsgHandler>();
	static{
		HeatMsgHandler hm =new HeatMsgHandler();
		handlers.put(hm.getMsgType()+"", hm);
		MethodCallMsgHandler mm = new MethodCallMsgHandler();
		handlers.put(mm.getMsgType()+"", mm);
	}
	public static void registerHandler(IServerMsgHandler hanlder){
		handlers.put(hanlder.getMsgType()+"", hanlder);
	}
	public static ResponseMsg handler(RequestMsg req){
		int msgType = req.getMsgType();
		IServerMsgHandler handler = handlers.get(msgType+"");
		if(handler != null){
			return handler.handler(req);
		}		
		return null;
	}
}
