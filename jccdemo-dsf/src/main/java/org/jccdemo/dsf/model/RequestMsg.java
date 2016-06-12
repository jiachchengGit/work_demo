package org.jccdemo.dsf.model;

import java.io.Serializable;

import org.jccdemo.dsf.queue.ResponeCallBackQueue;

public class RequestMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private MsgHeader hearder;
	
	private MsgBody body;
	
	private ResponeCallBackQueue responeHanlder;
	
	public ResponeCallBackQueue getResponeHanlder() {
		return responeHanlder;
	}
	public void setResponeHanlder(ResponeCallBackQueue responeHanlder) {
		this.responeHanlder = responeHanlder;
	}
	public MsgHeader getHearder() {
		return hearder;
	}
	public void setHearder(MsgHeader hearder) {
		this.hearder = hearder;
	}
	public MsgBody getBody() {
		return body;
	}
	public void setBody(MsgBody body) {
		this.body = body;
	}
}
