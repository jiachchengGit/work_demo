/**   
* @Title: SubProNode.java 
* @Package com.jd.jcc.engine.nodedefine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:01:30 
* @version V1.0   
*/
package com.jd.jcc.engine.nodedefine;

import com.jd.jcc.engine.model.ProNodeTypeEnum;

/** 
 * @ClassName: SubProNode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午2:01:30 
 *  
 */
public class SubProNode extends AbstractProNode {
	//子流程ID
	private String subProcessId;
	
	public SubProNode(String nodeType) {
		super(ProNodeTypeEnum.sub_process.name());
	}
	
	/**
	 * @return the subProcessId
	 */
	public String getSubProcessId() {
		return subProcessId;
	}


	/**
	 * @param subProcessId the subProcessId to set
	 */
	public void setSubProcessId(String subProcessId) {
		this.subProcessId = subProcessId;
	}
}
