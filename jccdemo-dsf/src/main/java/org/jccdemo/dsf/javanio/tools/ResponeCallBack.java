package org.jccdemo.dsf.javanio.tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.jccdemo.dsf.javanio.model.ResponseMsg;

public class ResponeCallBack {
	private BlockingQueue<ResponseMsg> queue = new LinkedBlockingQueue<ResponseMsg>(1);
	public void put(ResponseMsg response){
		queue.offer(response);
	}
	public ResponseMsg getEle() throws Exception{
		//等待2s超时
		return queue.poll(2000, TimeUnit.MILLISECONDS);
	}
}
