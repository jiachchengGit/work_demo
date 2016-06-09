package org.jccdemo.dsf.javanio;

import org.jccdemo.dsf.javanio.model.BaseMsg;
import org.jccdemo.dsf.javanio.model.MsgBody;
import org.jccdemo.dsf.javanio.model.MsgHeader;
import org.jccdemo.dsf.javanio.model.RequestMsg;
import org.jccdemo.dsf.javanio.model.ResponseMsg;
import org.jccdemo.dsf.javanio.tools.ClientRequstQueue;
import org.jccdemo.dsf.javanio.tools.ResponeCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgServiceImpl implements MsgService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public ResponseMsg sendMsg(BaseMsg msg) {
		ResponeCallBack responseHandler = new ResponeCallBack();
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
