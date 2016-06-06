/**   
* @Title: TestMsgPack.java 
* @Package org.jccdemo.dsf 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月6日 上午10:43:57 
* @version V1.0   
*/
package org.jccdemo.dsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.msgpack.template.Templates;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

/** 
 * @ClassName: TestMsgPack 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月6日 上午10:43:57 
 *  
 */
public class TestMsgPack {

	/**
	 * @throws IOException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年6月6日 上午10:43:57
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) throws IOException {
		MessagePack msgpack = new MessagePack();
		MyBean bean = new MyBean();
		bean.setName("chenjiacheng");
		// Serialize
		byte[] raw = msgpack.write(bean);

		// Deserialize directly using a template
		MyBean b = msgpack.read(raw, MyBean.class);
		System.out.println(b.toString());
	}
}

@Message
class MyBean{
	
	private String name;
	private List<String> list;
	private MyBean obj;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}
	/**
	 * @return the obj
	 */
	public MyBean getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(MyBean obj) {
		this.obj = obj;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyBean [name=" + name + "]";
	}
}

