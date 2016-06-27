/**   
* @Title: MethodInvoker.java 
* @Package org.jccdemo.dsf.base 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年6月7日 上午11:15:25 
* @version V1.0   
*/
package org.jccdemo.dsf.base;

import org.jccdemo.dsf.model.RequestMsg;
import org.jccdemo.dsf.model.ResponseMsg;


/** 
 * @ClassName: MethodInvoker 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年6月7日 上午11:15:25 
 *  
 */
public interface MethodInvoker {
	public ResponseMsg invoke(RequestMsg request) throws Exception;
}
