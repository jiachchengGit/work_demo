/**   
* @Title: IkExpressionUtils.java 
* @Package com.jd.jimi.kms.expresssion 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月4日 下午3:48:19 
* @version V1.0   
*/
package com.jcc.demo.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

/** 
 * @ClassName: IkExpressionUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月4日 下午3:48:19 
 *  
 */
public class IkExpressionUtils {
	
	public  static boolean logicExpression(String expression,Map<String,VariableType> values){
		boolean flag=false;
		List<Variable> variables =  new ArrayList<Variable>();
		if(values != null && !values.isEmpty()){
			Set<Entry<String, VariableType>> entrySet = values.entrySet();
			for(Entry<String, VariableType> e:entrySet){
				Object value = parseValue(e.getValue());
				Variable v = Variable.createVariable(e.getKey(), value);
				variables.add(v);
			}
		}
		if(StringUtils.isNotBlank(expression)){
			flag = (Boolean) ExpressionEvaluator.evaluate(expression, variables);
		}
		return flag;
	}

//	public static void main(String[] args){
//		VariableType vt = new VariableType(Double.class);
//		vt.setValue(12346.5);
//		Object pv = parseValue(vt);
//		System.out.println(pv.getClass());
//	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月8日 下午2:47:46
	 * @param value
	 * @return        
	 * @throws 
	 */
	private static Object parseValue(VariableType vt) {
		if(vt != null){
			Object value2 = vt.getValue();
			Class<?> classType = vt.getClassType();
			if(classType.isAssignableFrom(String.class)){
				return value2.toString();
			}else if(classType.isAssignableFrom(Double.class)){
					return Double.parseDouble(value2.toString());
			}else{
				return value2;
			}
		}
		return null;
	}
}
