package org.jccdemo.dsf.model;

import java.io.Serializable;

import org.jccdemo.dsf.queue.ClientResponeCallBackQueue;

public class RequestMsg extends BaseMsg implements Serializable{
	private static final long serialVersionUID = 1L;

	private MethodInvocation invocation;
	
	private ClientResponeCallBackQueue responeHanlder = new ClientResponeCallBackQueue();
	
	private MsgBody body;

	public MsgBody getBody() {
		return body;
	}

	public void setBody(MsgBody body) {
		this.body = body;
	}
	
	/**
	 * @return the invocation
	 */
	public MethodInvocation getInvocation() {
		return invocation;
	}
	/**
	 * @param invocation the invocation to set
	 */
	public void setInvocation(MethodInvocation invocation) {
		this.invocation = invocation;
	}

	
	public ClientResponeCallBackQueue getResponeHanlder() {
		return responeHanlder;
	}
}
