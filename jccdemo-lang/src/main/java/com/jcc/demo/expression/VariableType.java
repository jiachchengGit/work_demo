/**   
* @Title: VariableType.java 
* @Package com.jd.engine.domain.engine.exp 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月8日 下午2:11:02 
* @version V1.0   
*/
package com.jcc.demo.expression;

/** 
 * @ClassName: VariableType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月8日 下午2:11:02 
 *  
 */
public class VariableType {
	
	private Class<?> classType;
	
	private Object value;
	
	private String valueType;
	
	/**
	 * @return the valueType
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @author chenjiacheng
	 * @Date 2016年3月8日 下午2:19:05
	 * @param classType
	 * @param value 
	 */
	public VariableType(Class<?> classType, Object value) {
		this.classType = classType;
		this.value = value;
	}
	
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @author chenjiacheng
	 * @Date 2016年3月8日 下午2:20:08
	 * @param classType 
	 */
	public VariableType(Class<?> classType) {
		super();
		this.classType = classType;
	}



	/**
	 * @return the classType
	 */
	public Class<?> getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
}
