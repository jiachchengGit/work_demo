/**   
* @Title: MsgType.java 
* @Package org.jccdemo.dsf.base 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月18日 下午3:51:42 
* @version V1.0   
*/
package org.jccdemo.dsf.base;

/** 
 * @ClassName: MsgType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月18日 下午3:51:42 
 *  
 */
public enum MsgType {
	Heart(1),
	Business(2);
	private int msgType;
	
	private MsgType(int msgType){
		this.msgType=msgType;
	}
	
	public int getMsgType() {
		return msgType;
	}
}
