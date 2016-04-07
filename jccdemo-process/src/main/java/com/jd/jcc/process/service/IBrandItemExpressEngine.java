/**   
* @Title: IBrandItemExpressParse.java 
* @Package com.jd.jcc.engine.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月5日 下午2:31:13 
* @version V1.0   
*/
package com.jd.jcc.process.service;

import com.jd.jcc.process.nodedefine.BranchExpression;

/** 
 * @ClassName: IBrandItemExpressParse 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月5日 下午2:31:13 
 *  
 */
public interface IBrandItemExpressEngine {

	/**
	 * @param requestParam  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月5日 下午2:35:25
	 * @param branchExpression
	 * @return        
	 * @throws 
	 */
	boolean excuteExpress(BranchExpression branchExpression, Object requestParam);

}
