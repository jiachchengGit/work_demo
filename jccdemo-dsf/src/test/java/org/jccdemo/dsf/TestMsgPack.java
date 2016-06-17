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
import java.util.List;
import java.util.UUID;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;

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
		Object b = msgpack.read(raw, BaseBean.class);
		MyBean mb = msgpack.read(raw, MyBean.class);
		System.out.println(b.toString());
		System.out.println(mb.toString());
	}
}

@Message
class BaseBean{
	private String id = UUID.randomUUID().toString();

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseBean [id=" + id + "]";
	}
}

@Message
class MyBean extends BaseBean{
	
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
		return "MyBean [name=" + name + ", getId()=" + getId() + "]";
	}
}

