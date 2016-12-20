/**   
* @Title: BrandItemValueParseImpl.java 
* @Package com.jd.jcc.process.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月7日 下午7:31:19 
* @version V1.0   
*/
package com.jd.jcc.process.service;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jccdemo.expression.VariableType;

/** 
 * @ClassName: BrandItemValueParseImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月7日 下午7:31:19 
 *  
 */
public class BrandItemValueParseImpl implements BrandItemValueParse {
	private Logger log  = LoggerFactory.getLogger(getClass());
	public Map<String, VariableType> parseVariableValue(Map<String, VariableType> codeValues, Object requestParam) {
		if(codeValues != null && !codeValues.isEmpty()){
			Collection<VariableType> values = codeValues.values();
			for(VariableType v:values){
				if(VariableType.LEFT.equals(v.getLeftOrRight())){
					int nextInt = new Random().nextInt(5);
					v.setValue(nextInt);
				}
			}
		}
		return codeValues;
	}

}
