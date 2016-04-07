/**   
* @Title: TestProcess.java 
* @Package com.jd.jcc.process 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月7日 下午7:21:48 
* @version V1.0   
*/
package com.jd.jcc.process;

import com.jd.jcc.process.model.ProParam;
import com.jd.jcc.process.model.ProResult;

/** 
 * @ClassName: TestProcess 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月7日 下午7:21:48 
 *  
 */
public class TestProcess {

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月7日 下午7:21:48
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessExcuteEngine pe = new ProcessExcuteEngine();
		ProParam param = new ProParam();
		pe.excuteProcess("123", param);
		ProResult result = param.getResult();
		System.out.println(result.getResult());
	}

}
