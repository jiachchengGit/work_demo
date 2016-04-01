/**   
* @Title: ProcessNodeDao.java 
* @Package com.jd.jcc.engine.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 上午9:57:02 
* @version V1.0   
*/
package com.jd.jcc.engine.dao;

import java.util.List;

import com.jd.jcc.engine.model.ReplyInterfaceConfig;
import com.jd.jcc.engine.model.ReplyInterfaceDefine;
import com.jd.jcc.engine.nodedefine.BaseProNode;
import com.jd.jcc.engine.nodedefine.BranchNodeItem;

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
	String querySubProcessByNodeId(String nodeId);

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

}
