package org.jccdemo.dsf.model;

import java.io.Serializable;
import java.util.UUID;

import org.jccdemo.dsf.common.DsfConst;

public abstract class BaseMsg  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msgId;
	private int msgType;
	public BaseMsg(){
		msgType=DsfConst.MsgType.HeartBeat;
		msgId = UUID.randomUUID().toString();
	}
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
