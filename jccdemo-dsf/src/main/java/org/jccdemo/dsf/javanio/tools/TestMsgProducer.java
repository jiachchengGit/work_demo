/**   
* @Title: MsgProducer.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:29:43 
* @version V1.0   
*/
package org.jccdemo.dsf.javanio.tools;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import org.jccdemo.dsf.javanio.MsgService;
import org.jccdemo.dsf.javanio.MsgServiceImpl;
import org.jccdemo.dsf.javanio.model.HeartBeat;
import org.jccdemo.dsf.javanio.model.MsgBody;
import org.jccdemo.dsf.javanio.model.MsgHeader;
import org.jccdemo.dsf.javanio.model.RequestMsg;
import org.jccdemo.dsf.javanio.model.ResponseMsg;

/** 
 * @ClassName: MsgProducer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:29:43 
 *  
 */
public class TestMsgProducer {

	public static void produce(){
		Timer timer = new Timer("Timer--MsgProducer");
		timer.schedule(new TimerTask() {
			MsgService service = new MsgServiceImpl();
			@Override
			public void run() {
				HeartBeat hb = new HeartBeat();
				hb.setContent("client heart beat to server !");
				try {
					hb.setIp(InetAddress.getLocalHost().getHostAddress());
				} catch (Exception e) {
				}	
				ResponseMsg respone = service.sendMsg(hb);
				if(respone != null){
					System.out.println("MsgService call back="+respone.getBody().toString());
				}
			}
		}, 1000, 5000);
	}
	
//	private static RequestMsg createMsg(){
//		HeartBeat hb = new HeartBeat();
//		hb.setContent("client heart beat to server !");
//		try {
//			hb.setIp(InetAddress.getLocalHost().getHostAddress());
//		} catch (Exception e) {
//		}		
//		RequestMsg request = new RequestMsg();
//		MsgHeader header = new MsgHeader();
//		header.setMsgType(hb.getMsgType());
//		request.setHearder(header);
//		MsgBody body = new MsgBody();
//		body.setMsg(hb);
//		request.setBody(body);
//		System.out.println("---create msg----");
//		return request;
//	}

}
