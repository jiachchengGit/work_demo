/**   
* @Title: IkExpTest.java 
* @Package com.jccdemo.client.expression.ik 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月11日 下午2:35:56 
* @version V1.0   
*/
package com.jccdemo.client.expression.ik;

import java.util.ArrayList;
import java.util.List;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

/** 
 * @ClassName: IkExpTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月11日 下午2:35:56 
 *  
 */
public class IkExpTest {

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月11日 下午2:35:56
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(Variable.createVariable("arryValue", "你好,北京,山东,连"));
		variables.add(Variable.createVariable("value", "北京"));
		Object evaluate = ExpressionEvaluator.evaluate("$IS_STR_CONTAINS(arryValue,value)", variables);
		System.out.println(evaluate);
	}
}
