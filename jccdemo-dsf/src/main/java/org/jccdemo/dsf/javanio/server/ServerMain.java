/**   
 * @Title: ServerMain.java 
 * @Package org.jccdemo.dsf.javanio 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年6月7日 下午4:01:57 
 * @version V1.0   
 */
package org.jccdemo.dsf.javanio.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.jccdemo.dsf.DsfConst;
import org.jccdemo.dsf.javanio.model.HeartBeat;
import org.jccdemo.dsf.javanio.model.MsgBody;
import org.jccdemo.dsf.javanio.model.MsgHeader;
import org.jccdemo.dsf.javanio.model.RequestMsg;
import org.jccdemo.dsf.javanio.model.ResponseMsg;
import org.jccdemo.dsf.javanio.tools.JDKObjCodecUtil;
import org.jccdemo.dsf.javanio.tools.ServerResponseQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ServerMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年6月7日 下午4:01:57
 * 
 */
public class ServerMain {
	private static Logger log = LoggerFactory.getLogger(ServerMain.class);

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
	 * @Date 2016年6月7日 下午4:01:57
	 * @param args
	 * @throws
	 */
	public static void start() throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false);
		server.socket().bind(new InetSocketAddress(DsfConst.PORT));
		server.register(selector, SelectionKey.OP_ACCEPT);
		log.info("---Start server---"+server.hashCode());
		while (true) {
			int select = selector.select(2000);
			if(select < 1){
				log.info("no channel select");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isAcceptable()) {
					SocketChannel accept = server.accept();
					log.info("---server accept hashCode ---"+accept.hashCode());
					accept.configureBlocking(false);
					accept.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
				} else if(key.isConnectable()){
					log.info("--server and client channel is connected ---");
				} else 	if (key.isReadable()) {
					log.info(" --server begin to read msg --");
					doRead(selector, key);
				} else if (key.isWritable()) {
					doWrite(selector, key);
				}

			}
		}
	}
	private static void doWrite(Selector selector, SelectionKey key)
			throws ClosedChannelException {
		SocketChannel writeChannel = (SocketChannel) key.channel();
		writeChannel.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ);
		try {
			ByteBuffer responseMsg = JDKObjCodecUtil.getResponseMsg(ServerResponseQueue.getEle());
			if (responseMsg != null) {
				writeChannel.write(responseMsg);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	private static void doRead(Selector selector, SelectionKey key)
			throws ClosedChannelException, IOException {
		SocketChannel readChannel = (SocketChannel) key.channel();
		readChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		ByteBuffer bufHeader = ByteBuffer.allocate(DsfConst.MSG_HEADER_LENGTH);
		long bytesRead = readChannel.read(bufHeader);
		if (bytesRead == -1) {
			readChannel.close();
		} else {
			try {
				MsgHeader header = JDKObjCodecUtil.deserizable(	bufHeader.array(), MsgHeader.class);
				int length = header.getLength();
				ByteBuffer bufBody = ByteBuffer.allocate(length);
				int bodyRead = readChannel.read(bufBody);
				if (bodyRead != -1) {
					MsgBody body = JDKObjCodecUtil.deserizable(bufBody.array(), MsgBody.class);
					RequestMsg request = new RequestMsg();
					request.setBody(body);
					request.setHearder(header);
					handlerRequest(request);
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	private static void handlerRequest(RequestMsg request) {
		log.info("server recieve msg body="	+ request.getBody().getMsg().toString());
		ResponseMsg response = new ResponseMsg();
		response.setHearder(request.getHearder());
		response.setBody(createMsgBody());
		ServerResponseQueue.putEle(response);
	}

	private static MsgBody createMsgBody() {
		HeartBeat hb = new HeartBeat();
		hb.setContent("server response to heart beat !");
		try {
			hb.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
		}
		MsgBody body = new MsgBody();
		body.setMsg(hb);
		return body;
	}
}
