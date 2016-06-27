package org.jccdemo.dsf.model;

import java.io.Serializable;

import org.jccdemo.dsf.queue.ClientResponeCallBackQueue;

public class RequestMsg extends BaseMsg implements Serializable{

	private MethodInvocation invocation;
	
	private static final long serialVersionUID = 1L;
	
	private ClientResponeCallBackQueue responeHanlder = new ClientResponeCallBackQueue();
	
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
