package org.jccdemo.dsf.model;

import java.io.Serializable;
import java.util.UUID;

public class MsgHeader implements Serializable{
	private static final long serialVersionUID = 1L;
	private int length;
	private int msgType;
	private String msgId;
	public MsgHeader() {
		this.msgId=UUID.randomUUID().toString();
	}
	public MsgHeader(int length,int msgType){
		this.length=length;
		this.msgType=msgType;
		this.msgId=UUID.randomUUID().toString();
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	@Override
	public String toString() {
		return "MsgHeader [length=" + length + ", msgType=" + msgType
				+ ", msgId=" + msgId + "]";
	}
}
