package org.jccdemo.dsf.service;

import org.jccdemo.dsf.model.BaseMsg;
import org.jccdemo.dsf.model.ResponseMsg;

public interface MsgService {
	public ResponseMsg sendMsg(BaseMsg msg);
}
