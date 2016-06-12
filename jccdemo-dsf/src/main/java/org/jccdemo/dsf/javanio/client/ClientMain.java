/**   
 * @Title: ClientMain.java 
 * @Package org.jccdemo.dsf.javanio 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年6月7日 下午4:01:48 
 * @version V1.0   
 */
package org.jccdemo.dsf.javanio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.jccdemo.dsf.common.DsfConst;
import org.jccdemo.dsf.javanio.model.MsgBody;
import org.jccdemo.dsf.javanio.model.MsgHeader;
import org.jccdemo.dsf.javanio.model.RequestMsg;
import org.jccdemo.dsf.javanio.model.ResponseMsg;
import org.jccdemo.dsf.javanio.tools.JDKObjCodecUtil;
import org.jccdemo.dsf.javanio.tools.ClientRequstQueue;
import org.jccdemo.dsf.javanio.tools.TestMsgProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ClientMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年6月7日 下午4:01:48
 * 
 */
public class ClientMain {
	private static Logger log = LoggerFactory.getLogger(ClientMain.class);
	
	public static void main(String[] args){
		try {
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @Author chenjiacheng
	 * @Date 2016年6月7日 下午4:01:48
	 * @param args
	 * @throws
	 */
	public static void start() throws IOException {
		
		TestMsgProducer.produce();
		
		Selector selector = Selector.open();
		SocketChannel readChannel = SocketChannel.open();
		readChannel.configureBlocking(false);
		readChannel.connect(new InetSocketAddress(DsfConst.IP, DsfConst.PORT));
		readChannel.register(selector, SelectionKey.OP_CONNECT);
		log.info("--start client -- ");
		while (true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				keyIterator.remove();
				if (key.isConnectable()) {
					SocketChannel sc1 = (SocketChannel)key.channel();
					log.info("-- client connected to server ---"+sc1.hashCode());
                    sc1.finishConnect(); 
                    sc1.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    continue; 
                }
				if(key.isReadable()){
					doRead(key);  
				}
				if(key.isValid() && key.isWritable()){
					SocketChannel writeChannel = (SocketChannel)key.channel();
					try {
						RequestMsg request = ClientRequstQueue.getEle();
						ByteBuffer requestMsg = JDKObjCodecUtil.getRequestMsg(request);
						if(requestMsg != null){
							ClientRequstQueue.saveWaitRespone(request);
							writeChannel.write(requestMsg);
						}
					} catch (Exception e) {
						log.error("",e);
					}
					writeChannel.register(key.selector(),SelectionKey.OP_READ | SelectionKey.OP_WRITE );  
				}
			}
		}
	}
	private static void doRead(SelectionKey key) throws IOException,
			ClosedChannelException {
		SocketChannel readChannel2 = (SocketChannel)key.channel();
		ByteBuffer bufHeader = ByteBuffer.allocate(DsfConst.MSG_HEADER_LENGTH);
		long bytesRead=readChannel2.read(bufHeader);
		if(bytesRead==-1) {  
		    readChannel2.close();  
		} else {  
			try {
				MsgHeader header = JDKObjCodecUtil.deserizable(bufHeader.array(), MsgHeader.class);
				int length = header.getLength();
				ByteBuffer bufBody = ByteBuffer.allocate(length);
				int bodyRead = readChannel2.read(bufBody);
				if(bodyRead != -1){
					MsgBody body = JDKObjCodecUtil.deserizable(bufBody.array(), MsgBody.class);
					ResponseMsg respone = new ResponseMsg();
					respone.setBody(body);
					respone.setHearder(header);
					handleResponeMsg(respone);
				}
			} catch (Exception e) {
				log.error("",e);
			}
		}  
		readChannel2.register(key.selector(),SelectionKey.OP_READ | SelectionKey.OP_WRITE );
	}

	private static void handleResponeMsg(ResponseMsg respone) {
		RequestMsg request = ClientRequstQueue.getWaitRespone(respone.getHearder().getMsgId());
		request.getResponeHanlder().put(respone);
//		System.out.println("client recieve msg body="+respone.getBody().getMsg().toString());
	}
}
