import java.io.IOException;
import java.io.Serializable;

import com.jcc.demo.common.ObjectCopyTools;

/**   
 * @Title: TestCopy.java 
 * @Package  
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年10月9日 下午3:39:27 
 * @version V1.0   
 */

/** 
 * @ClassName: TestCopy 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年10月9日 下午3:39:27 
 *  
 */
public class TestCopy {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		MyBean mb=new MyBean();
		MyModel mm = new MyModel();
		mm.setName("JCC model");
		mb.setModel(mm);
		mb.setMsg("mybean msg");
		Object dcBean = ObjectCopyTools.deepClone(mb);
		System.out.println(dcBean);
		System.out.println(mb.hashCode()+"--"+dcBean.hashCode());
	}
}

class MyBean implements Serializable{
	private MyModel model;
	
	private String msg;
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the model
	 */
	public MyModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(MyModel model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyBean [model=" + model.toString() + ", msg=" + msg + "]";
	}
	
}

class MyModel  implements Serializable{
	private String name;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyModel [name=" + name + "]";
	}
}