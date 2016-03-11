/**   
* @Title: MathExpressionMain.java 
* @Package com.jccdemo.client.expression 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年2月18日 下午2:07:16 
* @version V1.0   
*/
package com.jccdemo.client.expression;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;
import org.wltea.expression.function.FunctionLoader;


/** 
 * @ClassName: MathExpressionMain 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年2月18日 下午2:07:16 
 *  
 */
public class MathExpressionMain {

	/**
	 * @throws NoSuchMethodException 
	 * @throws SecurityException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年2月18日 下午2:07:16
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		String expression="(a+b)*c";
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(Variable.createVariable("a", 110));
		variables.add(Variable.createVariable("b", 22));
		variables.add(Variable.createVariable("c", 2));
		variables.add(Variable.createVariable("d", null));
		variables.add(Variable.createVariable("d1", new Date(System.currentTimeMillis()+100)));
		variables.add(Variable.createVariable("d2", new Date()));
		variables.add(Variable.createVariable("myExp","你好，123，再见"));
		MyMatch mM = new MyMatch();
		FunctionLoader.addFunction("MyMatch2", mM, mM.getClass().getMethod("match"
				, new Class[]{java.lang.String.class,java.lang.String.class}));
		Object evaluate = ExpressionEvaluator.evaluate(expression, variables);
		System.out.println(evaluate);
		System.out.println(evaluate.getClass().getName());
		Object evaluate2 = ExpressionEvaluator.evaluate("d==null && b==221?\"你好\":\"不好\"", variables);
		System.out.println(evaluate2);
		String c = ExpressionEvaluator.compile("d==null && b==221?\"你好\":\"不好\"", variables);
		System.out.println(c);
		System.out.println(ExpressionEvaluator.evaluate("d1 > d2?\"你好1\":\"不好1\"", variables));
		Object match = ExpressionEvaluator.evaluate("true && d==null && true == $MyMatch2(myExp,\"1|2|3\")", variables);
		System.out.println("match="+match);
		System.out.println(ExpressionEvaluator.evaluate("你好", variables));
	}
}


