/**   
* @Title: AbstractProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 上午11:34:52 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

/** 
 * @ClassName: AbstractProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 上午11:34:52 
 *  
 */
public abstract class AbstractProNode{

	public abstract void addChildNode(BaseProNode child);
	
	public abstract void addParentNode(BaseProNode child);
}
