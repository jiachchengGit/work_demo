/**   
* @Title: TestNode.java 
* @Package com.jd.jcc.engine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午1:56:11 
* @version V1.0   
*/
package com.jd.jcc.engine;

import com.jd.jcc.engine.nodedefine.BusinessProNode;
import com.jd.jcc.engine.nodedefine.StartProNode;

/** 
 * @ClassName: TestNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午1:56:11 
 *  
 */
public class TestNode {
	public static void main(String[]args){
		BusinessProNode bpn = new BusinessProNode();
		StartProNode spn = new StartProNode();
		spn.setNextNode(bpn);
		System.out.println(spn.getNextNode().getClass());
	}
}
