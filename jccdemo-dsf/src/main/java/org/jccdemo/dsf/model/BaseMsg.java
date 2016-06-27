package org.jccdemo.dsf.model;

public abstract class BaseMsg {
	
	public static final int REQUEST_TYPE=1;
	public static final int RESPONE_TYPE=2;
	
	private String msgId;
	private int msgType;
	
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the msgType
	 */
	public int getMsgType() {
		return msgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
}
