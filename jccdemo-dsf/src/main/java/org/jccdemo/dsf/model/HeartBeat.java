package org.jccdemo.dsf.model;

import java.io.Serializable;

public class HeartBeat extends BaseMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ip;
	private String content;
	
	@Override
	public int getMsgType() {
		return BaseMsg.MsgType.Heart.getMsgType();
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "HeartBeat [ip=" + ip + ", content=" + content + "]";
	}
}
