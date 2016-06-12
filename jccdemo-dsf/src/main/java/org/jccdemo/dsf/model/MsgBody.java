package org.jccdemo.dsf.model;

import java.io.Serializable;

public class MsgBody implements Serializable{
	private static final long serialVersionUID = -418429841674015369L;
	private Object msg;
	private String classpath;
	
	public String getClasspath() {
		return classpath;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
		if(msg != null){
			this.classpath=msg.getClass().getName();
		}
	}

	@Override
	public String toString() {
		return "MsgBody [msg=" + msg.toString() + "]";
	}
}
