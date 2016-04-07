/**   
* @Title: TestNode.java 
* @Package com.jd.jcc.engine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午1:56:11 
* @version V1.0   
*/
package com.jd.jcc.process;

import java.util.regex.Pattern;

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
		String expright = getValue();
		String DIGITAL_REGEX="(^\\d+\\.{0,1}\\d+$)";
		if(Pattern.compile(DIGITAL_REGEX).matcher(expright).find()){
			System.out.println("Aaaa");
		}
//		IProcessNodeService ps = new ProcessNodeServiceImpl();
//		ProcessBean bean = ps.queryProcessBeanById("123");
//		if(bean != null){
//			
//		}
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月7日 下午8:30:12
	 * @return        
	 * @throws 
	 */
	private static String getValue() {
		return "3";
	}
}
