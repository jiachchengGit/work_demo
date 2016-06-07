/**   
* @Title: Person.java 
* @Package org.jccdemo.dsf.proxy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月7日 上午10:32:23 
* @version V1.0   
*/
package org.jccdemo.dsf.proxy;

/** 
 * @ClassName: Person 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月7日 上午10:32:23 
 *  
 */
public class Person {
	private String age;
	private String addr;
	
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @author chenjiacheng
	 * @Date 2016年6月7日 上午10:57:05
	 * @param age
	 * @param addr 
	 */
	public Person(String age, String addr) {
		super();
		this.age = age;
		this.addr = addr;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [age=" + age + ", addr=" + addr + "]";
	}
	
}
