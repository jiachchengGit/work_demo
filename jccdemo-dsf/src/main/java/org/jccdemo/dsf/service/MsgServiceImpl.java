package org.jccdemo.dsf.service;

import org.jccdemo.dsf.model.BaseMsg;
import org.jccdemo.dsf.model.MsgBody;
import org.jccdemo.dsf.model.MsgHeader;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.queue.ClientRequstQueue;
import org.jccdemo.dsf.queue.ResponeCallBackQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgServiceImpl implements MsgService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public ResponseMsg sendMsg(BaseMsg msg) {
		ResponeCallBackQueue responseHandler = new ResponeCallBackQueue();
		RequestMsg request = new RequestMsg();
		MsgHeader header = new MsgHeader();
		header.setMsgType(msg.getMsgType());
		request.setHearder(header);
		MsgBody body = new MsgBody();
		body.setMsg(msg);
		request.setBody(body);
		request.setResponeHanlder(responseHandler);
		//放入队列，等待发送
		ClientRequstQueue.putEle(request);		
		try {
			return responseHandler.getEle();
		} catch (Exception e) {
			log.error("",e);
		}
		return null;
	}

}
