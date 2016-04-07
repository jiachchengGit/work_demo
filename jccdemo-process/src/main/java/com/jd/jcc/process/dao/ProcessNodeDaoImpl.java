/**   
* @Title: ProcessNodeDaoImpl.java 
* @Package com.jd.jcc.process.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月7日 下午2:24:23 
* @version V1.0   
*/
package com.jd.jcc.process.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jcc.process.model.ReplyInterfaceDefine;
import com.jd.jcc.process.nodedefine.BaseProNode;
import com.jd.jcc.process.nodedefine.BranchNodeItem;
import com.jd.jcc.process.nodedefine.ParallelLineItem;
import com.jd.jcc.process.nodedefine.ProcessModel;

/** 
 * @ClassName: ProcessNodeDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月7日 下午2:24:23 
 *  
 */
public class ProcessNodeDaoImpl extends AbstractBaseDao implements	ProcessNodeDao {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public List<BaseProNode> queryNodesByProId(String processId){
		BaseProNode pm = new BaseProNode();
		pm.setProcessId(processId);
		try {
			List<BaseProNode> queryForList = getSqlMapClient().queryForList(getNameSpace()+"queryNodesByProId", pm);
			return queryForList;
		} catch (SQLException e) {
			log.error("数据库查询失败",e);
		}
		return null;
	}

	public ProcessModel querySubProcessByNodeId(String nodeId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("nodeId", nodeId);
		param.put("processStatus", "1");
		try {
			List<ProcessModel> queryForList = getSqlMapClient().queryForList(getNameSpace()+"querySubProcessByNodeId", param);
			if(queryForList != null && queryForList.size() > 0){
				return queryForList.get(0);
			}
		} catch (SQLException e) {
			log.error("数据库查询失败",e);
		}
		return null;
	}

	public List<ReplyInterfaceDefine> queryReplyInterfaceByNodeId(String nodeId) {
		
		return null;
	}

	public List<BranchNodeItem> queryBranchItemByNodeId(String nodeId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("nodeId", nodeId);
		try {
			List<BranchNodeItem> queryForList = getSqlMapClient().queryForList(getNameSpace()+"queryBranchItemByNodeId", param);
			return queryForList;
		} catch (SQLException e) {
			log.error("数据库查询失败",e);
		}
		return null;
	}

	public List<ParallelLineItem> queryParallelLineItemByNodeId(String nodeId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("nodeId", nodeId);
		try {
			List<ParallelLineItem> queryForList = getSqlMapClient().queryForList(getNameSpace()+"queryParallelLineItemByNodeId", param);
			return queryForList;
		} catch (SQLException e) {
			log.error("数据库查询失败",e);
		}
		return null;
	}

	@Override
	public String getNameSpace() {
		return "com.jd.jcc.process.dao.ProcessNodeDao.";
	}

}
