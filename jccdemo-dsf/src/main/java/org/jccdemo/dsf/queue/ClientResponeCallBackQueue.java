package org.jccdemo.dsf.queue;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.jccdemo.dsf.model.ResponseMsg;

public class ClientResponeCallBackQueue implements Serializable{
	private BlockingQueue<ResponseMsg> queue = new LinkedBlockingQueue<ResponseMsg>(1);
	public void put(ResponseMsg response){
		queue.offer(response);
	}
	public ResponseMsg getEle() throws Exception{
		//等待2s超时
		return queue.poll(5000, TimeUnit.MILLISECONDS);
	}
}
