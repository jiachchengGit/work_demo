package org.jccdemo.dsf.model;

import java.io.Serializable;

public class ResponseMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	private MsgHeader hearder;

	private MsgBody body;

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
