/**   
* @Title: ProcessNodeServiceImpl.java 
* @Package com.jd.jcc.engine.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年4月1日 上午9:56:09 
* @version V1.0   
*/
package com.jd.jcc.engine.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.jd.jcc.engine.dao.ProcessNodeDao;
import com.jd.jcc.engine.model.ProNodeTypeEnum;
import com.jd.jcc.engine.model.ProcessBean;
import com.jd.jcc.engine.nodedefine.BaseProNode;
import com.jd.jcc.engine.nodedefine.AggregationProNode;
import com.jd.jcc.engine.nodedefine.BranchExpression;
import com.jd.jcc.engine.nodedefine.BranchProNode;
import com.jd.jcc.engine.nodedefine.BusinessProNode;
import com.jd.jcc.engine.nodedefine.EndProNode;
import com.jd.jcc.engine.nodedefine.BranchNodeItem;
import com.jd.jcc.engine.nodedefine.ParallelLineItem;
import com.jd.jcc.engine.nodedefine.ParallelProNode;
import com.jd.jcc.engine.nodedefine.StartProNode;
import com.jd.jcc.engine.nodedefine.SubProNode;

/** 
 * @ClassName: ProcessNodeServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年4月1日 上午9:56:09 
 *  
 */
public class ProcessNodeServiceImpl implements IProcessNodeService {
	
	private Logger log = LoggerFactory.getLogger(ProcessNodeServiceImpl.class);
	
	private ProcessNodeDao processNodeDao;
	
	public ProcessBean queryProcessBeanById(String processId) {
		ProcessBean pb = null;
		List<BaseProNode> nodes = processNodeDao.queryNodesByProId(processId);
		Map<String,BaseProNode> nodeMap = assemblyNodeAttrs(nodes);
		BaseProNode startNode = assemblyNodeRelative(nodeMap);
		if(startNode != null){
			pb = new ProcessBean();
			pb.setNodes(nodeMap);
			pb.setStartNode(startNode);
			pb.setProcessId(processId);
		}
		return pb;
	}
	
	/** 
	 * @Description: 组装对象关系 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:29:07
	 * @param nodeMap
	 * @return        
	 * @throws 
	 */
	private BaseProNode assemblyNodeRelative(Map<String, BaseProNode> nodeMap) {
		BaseProNode startNode = null;
		if(nodeMap != null && !nodeMap.isEmpty()){
			Collection<BaseProNode> values = nodeMap.values();
			for(BaseProNode n:values){
				String parentKey = n.getParentKey();
				if(parentKey != null){
					String[] keys = parentKey.split(",");
					for(String key:keys){
						BaseProNode parentNode = nodeMap.get(key);
						//设置父级节点
						n.addParentNode(parentNode);
						//设置子集节点
						parentNode.addChildNode(n);
					}
				}else if(ProNodeTypeEnum.start.name().equals(n.getNodeType())){
					startNode=n;
				}else{
					throw new RuntimeException("当前节点没有父级节点且不是开始节点nodeId="+n.getNodeId()+",nodeName="+n.getNodeName());
				}
			}
		}
		return startNode;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:05:36
	 * @param nodes
	 * @return        
	 * @throws 
	 */
	private Map<String,BaseProNode> assemblyNodeAttrs(List<BaseProNode> nodes) {
		Map<String,BaseProNode> nodeMap = null;
		if(nodes != null && nodes.size() > 0){
			nodeMap  = new HashMap<String,BaseProNode>();
			for(BaseProNode n:nodes){
				BaseProNode nn = null;
				String nodeType = n.getNodeType();
				if(ProNodeTypeEnum.start.name().equals(nodeType)){
					nn = packageStartNode(n);
				}if(ProNodeTypeEnum.end.name().equals(nodeType)){
					nn = packageEndNode(n);
				}if(ProNodeTypeEnum.business.name().equals(nodeType)){
					nn = packageBusinessNode(n);
				}if(ProNodeTypeEnum.branch.name().equals(nodeType)){
					nn = packageBranchNode(n);
				}if(ProNodeTypeEnum.parallel.name().equals(nodeType)){
					nn = packageParallelNode(n);
				}if(ProNodeTypeEnum.aggregation.name().equals(nodeType)){
					nn = packageAggregatrionNode(n);
				}if(ProNodeTypeEnum.sub_process.name().equals(nodeType)){
					nn = packageSubProNode(n);
				}
				nodeMap.put(nn.getNodeKey(), nn);
			}
		}
		return nodeMap;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:13:19
	 * @param n
	 * @return        
	 * @throws 
	 */
	private ParallelProNode packageParallelNode(BaseProNode n) {
		ParallelProNode pn = new ParallelProNode();
		BeanUtils.copyProperties(n, pn);
		List<ParallelLineItem> lineItems = processNodeDao.queryParallelLineItemByNodeKey(n.getNodeKey());
		pn.setLineItems(lineItems);
		return pn;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:12:46
	 * @param n
	 * @return        
	 * @throws 
	 */
	private AggregationProNode packageAggregatrionNode(BaseProNode n) {
		AggregationProNode an = new AggregationProNode();
		BeanUtils.copyProperties(n, an);
		return an;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:12:13
	 * @param n
	 * @return        
	 * @throws 
	 */
	private SubProNode packageSubProNode(BaseProNode n) {
		SubProNode sn = new SubProNode();
		BeanUtils.copyProperties(n, sn);
		String nodeId = n.getNodeId();
		String processId = processNodeDao.querySubProcessByNodeKey(nodeId);
		sn.setSubProcessId(processId);
		return sn;
	}
	
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:11:32
	 * @param n
	 * @return        
	 * @throws 
	 */
	private BranchProNode packageBranchNode(BaseProNode n) {
		BranchProNode bn = new BranchProNode();
		BeanUtils.copyProperties(n, bn);
		List<BranchNodeItem> items = processNodeDao.queryBranchItemByNodeKey(bn.getNodeId());
		if(items != null && items.size() > 0){
			for(BranchNodeItem ni:items){
				String expression = ni.getExpression();
				BranchExpression parseObject = JSONObject.parseObject(expression, BranchExpression.class);
				ni.setBranchExpression(parseObject);
			}
		}
		bn.setItems(items);
		return bn;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:11:01
	 * @param n
	 * @return        
	 * @throws 
	 */
	private BusinessProNode packageBusinessNode(BaseProNode n) {
		BusinessProNode bn = new BusinessProNode();
		BeanUtils.copyProperties(n, bn);
		return bn;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:10:22
	 * @param n
	 * @return        
	 * @throws 
	 */
	private EndProNode packageEndNode(BaseProNode n) {
		EndProNode en = new EndProNode();
		BeanUtils.copyProperties(n, en);
		return en;
	}
	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年4月1日 上午10:09:52
	 * @param n
	 * @return        
	 * @throws 
	 */
	private StartProNode packageStartNode(BaseProNode n) {
		StartProNode sn = new StartProNode();
		BeanUtils.copyProperties(n, sn);
		
		return sn;
	}

	/**
	 * @return the processNodeDao
	 */
	public ProcessNodeDao getProcessNodeDao() {
		return processNodeDao;
	}

	/**
	 * @param processNodeDao the processNodeDao to set
	 */
	public void setProcessNodeDao(ProcessNodeDao processNodeDao) {
		this.processNodeDao = processNodeDao;
	}

}
