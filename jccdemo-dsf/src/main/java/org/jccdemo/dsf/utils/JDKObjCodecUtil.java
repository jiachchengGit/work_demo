package org.jccdemo.dsf.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
