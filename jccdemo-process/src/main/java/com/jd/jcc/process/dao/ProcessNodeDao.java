/**   
* @Title: ProcessNodeDao.java 
* @Package com.jd.jcc.engine.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 上午9:57:02 
* @version V1.0   
*/
package com.jd.jcc.process.dao;

import java.util.List;

import com.jd.jcc.process.model.ReplyInterfaceDefine;
import com.jd.jcc.process.nodedefine.BaseProNode;
import com.jd.jcc.process.nodedefine.BranchNodeItem;
import com.jd.jcc.process.nodedefine.ParallelLineItem;
import com.jd.jcc.process.nodedefine.ProcessModel;

/** 
 * @ClassName: ProcessNodeDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 上午9:57:02 
 *  
 */
public interface ProcessNodeDao {

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午9:58:59
	 * @param processId
	 * @return        
	 * @throws 
	 */
	List<BaseProNode> queryNodesByProId(String processId);

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 下午2:12:46
	 * @param nodeId
	 * @return        
	 * @throws 
	 */
	ProcessModel querySubProcessByNodeId(String nodeId);

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 下午2:47:17
	 * @param nodeId
	 * @return        
	 * @throws 
	 */
	List<ReplyInterfaceDefine> queryReplyInterfaceByNodeId(String nodeId);

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 下午3:09:28
	 * @param nodeKey
	 * @return        
	 * @throws 
	 */
	List<BranchNodeItem> queryBranchItemByNodeId(String nodeKey);

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月5日 上午11:38:45
	 * @param nodeKey
	 * @return        
	 * @throws 
	 */
	List<ParallelLineItem> queryParallelLineItemByNodeId(String nodeKey);

}
