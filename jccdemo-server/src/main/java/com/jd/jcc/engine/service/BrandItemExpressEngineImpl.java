/**   
* @Title: BrandItemExpressEngineImpl.java 
* @Package com.jd.jcc.engine.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月5日 下午2:36:16 
* @version V1.0   
*/
package com.jd.jcc.engine.service;

import java.util.Map;

import com.jcc.demo.expression.IkExpressionUtils;
import com.jcc.demo.expression.VariableType;
import com.jd.jcc.engine.common.BrandExpressUtils;
import com.jd.jcc.engine.model.ExpressionAssemblyResult;
import com.jd.jcc.engine.nodedefine.BranchExpression;

/** 
 * @ClassName: BrandItemExpressEngineImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月5日 下午2:36:16 
 *  
 */
public class BrandItemExpressEngineImpl implements IBrandItemExpressEngine {

	private IBrandItemValueParse brandItemValueParse;
	
	public boolean excuteExpress(BranchExpression branchExpression,Object requestParam) {
		ExpressionAssemblyResult express = BrandExpressUtils.assemblyBranchExpress(branchExpression);
		Map<String, VariableType> parseVariableValue = brandItemValueParse.parseVariableValue(express.getValues(),requestParam);
		boolean flag =IkExpressionUtils.logicExpression(express.getExpression(), parseVariableValue);
		return flag;
	}
}
