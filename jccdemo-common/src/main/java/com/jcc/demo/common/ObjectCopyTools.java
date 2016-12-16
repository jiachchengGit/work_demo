/**   
* @Title: ObjectCopyTools.java 
* @Package com.jcc.demo.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年10月9日 下午3:28:52 
* @version V1.0   
*/
package com.jcc.demo.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 * @ClassName: ObjectCopyTools 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年10月9日 下午3:28:52 
 *  
 */
public class ObjectCopyTools {
	
	public static Object deepClone(Object obj) throws IOException, ClassNotFoundException{
		/* 写入当前对象的二进制流 */  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(obj);  
  
        /* 读出二进制流产生的新对象 */  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis);  
        return ois.readObject();
	}
}
