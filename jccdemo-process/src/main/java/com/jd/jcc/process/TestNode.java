/**   
* @Title: TestNode.java 
* @Package com.jd.jcc.engine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午1:56:11 
* @version V1.0   
*/
package com.jd.jcc.process;

import com.jd.jcc.process.model.ProcessBean;
import com.jd.jcc.process.service.IProcessNodeService;
import com.jd.jcc.process.service.ProcessNodeServiceImpl;

/** 
 * @ClassName: TestNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午1:56:11 
 *  
 */
public class TestNode {
	public static void main(String[]args){
		IProcessNodeService ps = new ProcessNodeServiceImpl();
		ProcessBean bean = ps.queryProcessBeanById("123");
		if(bean != null){
			
		}
	}
}
