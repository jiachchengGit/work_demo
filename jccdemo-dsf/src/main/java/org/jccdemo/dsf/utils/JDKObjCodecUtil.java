package org.jccdemo.dsf.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import org.jccdemo.dsf.model.HeartBeat;
import org.jccdemo.dsf.model.MsgBody;
import org.jccdemo.dsf.model.MsgHeader;
import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;

public class JDKObjCodecUtil {
	
	public static byte[] serializable(Object obj) throws IOException{
		ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream= new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(obj);
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		return byteArray;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deserizable(byte[] byteArray,Class<T> t) throws Exception{
		if(byteArray == null){
			return null;
		}
		ByteArrayInputStream byteArrayInputStream= new ByteArrayInputStream(byteArray);
		ObjectInputStream objectInputStream= new ObjectInputStream(byteArrayInputStream);
		Object ro = objectInputStream.readObject();
		return (T)ro;
	}
	
	public static ByteBuffer getRequestMsg(RequestMsg request) throws Exception {
		if(request ==  null){
			return null;
		}
		byte[] bodyBytes = serializable(request.getBody());
		int bLen = bodyBytes.length;
		request.getHearder().setLength(bLen);		
		byte[] headerBytes = serializable(request.getHearder());
		int hLen = headerBytes.length;
		System.out.println("header length="+hLen);
		ByteBuffer bb = ByteBuffer.allocate(bLen+hLen);
		bb.put(headerBytes);
		for(int i=0;i<bLen;i++){
			bb.put(bodyBytes[i]);
		}
		bb.flip();//记得由写模式变为读模式，否则读不到数据
		return bb;
	}
	
	public static ByteBuffer getResponseMsg(ResponseMsg response) throws Exception {
		if(response ==  null){
			return null;
		}
		byte[] bodyBytes = serializable(response.getBody());
		int bLen = bodyBytes.length;
		response.getHearder().setLength(bLen);		
		byte[] headerBytes = serializable(response.getHearder());
		int hLen = headerBytes.length;
		ByteBuffer bb = ByteBuffer.allocate(bLen+hLen);
		bb.put(headerBytes);
		for(int i=0;i<bLen;i++){
			bb.put(bodyBytes[i]);
		}
		bb.flip();//记得由写模式变为读模式，否则读不到数据
		return bb;
	}
	
	public static void main(String[] args) throws Exception{
		
		HeartBeat hb = new HeartBeat();
		hb.setContent("This heart beat !!!");
		hb.setIp(InetAddress.getLocalHost().getHostAddress());		
		RequestMsg request = new RequestMsg();
		MsgHeader header = new MsgHeader();
		header.setMsgType(hb.getMsgType());
		request.setHearder(header);
		MsgBody body = new MsgBody();
		body.setMsg(hb);
		request.setBody(body);
		ByteBuffer bb = getRequestMsg(request);
		System.out.println("bb = "+bb.array().length);
		
	}
}
