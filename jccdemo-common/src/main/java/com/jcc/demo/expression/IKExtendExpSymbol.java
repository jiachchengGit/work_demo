/**   
* @Title: IKExtendExpSymbol.java 
* @Package com.jd.jimi.kms.expresssion 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年2月29日 下午2:19:19 
* @version V1.0   
*/
package com.jcc.demo.expression;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
 * @ClassName: IKExtendExpSymbol 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年2月29日 下午2:19:19 
 *  
 */
public class IKExtendExpSymbol {
	
	private Logger log  = LoggerFactory.getLogger(getClass());
	
	/**
	 * 匹配正则表达式
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年2月29日 下午2:21:45
	 * @param regex 正则表达式
	 * @param value 值
	 * @return        
	 * @throws
	 */
	public boolean matchRegex(String regex,String value) {
		boolean flag = false;
		if(regex != null && value != null){
			flag = Pattern.compile(regex).matcher(value).find();
		}
		log.info("--正则表达式:[{}],value={}",new String[]{regex,value});
		return flag;
	}
	
	/**
	 * 列表中包含某个值
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年2月29日 下午2:29:53
	 * @param enumValues 常量值列表
	 * @param value 测试值
	 * @return        
	 * @throws
	 */
	public boolean isListContains(List<String> enumValues,String value ){
		boolean flag = false;
		if(enumValues == null || enumValues.size() <1 || value == null){
			
		}else{
			for(String s:enumValues){
				if(value.equals(s)){
					flag = true;
					break;
				}
			}
			log.info("--正则表达式:[{}],value={}",new String[]{StringUtils.join(enumValues, ","),value});
		}
		return flag;
	}
	
	/**
	 * 逗号分隔的字符串数组是否包含某个值
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年2月29日 下午2:34:08
	 * @param enumValues 字符串，使用英文逗号分隔
	 * @param value 测试值
	 * @return        
	 * @throws
	 */
	public boolean isStrContains(String enumValues,String value){
		boolean flag =false;
		if(enumValues != null && value != null){
			List<String> asList = Arrays.asList(enumValues.split(","));
			flag = isListContains(asList, value);
		}
		log.info("--正则表达式:[{}],value={}",new String[]{enumValues,value});
		return flag;
	}
}
