package org.jccdemo.dsf.model;

import java.io.Serializable;

public class ResponseMsg extends BaseMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MsgBody body;

	public MsgBody getBody() {
		return body;
	}

	public void setBody(MsgBody body) {
		this.body = body;
	}
}
