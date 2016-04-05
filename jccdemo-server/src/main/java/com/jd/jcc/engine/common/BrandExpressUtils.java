/**   
* @Title: ClassRuleUtils.java 
* @Package com.jd.jimi.kms.process.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月7日 下午1:51:06 
* @version V1.0   
*/
package com.jd.jcc.engine.common;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcc.demo.expression.VariableType;
import com.jd.jcc.engine.model.ExpressionAssemblyResult;
import com.jd.jcc.engine.nodedefine.BranchExpression;
import com.jd.jcc.engine.nodedefine.BranchExpressionItem;

/** 
 * @ClassName: ClassRuleUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月7日 下午1:51:06 
 *  
 */

public class BrandExpressUtils {
	
	private static Logger log = LoggerFactory.getLogger(BrandExpressUtils.class);
	
	
	public static ExpressionAssemblyResult assemblyBranchExpress(BranchExpression expression){
		ExpressionAssemblyResult result = new ExpressionAssemblyResult();
		String expressionType = expression.getExpressionType();
		Map<String, VariableType> codeValues =null;
		String parseExpression = null;
		if(BranchExpression.TYPE_RULE.equals(expressionType)){
			parseExpression = parseRegexRule("user_quesion", expression.getScript(), codeValues);
		}else{
			List<BranchExpressionItem> expressionItems = expression.getExpressionItems();
			parseExpression = parseExpression(expressionItems,codeValues);
		}
		result.setValues(codeValues);
		result.setExpression(parseExpression);
		return result;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月4日 下午1:48:58
	 * @param expression
	 * @param codeValues
	 * @return        
	 * @throws 
	 */
	private static String parseExpression(List<BranchExpressionItem> items,Map<String, VariableType> codeValues) {
		StringBuffer sb = new StringBuffer();
		if(items != null && items.size() > 0){
			for(BranchExpressionItem ei:items){
				String ex = parseExpressionItem(ei,codeValues);
				sb.append(ex);
			}
		}
		return sb.toString();
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月4日 下午1:52:45
	 * @param ei
	 * @return        
	 * @throws 
	 */
	private static String parseExpressionItem(BranchExpressionItem item,Map<String, VariableType> codeValues) {
		String expleft = item.getExpleft();
		String expright = item.getExpright();
		String symbol = item.getSymbol();
		String connector = item.getConnector();
		StringBuffer exp = new StringBuffer();
		Class<?> classType = getValueClassType(expright);
		if(StringUtils.isBlank(expright)){
			expright = null;
		}else{
			VariableType valueRight = new VariableType(classType);
			valueRight.setValue(expright);
			codeValues.put(expright, valueRight);
		}
		VariableType valueLeft = new VariableType(classType);
		valueLeft.setValue(expleft);
		codeValues.put(expleft, valueLeft);
		
		if(LogicSymbol.IN.equals(symbol)){
			exp.append(String.format(IkFuction.IS_STR_CONTAINS, expright,expleft));
		}else if(LogicSymbol.MATCH.equals(symbol)){
			exp.append(String.format(IkFuction.MATCH_REGEX,expright,expleft));
		}else{
			exp.append(expleft).append(" ").append(symbol).append(" ").append(expright);
		}
		if(connector != null){
			if(LogicSymbol.OR.equals(connector)){
				exp.append(" || "); 
			}else if(LogicSymbol.AND.equals(connector)){
				exp.append(" && ");
			}
		}
		return exp.toString();
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月8日 下午2:21:52
	 * @param expright
	 * @return        
	 * @throws 
	 */
	private static Class<?> getValueClassType(String expright) {
		if(StringUtils.isNotBlank(expright)){
			String DIGITAL_REGEX="(^\\d+\\.{0,1}\\d+$)";
			if(Pattern.compile(DIGITAL_REGEX).matcher(expright).find()){
				return Double.class;
			}
		}
		return String.class;
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月4日 上午11:47:50
	 * @param script
	 * @param codeValues
	 * @return        
	 * @throws 
	 */
	private static String parseRegexRule(String code,String script, Map<String, VariableType> codeValues) {
		codeValues.put(code, new VariableType(String.class,null));
		return String.format(IkFuction.MATCH_REGEX, code,script);
	}
}

interface IkFuction{
	public String MATCH_REGEX="$MATCH_REGEX(%s,%s)";
	public String IS_LIST_CONTAINS="$IS_LIST_CONTAINS(%s,%s)";
	public String IS_STR_CONTAINS="$IS_STR_CONTAINS(%s,%s)";
}

interface LogicSymbol{
	public String IN = "in";
	public String OR ="OR";
	public String AND="AND";
	public String MATCH="match";
}