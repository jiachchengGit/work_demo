/**   
* @Title: HeatMsgHandler.java 
* @Package org.jccdemo.dsf.server.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月28日 上午10:28:45 
* @version V1.0   
*/
package org.jccdemo.dsf.server.impl;

import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.model.MsgBody;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.server.IServerMsgHandler;
import org.jccdemo.dsf.test.bean.HeatResp;

/** 
 * @ClassName: HeatMsgHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月28日 上午10:28:45 
 *  
 */
public class HeatMsgHandler implements IServerMsgHandler {

	public ResponseMsg handler(RequestMsg req) {
		ResponseMsg resp = new ResponseMsg();
		MsgBody body = new MsgBody();
		HeatResp hr = new HeatResp();
		hr.setAnswer("--This is server response to client msg---");
		body.setMsg(hr);
		resp.setBody(body);
		return resp;
	}

	public int getMsgType() {
		return DsfConst.MsgType.HeartBeat;
	}
}
