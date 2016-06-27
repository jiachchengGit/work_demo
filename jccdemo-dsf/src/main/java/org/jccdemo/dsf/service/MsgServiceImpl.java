package org.jccdemo.dsf.service;

import org.jccdemo.dsf.model.BaseMsg;
import org.jccdemo.dsf.model.MsgBody;
import org.jccdemo.dsf.model.MsgHeader;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.queue.ClientWaitResponseQueue;
import org.jccdemo.dsf.queue.ClientResponeCallBackQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgServiceImpl implements MsgService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public ResponseMsg sendMsg(BaseMsg msg) {
//		RequestMsg request = new RequestMsg();
//		MsgHeader header = new MsgHeader();
//		header.setMsgType(msg.getMsgType());
//		MsgBody body = new MsgBody();
//		body.setMsg(msg);
//		request.setBody(body);
//		//放入队列，等待发送
//		ClientRequstQueue.putEle(request);		
//		try {
//			return responseHandler.getEle();
//		} catch (Exception e) {
//			log.error("",e);
//		}
		return null;
	}

}
