package org.jccdemo.dsf.javanio;

import org.jccdemo.dsf.javanio.model.BaseMsg;
import org.jccdemo.dsf.javanio.model.ResponseMsg;

public interface MsgService {
	public ResponseMsg sendMsg(BaseMsg msg);
}
