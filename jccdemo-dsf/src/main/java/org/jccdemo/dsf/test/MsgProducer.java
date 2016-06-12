/**   
* @Title: MsgProducer.java 
* @Package org.jccdemo.dsf.javanio 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月8日 上午9:29:43 
* @version V1.0   
*/
package org.jccdemo.dsf.test;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import org.jccdemo.dsf.model.HeartBeat;
import org.jccdemo.dsf.model.ResponseMsg;
import org.jccdemo.dsf.service.MsgService;
import org.jccdemo.dsf.service.MsgServiceImpl;

/** 
 * @ClassName: MsgProducer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月8日 上午9:29:43 
 *  
 */
public class MsgProducer {

	public static void produce(){
		Timer timer = new Timer("Timer--MsgProducer");
		timer.schedule(new TimerTask() {
			MsgService service = new MsgServiceImpl();
			@Override
			public void run() {
				HeartBeat hb = createMsg();	
				ResponseMsg respone = service.sendMsg(hb);
				if(respone != null){
					System.out.println("MsgService call back="+respone.getBody().toString());
				}
			}
			/** 
			 * @Description: TODO(这里用一句话描述这个方法的作用) 
			 * @Author chenjiacheng
			 * @Date 2016年6月12日 下午5:17:46
			 * @return        
			 * @throws 
			 */
			private HeartBeat createMsg() {
				HeartBeat hb = new HeartBeat();
				hb.setContent("client heart beat to server !");
				try {
					hb.setIp(InetAddress.getLocalHost().getHostAddress());
				} catch (Exception e) {
				}
				return hb;
			}
		}, 1000, 5000);
	}
}
