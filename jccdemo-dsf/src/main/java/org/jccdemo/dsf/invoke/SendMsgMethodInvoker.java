/**   
* @Title: SendMsgMethodInvoker.java 
* @Package org.jccdemo.dsf 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午5:01:34 
* @version V1.0   
*/
package org.jccdemo.dsf.invoke;

import io.netty.channel.socket.SocketChannel;

import org.jccdemo.dsf.base.MethodInvoker;
import org.jccdemo.dsf.client.ClientChannelCache;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.queue.ClientWaitResponseQueue;

/** 
 * @ClassName: SendMsgMethodInvoker 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午5:01:34 
 *  
 */
public class SendMsgMethodInvoker implements MethodInvoker {

	public ResponseMsg invoke(RequestMsg request) throws Exception {
		String clazzName = request.getInvocation().getClazzName();
		SocketChannel channel = ClientChannelCache.getChannel(clazzName);
		if(channel != null){
			channel.writeAndFlush(request);
			ClientWaitResponseQueue.saveWaitRespone(request);
			return request.getResponeHanlder().getEle();
		}
		return null;
	}
}
